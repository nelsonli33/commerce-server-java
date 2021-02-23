package com.shopflix.merchant.facades.delivery.data;

import java.io.Serializable;

public class DeliveryModeValueData implements Serializable
{
    private static final long serialVersionUID = -5166118819551382204L;

    private Long id;

    private Double minOrderTotal;

    private Double deliveryCost;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Double getMinOrderTotal()
    {
        return minOrderTotal;
    }

    public void setMinOrderTotal(Double minOrderTotal)
    {
        this.minOrderTotal = minOrderTotal;
    }

    public Double getDeliveryCost()
    {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost)
    {
        this.deliveryCost = deliveryCost;
    }
}
