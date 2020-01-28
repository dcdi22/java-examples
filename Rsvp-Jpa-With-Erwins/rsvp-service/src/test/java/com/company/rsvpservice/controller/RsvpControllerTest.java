package com.company.rsvpservice.controller;

import com.company.rsvpservice.model.Rsvp;
import com.company.rsvpservice.repository.RsvpRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RsvpController.class)
class RsvpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RsvpRepository repo;

    private ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        reset(repo);
    }


    @Test
    void createRsvp() throws Exception {
        Rsvp in = new Rsvp(null, "GN", 500, "PN");

        String inputJson = mapper.writeValueAsString(in);

        Rsvp out = new Rsvp(1, "GN", 500, "PN");

        String outputJson = mapper.writeValueAsString(out);

        when(repo.save(in)).thenReturn(out);

        this.mockMvc.perform(post("/rsvp")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));

    }

    @Test
    void fetchAllRsvps() throws Exception {
        Rsvp out = new Rsvp(1, "GN", 500, "PN");

        List<Rsvp> rsvpList = new ArrayList<>();
        rsvpList.add(out);

        String outputJson = mapper.writeValueAsString(rsvpList);

        when(repo.findAll()).thenReturn(rsvpList);

        this.mockMvc.perform(get("/rsvp"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void fetchRsvpReturnWithJson() throws Exception {
        Rsvp out = new Rsvp(1, "GN", 500, "PN");

        String outputJson = mapper.writeValueAsString(out);

        when(repo.findById(1)).thenReturn(java.util.Optional.of(out));

        this.mockMvc.perform(get("/rsvp/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    void fetchRsvpReturnWith404() throws Exception {
        this.mockMvc.perform(get("/rsvp/999"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void updateRsvp() throws Exception {
        Rsvp incomingRsvp = new Rsvp(1, "Guest Name", 500, "PN");

        String inputJson = mapper.writeValueAsString(incomingRsvp);

        this.mockMvc.perform(put("/rsvp/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(inputJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));

    }

    @Test
    void deleteRsvp() throws Exception {
        this.mockMvc.perform(delete("/rsvp/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}