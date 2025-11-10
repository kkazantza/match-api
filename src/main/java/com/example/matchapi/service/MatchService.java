package com.example.matchapi.service;

import com.example.matchapi.advice.MatchNotFoundException;
import com.example.matchapi.dto.MatchDTO;
import com.example.matchapi.mapper.MatchMapper;
import com.example.matchapi.model.Match;
import com.example.matchapi.model.Sport;
import com.example.matchapi.repository.MatchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id " + id));
    }

    public MatchDTO save(MatchDTO matchDTO) {
        Match matchEntity = MatchMapper.toEntity(matchDTO);

        Match savedMatch = matchRepository.save(matchEntity);

        return MatchMapper.toDto(savedMatch);
    }


    public MatchDTO update(Long id, MatchDTO matchDTO) {
        Match existing = matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException("Match with ID " + id + " not found"));

        Match updated = Match.builder()
                .id(existing.getId())
                .description(matchDTO.getDescription() != null ? matchDTO.getDescription() : existing.getDescription())
                .matchDate(matchDTO.getMatchDate() != null ? matchDTO.getMatchDate() : existing.getMatchDate())
                .matchTime(matchDTO.getMatchTime() != null ? matchDTO.getMatchTime() : existing.getMatchTime())
                .teamA(matchDTO.getTeamA() != null ? matchDTO.getTeamA() : existing.getTeamA())
                .teamB(matchDTO.getTeamB() != null ? matchDTO.getTeamB() : existing.getTeamB())
                .sport(matchDTO.getSport() != null ? Sport.fromString(matchDTO.getSport()) : existing.getSport())
                .build();

        Match saved = matchRepository.save(updated);
        return MatchMapper.toDto(saved);
    }



    public void delete(Long id) {
        matchRepository.deleteById(id);
    }
}
