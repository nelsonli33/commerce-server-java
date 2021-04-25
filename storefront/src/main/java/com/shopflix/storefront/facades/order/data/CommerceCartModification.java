package com.shopflix.storefront.facades.order.data;

import com.shopflix.core.model.order.AbstractOrderLineItemModel;

import java.io.Serializable;

public class CommerceCartModification implements Serializable {

    private static final long serialVersionUID = -8110437157865578623L;

    private String statusCode;

    private int quantity;

    private int quantityAdded;

    private AbstractOrderLineItemModel lineItem;



    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode = statusCode;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getQuantityAdded()
    {
        return quantityAdded;
    }

    public void setQuantityAdded(int quantityAdded)
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
