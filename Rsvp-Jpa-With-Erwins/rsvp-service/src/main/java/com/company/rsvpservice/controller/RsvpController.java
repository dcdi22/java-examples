package com.company.rsvpservice.controller;

import com.company.rsvpservice.model.Rsvp;
import com.company.rsvpservice.repository.RsvpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rsvp")
public class RsvpController {

    @Autowired
    RsvpRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rsvp createRsvp(@RequestBody Rsvp rsvp) {
        return repo.save(rsvp);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Rsvp> fetchAllRsvps() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rsvp fetchRsvp(@PathVariable Integer id) throws EntityNotFoundException {
        Optional<Rsvp> rsvp = repo.findById(id);
        if (rsvp.isPresent()) {
            return repo.findById(id).get();
        } else {
            throw new EntityNotFoundException("expected to get back a motorcycle");
        }
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRsvp(@RequestBody Rsvp rsvp, @PathVariable Integer id) {
        rsvp.setRsvpId(id);
        repo.save(rsvp);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRsvp(@PathVariable Integer id) {
        repo.deleteById(id);
    }



}
