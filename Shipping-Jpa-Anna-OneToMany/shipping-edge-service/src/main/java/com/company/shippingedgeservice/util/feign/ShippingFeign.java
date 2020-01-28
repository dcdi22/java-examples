package com.company.shippingedgeservice.util.feign;

import com.company.shippingedgeservice.util.model.Invoice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "shipping-service")
public interface ShippingFeign {

    @GetMapping("/invoice/customer/{customerId}")
    List<Invoice> getInvoiceByCustomerId(@PathVariable int customerId);

    @PostMapping("/invoice")
    Invoice addItem(@RequestBody Invoice invoice);

}
