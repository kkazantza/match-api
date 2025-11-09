package com.example.matchapi.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class MatchDTO {

    private Long id;
    private String description;
    private LocalDate matchDate;
    private LocalTime matchTime;
    private String teamA;
    private String teamB;
    private String sport; // Use String instead of enum for easier JSON mapping

    private List<MatchOddsDTO> odds; // nested DTOs
}
