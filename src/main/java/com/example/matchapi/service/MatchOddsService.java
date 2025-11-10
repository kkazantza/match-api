package com.example.matchapi.service;


import com.example.matchapi.advice.MatchOddsNotFoundException;
import com.example.matchapi.dto.MatchOddsDTO;
import com.example.matchapi.mapper.MatchMapper;
import com.example.matchapi.model.Match;
import com.example.matchapi.model.MatchOdds;
import com.example.matchapi.repository.MatchOddsRepository;
import com.example.matchapi.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchOddsService {

    private final MatchOddsRepository matchOddsRepository;
    private final MatchRepository matchRepository;

    public MatchOddsService(MatchOddsRepository matchOddsRepository, MatchRepository matchRepository) {
        this.matchOddsRepository = matchOddsRepository;
        this.matchRepository = matchRepository;
    }

    public List<MatchOddsDTO> findAll() {
        return matchOddsRepository.findAll().stream()
                .map(MatchMapper::toDto)
                .toList();
    }

    public MatchOddsDTO findById(Long id) {
        return matchOddsRepository.findById(id)
                .map(MatchMapper::toDto)
                .orElseThrow(() -> new MatchOddsNotFoundException("MatchOdds not found"));
    }


    public MatchOddsDTO save(MatchOddsDTO matchOddsDTO) {

        Match match = matchRepository.findById(matchOddsDTO.getMatchId())
                .orElseThrow(() -> new MatchOddsNotFoundException("Match not found"));

        MatchOdds matchOdds = MatchMapper.toEntity(matchOddsDTO, match);

        MatchOdds saved = matchOddsRepository.save(matchOdds);

        return MatchMapper.toDto(saved);
    }

    public MatchOddsDTO update(Long id, MatchOddsDTO dto) {
        MatchOdds existing = matchOddsRepository.findById(id)
                .orElseThrow(() -> new MatchOddsNotFoundException("MatchOdds not found with id " + id));

        Match match = existing.getMatch();
        if (dto.getMatchId() != null) {
            match = matchRepository.findById(dto.getMatchId())
                    .orElseThrow(() -> new MatchOddsNotFoundException("Match not found with id " + dto.getMatchId()));
        }

        // Build updated entity
        MatchOdds updated = MatchOdds.builder()
                .id(existing.getId())
                .match(match)
                .specifier(dto.getSpecifier() != null ? dto.getSpecifier() : existing.getSpecifier())
                .odd(dto.getOdd() != null ? dto.getOdd() : existing.getOdd())
                .build();

        MatchOdds saved = matchOddsRepository.save(updated);
        return MatchMapper.toDto(saved);
    }


    public void delete(Long id) {
        matchOddsRepository.deleteById(id);
    }

}
