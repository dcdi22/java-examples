package com.company.shippingservice.controller;

import com.company.shippingservice.model.InvoiceItem;
import com.company.shippingservice.repository.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoiceitem")
public class InvoiceItemController {

    @Autowired
    private InvoiceItemRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItem createInvoiceItem(@RequestBody InvoiceItem invoiceItem) {
        return repo.save(invoiceItem);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceItem> getAllInvoiceItems() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceItem getInvoiceItem(@PathVariable Integer id) throws EntityNotFoundException {
        Optional<InvoiceItem> invoiceItem = repo.findById(id);
        if (invoiceItem.isPresent()) {
            return invoiceItem.get();
        } else {
            throw new EntityNotFoundException("no invoice item found");
        }
    }


    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateInvoiceItem(@RequestBody InvoiceItem invoiceItem, @PathVariable Integer id ) {
        invoiceItem.setInvoiceItemId(id);
        repo.save(invoiceItem);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInvoiceItem(@PathVariable Integer id) {
        repo.deleteById(id);
    }




}
