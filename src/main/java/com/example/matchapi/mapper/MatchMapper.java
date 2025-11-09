package com.example.matchapi.mapper;

import com.example.matchapi.dto.MatchDTO;
import com.example.matchapi.dto.MatchOddsDTO;
import com.example.matchapi.model.Match;
import com.example.matchapi.model.MatchOdds;
import com.example.matchapi.model.Sport;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MatchMapper {

    public MatchDTO toDto(Match match) {
        if (match == null) return null;

        List<MatchOddsDTO> oddsDto = match.getOdds() == null ? null :
                match.getOdds().stream()
                        .map(MatchMapper::toDto)
                        .collect(Collectors.toList());

        return MatchDTO.builder()
                .id(match.getId())
                .description(match.getDescription())
                .matchDate(match.getMatchDate())
                .matchTime(match.getMatchTime())
                .teamA(match.getTeamA())
                .teamB(match.getTeamB())
                .sport(match.getSport().name())
                .odds(oddsDto)
                .build();
    }

    public MatchOddsDTO toDto(MatchOdds odds) {
        if (odds == null) return null;

        return MatchOddsDTO.builder()
                .id(odds.getId())
                .specifier(odds.getSpecifier())
                .odd(odds.getOdd())
                .matchId(odds.getMatch()!=null?odds.getMatch().getId():null)
                .build();
    }

    public Match toEntity(MatchDTO dto) {
        if (dto == null) return null;

        Match match = Match.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .matchDate(dto.getMatchDate())
                .matchTime(dto.getMatchTime())
                .teamA(dto.getTeamA())
                .teamB(dto.getTeamB())
                .sport(Sport.valueOf(dto.getSport().toUpperCase()))
                .build();

        if (dto.getOdds() != null) {
            List<MatchOdds> odds = dto.getOdds().stream()
                    .map(odto -> toEntity(odto, match))
                    .collect(Collectors.toList());
            match.setOdds(odds);
        }

        return match;
    }

    public MatchOdds toEntity(MatchOddsDTO dto, Match match) {
        if (dto == null) return null;

        return MatchOdds.builder()
                .id(dto.getId())
                .specifier(dto.getSpecifier())
                .odd(dto.getOdd())
                .match(match)
                .build();
    }
}
