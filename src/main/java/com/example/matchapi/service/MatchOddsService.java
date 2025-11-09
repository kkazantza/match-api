package com.example.matchapi.service;


import com.example.matchapi.dto.MatchOddsDTO;
import com.example.matchapi.mapper.MatchMapper;
import com.example.matchapi.model.Match;
import com.example.matchapi.model.MatchOdds;
import com.example.matchapi.repository.MatchOddsRepository;
import com.example.matchapi.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchOddsService {

    private final MatchOddsRepository matchOddsRepository;
    private final MatchRepository matchRepository;

    public MatchOddsService(MatchOddsRepository matchOddsRepository, MatchRepository matchRepository) {
        this.matchOddsRepository = matchOddsRepository;
        this.matchRepository = matchRepository;
    }

    public List<MatchOddsDTO> findAll() {
        final var matchOddsEntities = matchOddsRepository.findAll();
        if (!matchOddsEntities.isEmpty()){
            return matchOddsEntities.stream()
                    .map(MatchMapper::toDto)
                    .toList();
        }
        return Collections.emptyList();
    }

    public MatchOddsDTO findById(Long id) {
        return matchOddsRepository.findById(id)
                .map(MatchMapper::toDto)
                .orElseThrow(() -> new RuntimeException("MatchOdds not found"));
    }


    public MatchOddsDTO save(MatchOddsDTO matchOddsDTO, Long matchId) {

        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found"));

        MatchOdds matchOdds = MatchMapper.toEntity(matchOddsDTO, match);

        MatchOdds saved = matchOddsRepository.save(matchOdds);

        return MatchMapper.toDto(saved);
    }

    public void delete(Long id) {
        matchOddsRepository.deleteById(id);
    }

}
