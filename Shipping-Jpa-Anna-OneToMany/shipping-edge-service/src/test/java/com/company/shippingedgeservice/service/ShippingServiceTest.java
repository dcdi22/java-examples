package com.company.shippingedgeservice.service;

import com.company.shippingedgeservice.util.feign.ShippingFeign;
import com.company.shippingedgeservice.util.model.Invoice;
import com.company.shippingedgeservice.util.model.Item;
import com.company.shippingedgeservice.viewmodel.InvoiceViewModel;
import com.company.shippingedgeservice.viewmodel.ItemViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ShippingServiceTest {

    @InjectMocks
    ShippingService service;

    @Mock
    ShippingFeign sFeign;

    static Item item = new Item(1, 2, "item name", "item description", 12,
            new BigDecimal("2.00"));

    static Invoice inv = new Invoice(2, 5, "30161", LocalDate.of(2019, 03, 23),
            new BigDecimal("20.00"), new BigDecimal(".072"), new BigDecimal("8.50"));

    @Before
    public void setUp() throws Exception {
        setUpFeignClientMock();
    }


    @Test
    public void getInvoicesByCustomerId() {
        Invoice newInv = inv;

        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoiceId(newInv.getInvoiceId());
        ivm.setCustomerId(newInv.getCustomerId());
        ivm.setShipToZip(newInv.getShipToZip());
        ivm.setPurchaseDate(newInv.getPurchaseDate());
        ivm.setTotalCost(newInv.getTotalCost());
        ivm.setSalesTax(newInv.getSalesTax());
        ivm.setSurcharge(newInv.getSurcharge());
        List<InvoiceViewModel> invoices = service.getInvoicesByCustomerId(ivm.getCustomerId());

        assertEquals(1, invoices.size());

    }

    @Test
    public void addItem() {
        Invoice newInv = inv;
        Item newItem = item;

        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setCustomerId(newInv.getCustomerId());
        ivm.setShipToZip(newInv.getShipToZip());
        ivm.setPurchaseDate(newInv.getPurchaseDate());
        ivm.setTotalCost(newInv.getTotalCost());
        ivm.setSalesTax(newInv.getSalesTax());
        ivm.setSurcharge(newInv.getSurcharge());

        ItemViewModel itemVm = new ItemViewModel();
        itemVm.setInvoiceId(newItem.getInvoiceId());
        itemVm.setItemName(newItem.getItemName());
        itemVm.setItemDescription(newItem.getItemDescription());
        itemVm.setWeight(newItem.getWeight());
        itemVm.setShipCost(newItem.getShipCost());

        List<ItemViewModel> items = new ArrayList<>();
        items.add(itemVm);

        ivm.setItems(items);
        service.addItem(ivm);
        ivm.setInvoiceId(newInv.getInvoiceId());

        assertNotNull(ivm);

    }


    private void setUpFeignClientMock() {
        Item newItem = new Item(1, 2, "item name", "item description", 12,
                new BigDecimal("2.00"));

        Invoice newInv = new Invoice(2, 5, "30161", LocalDate.of(2019, 03, 23),
                new BigDecimal("20.00"), new BigDecimal(".072"), new BigDecimal("8.50"));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(newInv);

        doReturn(invoiceList).when(sFeign).getInvoiceByCustomerId(5);

    }
}