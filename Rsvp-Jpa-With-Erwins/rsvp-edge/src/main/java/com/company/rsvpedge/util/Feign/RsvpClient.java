package com.company.rsvpedge.util.Feign;

import com.company.rsvpedge.models.Rsvp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(name = "rsvp-service")
public interface RsvpClient {
	@RequestMapping(value = "/rsvp", method = RequestMethod.POST)
	Rsvp createRsvp(@RequestBody @Valid Rsvp rsvp);

	@RequestMapping(value = "/rsvp/{id}", method = RequestMethod.GET)
	Rsvp getRsvp(@PathVariable Integer id);
}
