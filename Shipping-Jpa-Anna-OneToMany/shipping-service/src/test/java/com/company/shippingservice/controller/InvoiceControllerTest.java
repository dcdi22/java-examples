package com.company.shippingservice.controller;

import com.company.shippingservice.model.Invoice;
import com.company.shippingservice.repository.InvoiceRepository;
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


@WebMvcTest(InvoiceController.class)
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    InvoiceRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        reset(repo);
    }

    @Test
    void createInvoice() throws Exception {
        Invoice in = new Invoice(null, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));

        String inputJson = mapper.writeValueAsString(in);

        Invoice out = new Invoice(1, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));

        String outputJson = mapper.writeValueAsString(out);

        when(repo.save(in)).thenReturn(out);

        this.mockMvc.perform(post("/invoice")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(outputJson))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllInvoices() throws Exception {
        Invoice out = new Invoice(1, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));

        List<Invoice> iList = new ArrayList<>();
        iList.add(out);

        String outputJson = mapper.writeValueAsString(iList);

        when(repo.findAll()).thenReturn(iList);

        this.mockMvc.perform(get("/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getInvoiceReturnWithJson() throws Exception {
        Invoice out = new Invoice(1, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));

        String outputJson = mapper.writeValueAsString(out);

        when(repo.findById(out.getInvoiceId())).thenReturn(java.util.Optional.of(out));

        this.mockMvc.perform(get("/invoice/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

//    @Test
//    void getInvoiceReturnWith404() throws Exception {
//        this.mockMvc.perform(get("/invoice/999"))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }

    @Test
    void updateInvoice() throws Exception {
        Invoice in = new Invoice(1, 1, "shipZip", LocalDate.of(2019, 06, 19), new BigDecimal("100.00"), new BigDecimal("1.50"), new BigDecimal("1.75"));

        String inputJson = mapper.writeValueAsString(in);

        this.mockMvc.perform(put("/invoice/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(inputJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void deleteInvoice() throws Exception {
        this.mockMvc.perform(delete("/invoice/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}