package com.company.rsvpedge.controller;

import com.company.rsvpedge.exceptions.WrongNumberOfAttendees;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(WrongNumberOfAttendees.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleWrongNumberOfAttendees(WrongNumberOfAttendees e) {
		return "Whoops, there it is";
	}
}
