package com.example.matchapi.controller;

import com.example.matchapi.dto.MatchOddsDTO;
import com.example.matchapi.service.MatchOddsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MatchOddsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MatchOddsService matchOddsService;

    @InjectMocks
    private MatchOddsController matchOddsController;

    private ObjectMapper objectMapper;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc = MockMvcBuilders.standaloneSetup(matchOddsController).build();
    }

    @Test
    void testGetAllOdds() throws Exception {
        MatchOddsDTO odds = MatchOddsDTO.builder()
                        .id(1L)
                        .matchId(1L)
                        .specifier("X")
                        .odd(1.5)
                        .build();

        Mockito.when(matchOddsService.findAll()).thenReturn(List.of(odds));

        mockMvc.perform(get("/api/odds"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].specifier", is("X")));
    }

    @Test
    void testGetOddsById() throws Exception {
        MatchOddsDTO odds = MatchOddsDTO.builder()
                        .id(1L)
                        .matchId(1L)
                        .specifier("X")
                        .odd(1.5)
                        .build();

        Mockito.when(matchOddsService.findById(1L)).thenReturn(odds);

        mockMvc.perform(get("/api/odds/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.odd", is(1.5)));
    }

    @Test
    void testCreateOdds() throws Exception {
        MatchOddsDTO request = MatchOddsDTO.builder()
                .id(null)
                .matchId(1L)
                .specifier("1")
                .odd(2.0)
                .build();
        MatchOddsDTO saved = MatchOddsDTO.builder()
                .id(2L)
                .matchId(1L)
                .specifier("1")
                .odd(2.0)
                .build();

        Mockito.when(matchOddsService.save(Mockito.any(MatchOddsDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/api/odds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.specifier", is("1")));
    }
}
