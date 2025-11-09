package com.example.matchapi.service;

import com.example.matchapi.dto.MatchDTO;
import com.example.matchapi.mapper.MatchMapper;
import com.example.matchapi.model.Match;
import com.example.matchapi.model.Sport;
import com.example.matchapi.repository.MatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchService matchService;

    private Match match;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        match = Match.builder()
                .id(1L)
                .description("OSFP-PAO")
                .teamA("OSFP")
                .teamB("PAO")
                .matchDate(LocalDate.of(2021,3,31))
                .matchTime(LocalTime.of(12,0))
                .sport(Sport.FOOTBALL)
                .build();
    }


    @Test
    void testFindById_whenExists() {
        when(matchRepository.findById(1L)).thenReturn(Optional.of(match));

        MatchDTO result = matchService.findById(1L);
        assertNotNull(result);
        assertEquals("OSFP-PAO", result.getDescription());
        verify(matchRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_whenNotExists() {
        when(matchRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> matchService.findById(2L));
        verify(matchRepository, times(1)).findById(2L);
    }

    @Test
    void testFindAll() {
        when(matchRepository.findAll()).thenReturn(Arrays.asList(match));

        var result = matchService.findAll();
        assertEquals(1, result.size());
        assertEquals("OSFP", result.get(0).getTeamA());
        verify(matchRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(matchRepository.save(any(Match.class))).thenReturn(match);

        MatchDTO dto = MatchMapper.toDto(match);
        MatchDTO saved = matchService.save(dto);

        assertNotNull(saved);
        verify(matchRepository, times(1)).save(any(Match.class));
    }
}