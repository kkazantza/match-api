package com.example.matchapi.controller;

import com.example.matchapi.dto.MatchDTO;
import com.example.matchapi.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public List<MatchDTO> getAll() {
        return matchService.findAll();
    }

    @GetMapping("/{id}")
    public MatchDTO getById(@PathVariable Long id) {
        return matchService.findById(id);
    }

    @PostMapping
    public MatchDTO create(@RequestBody MatchDTO match) {
        return matchService.save(match);
    }

    @PutMapping("/{id}")
    public MatchDTO update(@PathVariable Long id, @RequestBody MatchDTO match) {
        match.setId(id);
        return matchService.save(match);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        matchService.delete(id);
    }
}
