package com.shopflix.storefront.data.order;

import com.shopflix.core.model.order.AbstractOrderLineItemModel;

import java.io.Serializable;

public class CommerceCartModification implements Serializable {

    private static final long serialVersionUID = -8110437157865578623L;

    private String statusCode;

    private long quantity;

    private long quantityAdded;

    private AbstractOrderLineItemModel lineItem;



    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode = statusCode;
    }

    public long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(long quantity)
    {
        this.quantity = quantity;
    }

    public long getQuantityAdded()
    {
        return quantityAdded;
    }

    public void setQuantityAdded(long quantityAdded)
    {
        this.quantityAdded = quantityAdded;
    }

    public AbstractOrderLineItemModel getLineItem()
    {
        return lineItem;
    }

    public void setLineItem(AbstractOrderLineItemModel lineItem)
    {
        this.lineItem = lineItem;
    }
}
