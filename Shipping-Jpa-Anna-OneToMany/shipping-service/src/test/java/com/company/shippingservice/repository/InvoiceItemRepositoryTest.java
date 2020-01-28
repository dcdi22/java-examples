package com.company.shippingservice.repository;

import com.company.shippingservice.model.Invoice;
import com.company.shippingservice.model.InvoiceItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceItemRepositoryTest {

    @Autowired
    private InvoiceItemRepository repo;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
    }

    @Test
    public void createInvoiceItem() {
        Invoice invoice = new Invoice(1, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));

        InvoiceItem invoiceItem = new InvoiceItem(null, invoice, "IN", "ID", 100, new BigDecimal("10.00"));

        invoiceItem = repo.save(invoiceItem);

        assertNotNull(invoiceItem);

    }


    @Test
    public void getAllInvoiceItems() {
        Invoice invoice = new Invoice(1, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));

        InvoiceItem invoiceItem = new InvoiceItem(null, invoice, "IN", "ID", 100, new BigDecimal("10.00"));

        InvoiceItem invoiceItem2 = new InvoiceItem(null, invoice, "IN", "ID", 100, new BigDecimal("10.00"));
        InvoiceItem invoiceItem3 = new InvoiceItem(null, invoice, "IN", "ID", 100, new BigDecimal("10.00"));

        repo.save(invoiceItem);
        repo.save(invoiceItem2);
        repo.save(invoiceItem3);

        assertEquals(3, repo.findAll().size());
    }


    @Test
    public void getSingleInvoiceItem() throws Exception {
        Invoice invoice = new Invoice(1, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));

        InvoiceItem invoiceItem = new InvoiceItem(null, invoice, "IN", "ID", 100, new BigDecimal("10.00"));

        invoiceItem = repo.save(invoiceItem);

        Optional<InvoiceItem> result = repo.findById(invoiceItem.getInvoiceItemId());

        if (result.isPresent()) {
            assertEquals("IN", result.get().getItemName());
            assertEquals("ID", result.get().getItemDescription());
        } else {
            throw new Exception("excpected in an invoice item");
        }
    }

    @Test
    public void updateInvoiceItem () throws Exception {
        Invoice invoice = new Invoice(1, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));

        InvoiceItem invoiceItem = new InvoiceItem(null, invoice, "IN", "ID", 100, new BigDecimal("10.00"));

        repo.save(invoiceItem);

        assertEquals("IN", invoiceItem.getItemName());

        invoiceItem.setItemName("Item Name");

        repo.save(invoiceItem);

        Optional<InvoiceItem> result = repo.findById(invoiceItem.getInvoiceItemId());

        if (result.isPresent()) {
            assertEquals("Item Name", invoiceItem.getItemName());
        } else {
            throw new Exception("expected an updated invoice item");
        }
    }


    @Test
    public void deleteInvoiceItem() {
        repo.deleteAll();

        assertEquals(0, repo.findAll().size());
    }











}