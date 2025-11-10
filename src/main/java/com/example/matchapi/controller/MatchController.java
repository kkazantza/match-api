package com.example.matchapi.controller;

import com.example.matchapi.dto.MatchDTO;
import com.example.matchapi.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@Tag(name = "Matches", description = "Operations for matches")
@Validated
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @Operation(summary = "Get all matches")
    @GetMapping
    public List<MatchDTO> getAll() {
        return matchService.findAll();
    }

    @Operation(summary = "Get match by ID")
    @GetMapping("/{id}")
    public MatchDTO getById(@PathVariable Long id) {
        return matchService.findById(id);
    }

    @Operation(summary = "Create a new match")
    @PostMapping
    public MatchDTO create(@RequestBody @Valid MatchDTO match) {
        return matchService.save(match);
    }

    @Operation(summary = "Update a match")
    @PutMapping("/{id}")
    public MatchDTO update(@PathVariable Long id, @RequestBody @Valid MatchDTO match) {
        return matchService.update(id, match);
    }

    @Operation(summary = "Delete a match")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        matchService.delete(id);
    }
}
