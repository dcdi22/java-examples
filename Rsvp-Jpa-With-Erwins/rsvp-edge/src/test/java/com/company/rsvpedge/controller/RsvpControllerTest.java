package com.company.rsvpedge.controller;

import com.company.rsvpedge.exceptions.WrongNumberOfAttendees;
import com.company.rsvpedge.models.Rsvp;
import com.company.rsvpedge.service.RsvpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RsvpController.class)
class RsvpControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	RsvpService rsvpService;

	@Test
	void provided_valid_data_it_can_persist_rsvp() throws Exception {
		Rsvp rsvp = new Rsvp();
		rsvp.setGuestName("guest name");
		rsvp.setPhoneNumber("222222222");
		rsvp.setTotalAttending(45);
		String inputJson = objectMapper.writeValueAsString(rsvp);

		Rsvp rsvp2 = new Rsvp();
		rsvp2.setRsvpId(1);
		rsvp2.setGuestName("guest name");
		rsvp2.setPhoneNumber("222222222");
		rsvp2.setTotalAttending(45);
		String outputJson = objectMapper.writeValueAsString(rsvp2);

		when(rsvpService.createRsvp(rsvp)).thenReturn(rsvp2);

		mockMvc.perform(
			post("/rsvp").content(inputJson).contentType(MediaType.APPLICATION_JSON)
		).andDo(print()).andExpect(status().isCreated()).andExpect(content().json(outputJson));

		verify(rsvpService, times(1)).createRsvp(rsvp);
	}

	@Test
	void it_throws_an_error_if_attendees_count_is_incorrect() throws Exception {
		Rsvp rsvp = new Rsvp();
		rsvp.setGuestName("guest name");
		rsvp.setPhoneNumber("222222222");
		rsvp.setTotalAttending(2);
		String inputJson = objectMapper.writeValueAsString(rsvp);

		doThrow(WrongNumberOfAttendees.class).when(rsvpService).createRsvp(rsvp);

		mockMvc.perform(
			post("/rsvp").content(inputJson).contentType(MediaType.APPLICATION_JSON)
		).andDo(print()).andExpect(status().isBadRequest());
	}

}