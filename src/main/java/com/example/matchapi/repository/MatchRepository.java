package com.example.matchapi.repository;

import com.example.matchapi.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> { }
