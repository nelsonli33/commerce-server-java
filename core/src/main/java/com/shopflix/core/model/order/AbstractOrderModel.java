package com.shopflix.core.model.order;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shopflix.core.model.ItemModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractOrderModel extends ItemModel {

    private String code;
    private Double deliveryCost;
    private Double subtotal;
    private Double totalPrice;
    private Double totalDiscounts;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<AbstractOrderLineItemModel> lineItems;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalDiscounts() {
        return totalDiscounts;
    }

    public void setTotalDiscounts(Double totalDiscounts) {
        this.totalDiscounts = totalDiscounts;
    }

    public List<AbstractOrderLineItemModel> getLineItems()
    {
        return lineItems;
    }

    public void setLineItems(List<AbstractOrderLineItemModel> lineItems)
    {
        this.lineItems = lineItems;
    }
}
