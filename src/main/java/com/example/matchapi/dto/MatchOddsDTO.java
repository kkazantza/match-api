package com.example.matchapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class MatchOddsDTO {

    private Long id;
    @NotNull
    private String specifier;
    @NotNull
    private Double odd;
    @NotNull
    private Long matchId;
}
