package com.shopflix.storefront.data.order;

import java.io.Serializable;
import java.util.List;

public class CartData implements Serializable
{
    private static final long serialVersionUID = 1254026089043700700L;

    private String code;

    private Double deliveryCost;

    private Double subtotal;

    private Double totalDiscounts;

    private Double totalPrice;

    private Integer totalItems;

    private List<OrderLineItemData> items;



    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Double getDeliveryCost()
    {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost)
    {
        this.deliveryCost = deliveryCost;
    }

    public Double getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(Double subtotal)
    {
        this.subtotal = subtotal;
    }

    public Double getTotalDiscounts()
    {
        return totalDiscounts;
    }

    public void setTotalDiscounts(Double totalDiscounts)
    {
        this.totalDiscounts = totalDiscounts;
    }

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalItems()
    {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems)
    {
        this.totalItems = totalItems;
    }

    public List<OrderLineItemData> getItems()
    {
        return items;
    }

    public void setItems(List<OrderLineItemData> items)
    {
        this.items = items;
    }
}
