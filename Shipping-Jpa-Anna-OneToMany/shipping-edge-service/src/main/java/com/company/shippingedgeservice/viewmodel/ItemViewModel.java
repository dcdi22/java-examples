package com.company.shippingedgeservice.viewmodel;

import com.company.shippingedgeservice.util.model.Item;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemViewModel {
    private int invoiceItemId;
    private int invoiceId;
    private String itemName;
    private String itemDescription;
    private int weight;
    private BigDecimal shipCost;

    public ItemViewModel(int invoiceItemId, int invoiceId, String itemName, String itemDescription, int weight, BigDecimal shipCost) {
        this.invoiceItemId = invoiceItemId;
        this.invoiceId = invoiceId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.weight = weight;
        this.shipCost = shipCost;
    }

    public ItemViewModel() {
    }

    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(int invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public BigDecimal getShipCost() {
        return shipCost;
    }

    public void setShipCost(BigDecimal shipCost) {
        this.shipCost = shipCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemViewModel that = (ItemViewModel) o;
        return invoiceItemId == that.invoiceItemId &&
                invoiceId == that.invoiceId &&
                weight == that.weight &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(itemDescription, that.itemDescription) &&
                Objects.equals(shipCost, that.shipCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceItemId, invoiceId, itemName, itemDescription, weight, shipCost);
    }
}
