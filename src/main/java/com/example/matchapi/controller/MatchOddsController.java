package com.example.matchapi.controller;

import com.example.matchapi.dto.MatchOddsDTO;
import com.example.matchapi.service.MatchOddsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matchOdds")
public class MatchOddsController {

    private final MatchOddsService matchOddsService;

    public MatchOddsController(MatchOddsService matchOddsService) {
        this.matchOddsService = matchOddsService;
    }

    @GetMapping
    public List<MatchOddsDTO> getAll() {
        return matchOddsService.findAll();
    }

    @GetMapping("/{id}")
    public MatchOddsDTO getById(@PathVariable Long id) {
        return matchOddsService.findById(id);
    }

    @PostMapping
    public MatchOddsDTO create(@RequestBody MatchOddsDTO odds) {
        return matchOddsService.save(odds, odds.getMatchId());
    }

    @PutMapping("/{id}")
    public MatchOddsDTO update(@PathVariable Long id, @RequestBody MatchOddsDTO odds) {
        odds.setId(id);
        return matchOddsService.save(odds, odds.getMatchId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        matchOddsService.delete(id);
    }
}
