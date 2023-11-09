package com.evandorou.oddsports.service;

import com.evandorou.oddsports.exception.MatchNotFoundException;
import com.evandorou.oddsports.model.Match;
import com.evandorou.oddsports.repository.MatchRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private static final Logger LOG = LoggerFactory.getLogger(MatchService.class);

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Transactional
    public Match createMatch(Match match) {
        // Perform business logic and validation if needed
        LOG.info("Creating match: {}", match);
        return matchRepository.save(match);
    }

    @Transactional
    public List<Match> getAllMatches() {
        // Implement logic to fetch all matches
        return matchRepository.findAll();
    }

    @Transactional
    public Optional<Match> getMatchById(Long id) {
        // Implement logic to fetch all matches
        return matchRepository.findById(id);
    }

    @Transactional
    public Match update(Long id, Match match) {
        // Ensure the ID in the request body matches the path variable ID
        match.setId(id);
        // Ensure the match with that ID exists
        matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException(id));
        return matchRepository.save(match);
    }

    public void delete(Long id) {

        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException(id));
        matchRepository.delete(match);
    }

    public Match findById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException(id));
    }

    public List<Match> findAll() {
        return matchRepository.findAll();
    }
}
