package com.company.shippingservice.controller;

import com.company.shippingservice.model.Invoice;
import com.company.shippingservice.model.InvoiceItem;
import com.company.shippingservice.repository.InvoiceItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InvoiceItemController.class)
class InvoiceItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    InvoiceItemRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        reset(repo);
    }

    @Test
    void createInvoiceItem() throws Exception {
        Invoice invoice = new Invoice(1, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));

        InvoiceItem in = new InvoiceItem(null, invoice, "IN", "ID", 100, new BigDecimal("10.00"));

        String inputJson = mapper.writeValueAsString(in);

        InvoiceItem out = new InvoiceItem(1, invoice, "IN", "ID", 100, new BigDecimal("10.00"));

        String outputJson = mapper.writeValueAsString(out);

        when(repo.save(in)).thenReturn(out);

        this.mockMvc.perform(post("/invoiceitem")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));

    }

    @Test
    void getAllInvoiceItems() throws Exception {
        InvoiceItem out = new InvoiceItem(1, null, "IN", "ID", 100, new BigDecimal("10.00"));

        List<InvoiceItem> iiList = new ArrayList<>();
        iiList.add(out);

        String outputJson = mapper.writeValueAsString(iiList);

        when(repo.findAll()).thenReturn(iiList);

        this.mockMvc.perform(get("/invoiceitem"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getInvoiceItem() throws Exception {
        InvoiceItem out = new InvoiceItem(1, null, "IN", "ID", 100, new BigDecimal("10.00"));

        String outputJson = mapper.writeValueAsString(out);

        when(repo.findById(out.getInvoiceItemId())).thenReturn(java.util.Optional.of(out));

        this.mockMvc.perform(get("/invoiceitem/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    void updateInvoiceItem() throws Exception {
        InvoiceItem in = new InvoiceItem(1, null, "IN", "ID", 100, new BigDecimal("10.00"));

        String inputJson = mapper.writeValueAsString(in);

        this.mockMvc.perform(put("/invoiceitem/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(inputJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void deleteInvoiceItem() throws Exception {
        this.mockMvc.perform(delete("/invoiceitem/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}