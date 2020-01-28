package com.company.rsvpservice.repository;

import com.company.rsvpservice.model.Rsvp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RsvpRepositoryTest {

    @Autowired
    private RsvpRepository repo;

//    private Rsvp rsvp1, rsvp2;

    @BeforeEach
    void setUp() {
        repo.findAll().stream().forEach(rsvp -> {
            repo.delete(rsvp);
        });

        /*
         rsvp_id int not null auto_increment primary key,
    guest_name varchar(50) not null,
    total_attending int not null,
    phone_number varchar(20) not null
         */

//        rsvp1 = new Rsvp(1, "GN", 500, "PN");
//
//        rsvp2 = new Rsvp(2, "GN", 500, "PN");

    }

    @Test
    public void createRsvp() {
        Rsvp rsvp = new Rsvp("GN", 500, "PN");

        rsvp = repo.save(rsvp);

        assertNotNull(rsvp.getRsvpId());
    }

    @Test
    public void getAllRsvps() {
        Rsvp rsvp = new Rsvp("GN", 500, "PN");
        Rsvp rsvp1 = new Rsvp("GN", 500, "PN");
        Rsvp rsvp2 = new Rsvp("GN", 500, "PN");
        Rsvp rsvp3 = new Rsvp("GN", 500, "PN");

        repo.save(rsvp);
        repo.save(rsvp1);
        repo.save(rsvp2);
        repo.save(rsvp3);

        assertEquals(4, repo.findAll().size());
    }

    @Test
    public void getSingleRsvp() throws Exception {
        Rsvp rsvp = new Rsvp("GN", 500, "PN");

        rsvp = repo.save(rsvp);

        Optional<Rsvp> result = repo.findById(rsvp.getRsvpId());

        if (result.isPresent()) {
            assertEquals("PN", result.get().getPhoneNumber());
            assertEquals("GN", result.get().getGuestName());
        } else {
            throw new Exception("expected to get my rsvp");
        }

    }

    @Test
    public void updateRsvp() throws Exception {
        Rsvp rsvp = new Rsvp("GN", 500, "PN");

        rsvp = repo.save(rsvp);

        // should be name GN
        assertEquals("GN", rsvp.getGuestName());

        rsvp.setGuestName("Guest Name");

        repo.save(rsvp);

        // fetch the same rsvp
        Optional<Rsvp> result = repo.findById(rsvp.getRsvpId());

        if(result.isPresent()) {
            assertEquals("Guest Name", result.get().getGuestName());
        } else {
            throw new Exception("Expected new guest name");
        }
    }

    @Test
    public void deleteRsvp() {
        repo.findAll().stream().forEach(rsvp -> {
            repo.delete(rsvp);
        });

        assertEquals(0, repo.findAll().size());
    }






}