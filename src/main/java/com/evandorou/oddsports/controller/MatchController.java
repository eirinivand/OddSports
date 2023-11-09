package com.evandorou.oddsports.controller;

import com.evandorou.oddsports.model.Match;
import com.evandorou.oddsports.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


/**
 * The type Match controller.
 */
@RestController
@RequestMapping("/api/matches")
@Tag(name = "Match Controller", description = "Controller for Match API")
public class MatchController {

    private final MatchService matchService;

    /**
     * Instantiates a new Match controller.
     *
     * @param matchService the match service
     */
    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }


    /**
     * Retrieve all matches
     *
     * @return a list of matches
     */
    @GetMapping
    @Operation(summary = "Get all matches", description = "Retrieve a list of all matches",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of matches",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Match.class))})})
    public ResponseEntity<List<Match>> getAllMatches() {
        // Retrieve and all matches
        // 200 (OK)
        List<Match> match = matchService.findAll();
        return ResponseEntity.ok(match);
    }

    /**
     * Retrieve match by id
     *
     * @param id the id
     * @return a match
     */
    @Operation(summary = "Get a match by ID",
            description = "Returns a single match",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Match found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Match.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Match not found",
                            content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(
            @Parameter(description = "ID of the match to be obtained", required = true)
            @PathVariable Long id) {
        // Retrieve and a match
        // 200 (OK)
        Match match = matchService.findById(id);
        return ResponseEntity.ok(match);
    }

    /**
     * Create a new match and return it
     *
     * @param match the match
     * @return response entity with the created match in the body
     */
    @Operation(summary = "Create a match",
            description = "Creates a new match",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Match created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Match.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "409",
                            description = "Match already exists",
                            content = @Content)
            })
    @PostMapping
    public ResponseEntity<Match> createMatch(
            @Parameter(description = "Match to add", required = true,
                    schema = @Schema(implementation = Match.class))
            @RequestBody Match match) {
        // Create a new match and return it
        // 201 (Created)
        Match createdMatch = matchService.createMatch(match);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdMatch.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdMatch);

    }

    /**
     * Update an existing match and return it
     *
     * @param id    the id
     * @param match the match
     * @return the response entity
     */
    @Operation(summary = "Update a match",
            description = "Update the details of an existing match",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully updated the match",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Match.class))}),
                    @ApiResponse(responseCode = "404", description = "Match not found",
                            content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(
            @Parameter(description = "ID of the match to be updated", required = true) @PathVariable Long id,
            @Parameter(description = "Updated match object", required = true) @RequestBody Match match) {
        // Update an existing match and return it
        // 200 (OK)
        Match updatedMatch = matchService.update(id, match);
        return ResponseEntity.ok(updatedMatch);
    }

    /**
     * Delete match response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "Delete a match", description = "Delete a match by its ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted the match",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Match not found",
                            content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(
            @Parameter(description = "ID of the match to be deleted", required = true) @PathVariable Long id) {
        // Delete a match
        // 204 (No Content)
        matchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}