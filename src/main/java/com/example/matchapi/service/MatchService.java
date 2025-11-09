package com.example.matchapi.service;

import com.example.matchapi.dto.MatchDTO;
import com.example.matchapi.mapper.MatchMapper;
import com.example.matchapi.model.Match;
import com.example.matchapi.repository.MatchRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<MatchDTO> findAll() {
        return matchRepository.findAll()
                .stream()
                .map(MatchMapper::toDto)
                .toList();
    }

    public MatchDTO findById(Long id) {
        return matchRepository.findById(id)
                .map(MatchMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Match not found with id " + id));
    }

    public MatchDTO save(MatchDTO matchDTO) {
        Match matchEntity = MatchMapper.toEntity(matchDTO);

        Match savedMatch = matchRepository.save(matchEntity);

        return MatchMapper.toDto(savedMatch);
    }


    public void delete(Long id) {
        matchRepository.deleteById(id);
    }
}
