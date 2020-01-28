package com.company.rsvpedge.service;

import com.company.rsvpedge.exceptions.WrongNumberOfAttendees;
import com.company.rsvpedge.models.Rsvp;
import com.company.rsvpedge.util.Feign.RsvpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RsvpServiceTest {
	@MockBean
	RsvpClient rsvpClient;

	@Autowired
	RsvpService rsvpService;

	@Test
	void it_throws_an_error_if_total_attendee_less_than_one() {
		Rsvp rsvp = new Rsvp();
		rsvp.setGuestName("guest name");
		rsvp.setPhoneNumber("222222222");
		rsvp.setTotalAttending(-3);
		System.out.println(rsvpService);
		assertThrows(WrongNumberOfAttendees.class, () -> {
			rsvpService.createRsvp(rsvp);
		});
	}

	@Test
	void it_throws_an_error_if_total_attendee_greater_than_four() {
		Rsvp rsvp = new Rsvp();
		rsvp.setGuestName("guest name");
		rsvp.setPhoneNumber("222222222");
		rsvp.setTotalAttending(5);
		System.out.println(rsvpService);
		assertThrows(WrongNumberOfAttendees.class, () -> {
			rsvpService.createRsvp(rsvp);
		});
	}

	@Test
	void it_returns_an_rsvp_with_valid_params() {
		Rsvp rsvp = new Rsvp();
		rsvp.setGuestName("guest name");
		rsvp.setPhoneNumber("222222222");
		rsvp.setTotalAttending(5);

		Rsvp rsvp2 = new Rsvp();
		rsvp2.setRsvpId(2);
		rsvp2.setGuestName("guest name");
		rsvp2.setPhoneNumber("222222222");
		rsvp2.setTotalAttending(5);

		when(rsvpClient.createRsvp(rsvp)).thenReturn(rsvp2);
		when(rsvpClient.getRsvp(2)).thenReturn(rsvp2);

		verify(rsvpClient, times(1)).createRsvp(rsvp);
		verify(rsvpClient, times(1)).getRsvp(2);

		assertEquals(rsvp2, rsvpClient.getRsvp(2));
	}
}