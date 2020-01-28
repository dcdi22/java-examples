package com.company.shippingservice.repository;

import com.company.shippingservice.model.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceRepositoryTest {

    @Autowired
    private InvoiceRepository repo;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
    }

    @Test
    public void createRepo() {
        Invoice invoice = new Invoice(null, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));

        invoice = repo.save(invoice);

        assertNotNull(invoice);
    }


    @Test
    public void getAllInvoices() {
        Invoice invoice = new Invoice(null, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));
        Invoice invoice2 = new Invoice(null, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));
        Invoice invoice3 = new Invoice(null, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));


        repo.save(invoice);
        repo.save(invoice2);
        repo.save(invoice3);

        assertEquals(3, repo.findAll().size());
    }


    @Test
    public void getSingleInvoice() throws Exception {
        Invoice invoice = new Invoice(null, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));

        invoice = repo.save(invoice);

        Optional<Invoice> result = repo.findById(invoice.getInvoiceId());

        if (result.isPresent()) {
            assertEquals("shipZip", result.get().getShipToZip());
            assertEquals(new BigDecimal("100.00"), result.get().getTotalCost());
        } else {
            throw new Exception("expected to get an invoice");
        }
    }

    @Test
    public void updateInvoice() throws Exception {
        Invoice invoice = new Invoice(null, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));

        invoice = repo.save(invoice);

        assertEquals("shipZip", invoice.getShipToZip());

        invoice.setShipToZip("Hello");

        repo.save(invoice);

        Optional<Invoice> result = repo.findById(invoice.getInvoiceId());

        if (result.isPresent()) {
            assertEquals("Hello", result.get().getShipToZip());
        } else {
            throw new Exception("Expected to get updated result");
        }
    }


    @Test
    public void deleteInvoice() {
        repo.deleteAll();

        assertEquals(0, repo.findAll().size());
    }


}