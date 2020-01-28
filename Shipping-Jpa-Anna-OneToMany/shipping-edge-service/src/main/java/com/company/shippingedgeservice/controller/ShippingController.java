package com.company.shippingedgeservice.controller;

import com.company.shippingedgeservice.service.ShippingService;
import com.company.shippingedgeservice.viewmodel.InvoiceViewModel;
import com.company.shippingedgeservice.viewmodel.ItemViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class ShippingController {

    @Autowired
    ShippingService service;

    @PostMapping("/item")
    public InvoiceViewModel addItem(@RequestBody InvoiceViewModel item){

        return service.addItem(item);
    }

    @GetMapping("/customer/{customerId}")
    public List<InvoiceViewModel> getInvoicesByCustomerId(@PathVariable int customerId){

        return service.getInvoicesByCustomerId(customerId);
    }

}
