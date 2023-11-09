package com.evandorou.oddsports;

import com.evandorou.oddsports.model.Match;
import com.evandorou.oddsports.service.MatchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MatchServiceTest {

    @Autowired
    private MatchService matchService;

    @Test
    public void whenValidId_thenMatchShouldBeFound() {
        Optional<Match> match = matchService.getMatchById(1L);
        assertTrue(match.isPresent());
        assertEquals("Champions League Final", match.get().getDescription());
    }


    @Test
    public void findAllMatches_ShouldReturnAllMatches() {
        // Act
        List<Match> matches = matchService.getAllMatches();

        // Assert
        assertNotNull(matches);

        // As in data.sql
        assertEquals(6, matches.size());
    }
}
