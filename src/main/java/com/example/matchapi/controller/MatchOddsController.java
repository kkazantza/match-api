package com.example.matchapi.controller;

import com.example.matchapi.dto.MatchOddsDTO;
import com.example.matchapi.service.MatchOddsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/odds")
@Tag(name = "Odds", description = "Operations for match odds")
@Validated
public class MatchOddsController {

    private final MatchOddsService matchOddsService;

    public MatchOddsController(MatchOddsService matchOddsService) {
        this.matchOddsService = matchOddsService;
    }

    @Operation(summary = "Get all match odds")
    @GetMapping
    public List<MatchOddsDTO> getAll() {
        return matchOddsService.findAll();
    }

    @Operation(summary = "Get match odds by ID")
    @GetMapping("/{id}")
    public MatchOddsDTO getById(@PathVariable Long id) {
        return matchOddsService.findById(id);
    }

    @Operation(summary = "Create match odds")
    @PostMapping
    public MatchOddsDTO create(@RequestBody @Valid MatchOddsDTO odds) {
        return matchOddsService.save(odds);
    }

    @Operation(summary = "Update match odds")
    @PutMapping("/{id}")
    public MatchOddsDTO update(@PathVariable Long id, @RequestBody @Valid MatchOddsDTO odds) {
        odds.setId(id);
        return matchOddsService.update(id, odds);
    }

    @Operation(summary = "Delete match odds")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        matchOddsService.delete(id);
    }
}
