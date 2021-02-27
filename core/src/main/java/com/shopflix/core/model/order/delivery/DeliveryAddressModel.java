package com.shopflix.core.model.order.delivery;

import com.shopflix.core.model.order.AbstractOrderModel;
import com.shopflix.core.model.user.AddressModel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("DeliveryAddress")
public class DeliveryAddressModel extends AddressModel
{
    @OneToOne(mappedBy = "deliveryAddress", fetch = FetchType.LAZY)
    private AbstractOrderModel order;

    public AbstractOrderModel getOrder()
    {
        return order;
    }

    public void setOrder(AbstractOrderModel order)
    {
        this.order = order;
    }
}
