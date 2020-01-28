package com.company.shippingedgeservice.service;

import com.company.shippingedgeservice.util.feign.ShippingFeign;
import com.company.shippingedgeservice.util.model.Invoice;
import com.company.shippingedgeservice.util.model.Item;
import com.company.shippingedgeservice.viewmodel.InvoiceViewModel;
import com.company.shippingedgeservice.viewmodel.ItemViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingService {

    @Autowired
    ShippingFeign sFeign;

    public List<InvoiceViewModel> getInvoicesByCustomerId(int customerId){

        List<Invoice> invoices = sFeign.getInvoiceByCustomerId(customerId);

        List<InvoiceViewModel> ivmList = new ArrayList<>();

        for(Invoice invoice : invoices){
            InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
            ivmList.add(ivm);
        }

        return ivmList;
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoiceId(invoice.getInvoiceId());
        ivm.setCustomerId(invoice.getCustomerId());
        ivm.setShipToZip(invoice.getShipToZip());
        ivm.setPurchaseDate(invoice.getPurchaseDate());
        ivm.setTotalCost(invoice.getTotalCost());
        ivm.setSalesTax(invoice.getSalesTax());
        ivm.setSurcharge(invoice.getSurcharge());

        return ivm;
    }

    public InvoiceViewModel addItem(InvoiceViewModel item){

        Invoice newInvoice = new Invoice();
        newInvoice.setInvoiceId(item.getInvoiceId());
        newInvoice.setCustomerId(item.getCustomerId());
        newInvoice.setShipToZip(item.getShipToZip());
        newInvoice.setPurchaseDate(item.getPurchaseDate());
        newInvoice.setTotalCost(item.getTotalCost());
        newInvoice.setSalesTax(item.getSalesTax());
        newInvoice.setSurcharge(item.getSurcharge());

//            item.getItems().stream().map(
//                    itemViewModel -> new ItemViewModel(itemViewModel.getInvoiceItemId(),
//                            itemViewModel.setInvoiceId(),
//                                    itemViewModel.getItemName(),
//                                    itemViewModel.getItemDescription(),
//                                    itemViewModel.getWeight(),
//                                    itemViewModel.getShipCost()));
//            )

        return item;

    }

}
