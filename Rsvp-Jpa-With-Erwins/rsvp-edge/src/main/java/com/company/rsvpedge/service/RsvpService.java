package com.company.rsvpedge.service;

import com.company.rsvpedge.exceptions.WrongNumberOfAttendees;
import com.company.rsvpedge.models.Rsvp;
import com.company.rsvpedge.util.Feign.RsvpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class RsvpService {
//	@Autowired
	private RsvpClient rsvpClient;

	public RsvpService() {
	}

	@Autowired
	public RsvpService(RsvpClient rsvpClient) {
		this.rsvpClient = rsvpClient;
	}

	/**
	 * Call the feign client and create a rsvp
	 * @param rsvp
	 * @return
	 * @throws WrongNumberOfAttendees
	 */
	public Rsvp createRsvp(Rsvp rsvp) throws WrongNumberOfAttendees {
		if(rsvp.getTotalAttending() < 1 || rsvp.getTotalAttending() > 4) {
			throw new WrongNumberOfAttendees();
		}

		return rsvpClient.createRsvp(rsvp);
	}

	public Rsvp getRsvp(Integer id) {
		return rsvpClient.getRsvp(id);
	}
}
