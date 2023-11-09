package com.evandorou.oddsports.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

/**
 * The type Match odds.
 */
@Entity
@Data
public class MatchOdds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the odds", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = "specifier")
    @Schema(description = "Name of the Odds Specifier", example = "Win-Lose")
    private String specifier;

    @Column(name = "odds")
    @Schema(description = "Odds of this Specifier", example = "1.90")
    private double odds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    @JsonIgnore
    private Match match;
}
