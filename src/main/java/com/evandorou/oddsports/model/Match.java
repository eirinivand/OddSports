package com.evandorou.oddsports.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * The type Match.
 */
@Entity
@Data
@NoArgsConstructor
@Schema(description = "Represents a match between two teams")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the match", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = "description")
    @Schema(description = "Description of the match", example = "NBA Playoffs Game 4")
    private String description;

    @Column(name = "match_date")
    @Schema(description = "Date of the match")
    private LocalDate matchDate;

    @Column(name = "match_time")
    @Schema(description = "Time of the match", example = "15:30:00")
    private LocalTime matchTime;

    @Column(name = "team_a")
    @Schema(description = "The first team of the match", example = "Team A")
    private String teamA;

    @Column(name = "team_b")
    @Schema(description = "The second team of the match", example = "Team B")
    private String teamB;

    @Column(name = "sport")
    @Schema(description = "Sport", exampleClasses = Sport.class)
    private Sport sport;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "Match Odds", exampleClasses = MatchOdds.class)
    private List<MatchOdds> odds;

    /**
     * Instantiates a new Match.
     *
     * @param description the description
     * @param matchDate   the match date
     * @param matchTime   the match time
     * @param teamA       the team a
     * @param teamB       the team b
     * @param sport       the sport
     * @param odds        the odds
     */
    public Match(String description, Date matchDate, String matchTime, String teamA, String teamB, Sport sport, List<MatchOdds> odds) {
        this.description = description;
        this.matchDate = LocalDate.from(matchDate.toInstant().atZone(TimeZone.getDefault().toZoneId()));
        this.matchTime = LocalTime.parse(matchTime);
        this.teamA = teamA;
        this.teamB = teamB;
        this.sport = sport;
        this.odds = odds;
    }

    /**
     * Instantiates a new Match.
     *
     * @param description the description
     * @param matchDate   the match date
     * @param matchTime   the match time
     * @param teamA       the team a
     * @param teamB       the team b
     * @param sport       the sport
     */
    public Match(String description, Date matchDate, String matchTime, String teamA, String teamB, Sport sport) {
        this.description = description;
        this.matchDate = LocalDate.from(matchDate.toInstant().atZone(TimeZone.getDefault().toZoneId()));
        this.matchTime = LocalTime.parse(matchTime);
        this.teamA = teamA;
        this.teamB = teamB;
        this.sport = sport;
        this.odds = new ArrayList<>();
    }


    /**
     * Sets odds.
     *
     * @param odds the odds
     */
    public void setOdds(List<MatchOdds> odds) {
        this.odds = odds;
        if (odds != null) {
            odds.forEach(odd -> odd.setMatch(this));
        }
    }

    /**
     * Add odds.
     *
     * @param odd the odd
     */
    public void addOdds(MatchOdds odd) {
        this.odds.add(odd);
        odd.setMatch(this);
    }

    /**
     * Remove odds.
     *
     * @param odd the odd
     */
    public void removeOdds(MatchOdds odd) {
        this.odds.remove(odd);
        odd.setMatch(null);
    }
}
