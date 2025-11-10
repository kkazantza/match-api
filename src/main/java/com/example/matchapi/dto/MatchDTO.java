package com.example.matchapi.dto;

import com.example.matchapi.model.Sport;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@Jacksonized
public class MatchDTO {

    private Long id;
    @NotNull(message = "Description must not be null")
    private String description;
    @NotNull(message = "Match date must not be null")
    private LocalDate matchDate;
    @NotNull(message = "Match time must not be null")
    private LocalTime matchTime;
    @NotNull(message = "Team A must not be null")
    private String teamA;
    @NotNull(message = "Team B must not be null")
    private String teamB;
    @NotNull(message = "Sport must not be null")
    @ValidSport
    private String sport;

    private List<MatchOddsDTO> odds; // nested DTOs
}
