package com.example.matchapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchOddsDTO {

    private Long id;
    private String specifier;
    private Double odd;
    private Long matchId;
}
