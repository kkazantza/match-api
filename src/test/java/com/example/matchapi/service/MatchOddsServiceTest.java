package com.example.matchapi.service;

import com.example.matchapi.dto.MatchOddsDTO;
import com.example.matchapi.model.Match;
import com.example.matchapi.model.MatchOdds;
import com.example.matchapi.repository.MatchOddsRepository;
import com.example.matchapi.repository.MatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MatchOddsServiceTest {

    @Mock
    private MatchOddsRepository matchOddsRepository;

    @InjectMocks
    private MatchOddsService matchOddsService;

    private MatchOdds odds;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Match match = Match.builder()
                .id(1L)
                .description("OSFP-PAO")
                .build();

        odds = MatchOdds.builder()
                .id(1L)
                .match(match)
                .specifier("X")
                .odd(1.5)
                .build();
    }

    @Test
    void testFindById_whenExists() {
        when(matchOddsRepository.findById(1L)).thenReturn(Optional.of(odds));

        MatchOddsDTO result = matchOddsService.findById(1L);

        assertNotNull(result);
        assertEquals("X", result.getSpecifier());
        verify(matchOddsRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_whenNotExists() {
        when(matchOddsRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> matchOddsService.findById(2L));
        verify(matchOddsRepository, times(1)).findById(2L);
    }

}
