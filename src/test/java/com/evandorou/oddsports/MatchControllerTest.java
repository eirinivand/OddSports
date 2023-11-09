package com.evandorou.oddsports;

import com.evandorou.oddsports.model.Match;
import com.evandorou.oddsports.service.MatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MatchService matchService;


    @Test
    public void testCreateMatch() throws Exception {
        Match matchToCreate = new Match();
        matchToCreate.setTeamA("Team A");
        matchToCreate.setTeamB("Team B");
        // ... set other properties as needed

        Match createdMatch = new Match();
        createdMatch.setId(1L); // Assume the ID is set by the service layer when saving
        createdMatch.setTeamA(matchToCreate.getTeamA());
        createdMatch.setTeamB(matchToCreate.getTeamB());
        // ... copy other properties

        given(matchService.createMatch(any(Match.class))).willReturn(createdMatch);

        // Act & Assert
        mockMvc.perform(post("/api/matches")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(matchToCreate)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(createdMatch.getId()))
                .andExpect(jsonPath("$.teamA").value(createdMatch.getTeamA()))
                .andExpect(jsonPath("$.teamB").value(createdMatch.getTeamB()));
        // ... other JSON path assertions

        // Optionally verify that the service method was called
        verify(matchService).createMatch(any(Match.class));
    }

    @Test
    public void testReadMatch() throws Exception {
        Long matchId = 1L;
        Match match = new Match();
        match.setId(matchId);
        match.setTeamA("Team A");
        match.setTeamB("Team B");
        // ... other property setters

        given(matchService.findById(matchId)).willReturn(match);
        // Assume there is a match with ID 1
        mockMvc.perform(get("/api/matches/{id}", matchId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teamA").exists())
                .andExpect(jsonPath("$.teamB").exists());
        // ... assert other properties
    }

    @Test
    public void testUpdateMatch() throws Exception {
        // Assume there is a match with ID 1
        Match updatedMatch = new Match();
        updatedMatch.setTeamA("Team A Updated");
        updatedMatch.setTeamB("Team B Updated");
        // ... set other properties

        mockMvc.perform(put("/api/matches/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedMatch)))
                .andExpect(status().isOk());
        // ... assert other properties
    }

    @Test
    public void testDeleteMatch() throws Exception {
        // Assume there is a match with ID 1
        mockMvc.perform(delete("/api/matches/1"))
                .andExpect(status().isNoContent());
    }
}
