package com.example.matchapi.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate matchDate;
    private LocalTime matchTime;
    private String teamA;
    private String teamB;

    @Enumerated(EnumType.STRING)
    private Sport sport;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchOdds> odds;
}
