package com.company.rsvpedge.exceptions;

public class WrongNumberOfAttendees extends Exception {
	public WrongNumberOfAttendees() {
		super("Attendees must be greater than 0 and less than 5");
	}
}
