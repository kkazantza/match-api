package com.example.matchapi.controller;

import com.example.matchapi.dto.MatchDTO;
import com.example.matchapi.model.Sport;
import com.example.matchapi.service.MatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MatchControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MatchService matchService;

    @InjectMocks
    private MatchController matchController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // enable Java 8 date/time support

        mockMvc = MockMvcBuilders.standaloneSetup(matchController).build();
    }

    @Test
    void testGetAllMatches() throws Exception {
        MatchDTO match =
                MatchDTO.builder()
                        .id(1L)
                        .description("OSFP-PAO")
                        .matchDate(LocalDate.of(2021,3,31))
                        .matchTime(LocalTime.of(12,0))
                        .teamA("OSFP")
                        .teamB("PAO")
                        .sport(Sport.FOOTBALL.name())
                        .build();


        Mockito.when(matchService.findAll()).thenReturn(List.of(match));

        mockMvc.perform(get("/api/matches"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].teamA", is("OSFP")));
    }

    @Test
    void testGetMatchById() throws Exception {
        MatchDTO match =
                MatchDTO.builder()
                        .id(1L)
                        .description("OSFP-PAO")
                        .matchDate(LocalDate.of(2021,3,31))
                        .matchTime(LocalTime.of(12,0))
                        .teamA("OSFP")
                        .teamB("PAO")
                        .sport(Sport.FOOTBALL.name())
                        .build();

        Mockito.when(matchService.findById(1L)).thenReturn(match);

        mockMvc.perform(get("/api/matches/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teamB", is("PAO")));
    }

    @Test
    void testCreateMatch() throws Exception {
        MatchDTO match = MatchDTO.builder()
                .id(null)
                .description("OSFP-PAO")
                .matchDate(LocalDate.of(2021,3,31))
                .matchTime(LocalTime.of(12,0))
                .teamA("OSFP")
                .teamB("PAO")
                .sport(Sport.FOOTBALL.name())
                .build();

        MatchDTO saved = MatchDTO.builder()
                .id(1L)
                .description("OSFP-PAO")
                .matchDate(LocalDate.of(2021,3,31))
                .matchTime(LocalTime.of(12,0))
                .teamA("OSFP")
                .teamB("PAO")
                .sport(Sport.FOOTBALL.name())
                .build();

        Mockito.when(matchService.save(Mockito.any(MatchDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/api/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(match)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }
}
