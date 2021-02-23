package com.shopflix.core.model.order.delivery;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shopflix.core.model.ItemModel;

import javax.persistence.*;

@Entity
@Table(name = "deliverymodevalues")
public class DeliveryModeValueModel extends ItemModel
{
    private Double minOrderTotal;
    private Double deliveryCost;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private DeliveryModeModel deliveryMode;

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

    public DeliveryModeModel getDeliveryMode()
    {
        return deliveryMode;
    }

    public void setDeliveryMode(DeliveryModeModel deliveryMode)
    {
        this.deliveryMode = deliveryMode;
    }
}
