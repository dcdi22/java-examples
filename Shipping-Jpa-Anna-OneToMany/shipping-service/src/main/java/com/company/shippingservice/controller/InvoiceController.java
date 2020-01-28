package com.company.shippingservice.controller;

import com.company.shippingservice.model.Invoice;
import com.company.shippingservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return repo.save(invoice);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoice(@PathVariable Integer id) throws EntityNotFoundException {
        Optional<Invoice> invoice = repo.findById(id);
        if (invoice.isPresent()) {
            return invoice.get();
        } else {
            throw new EntityNotFoundException("No invoice found");
        }
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateInvoice(@RequestBody Invoice invoice, @PathVariable Integer id) {
        invoice.setInvoiceId(id);
        repo.save(invoice);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoice(@PathVariable Integer id) {
        repo.deleteById(id);
    }

}
