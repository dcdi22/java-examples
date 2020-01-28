package com.company.rsvpedge.controller;

import com.company.rsvpedge.exceptions.WrongNumberOfAttendees;
import com.company.rsvpedge.models.Rsvp;
import com.company.rsvpedge.service.RsvpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rsvp")
public class RsvpController {
	@Autowired
	RsvpService rsvpService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Rsvp create(@RequestBody @Valid Rsvp rsvp) throws WrongNumberOfAttendees {
		return rsvpService.createRsvp(rsvp);
	}
}
