package com.company.shippingedgeservice.controller;

import com.company.shippingedgeservice.service.ShippingService;
import com.company.shippingedgeservice.viewmodel.InvoiceViewModel;
import com.company.shippingedgeservice.viewmodel.ItemViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ShippingController.class)
public class ShippingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ShippingService service;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void addItem() throws Exception {
        InvoiceViewModel inputIvm = new InvoiceViewModel(0, 5, "30161", LocalDate.of(2019, 03, 23),
                new BigDecimal("20.00"), new BigDecimal(".072"), new BigDecimal("8.50"));
        ItemViewModel ivm = new ItemViewModel(1, 2, "item name", "item description", 12,
                new BigDecimal("2.00"));

        List<ItemViewModel> items = new ArrayList<>();
        items.add(ivm);
        inputIvm.setItems(items);

        String inputJson = mapper.writeValueAsString(inputIvm);

        InvoiceViewModel expected = new InvoiceViewModel(0, 5, "30161", LocalDate.of(2019, 03, 23),
                new BigDecimal("20.00"), new BigDecimal(".072"), new BigDecimal("8.50"));

        expected.setItems(items);

        String outputJson = mapper.writeValueAsString(expected);

        when(service.addItem(inputIvm)).thenReturn(expected);

        mockMvc.perform(post("/invoice/item")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void getInvoicesByCustomerId() throws Exception {

        InvoiceViewModel inputIvm = new InvoiceViewModel(2, 5, "30161", LocalDate.of(2019, 03, 23),
                new BigDecimal("20.00"), new BigDecimal(".072"), new BigDecimal("8.50"));

        List<InvoiceViewModel> invList = new ArrayList<>();
        invList.add(inputIvm);

        String outputJson = mapper.writeValueAsString(invList);
        when(service.getInvoicesByCustomerId(5)).thenReturn(invList);

        mockMvc.perform(get("/invoice/customer/5"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));

    }
}