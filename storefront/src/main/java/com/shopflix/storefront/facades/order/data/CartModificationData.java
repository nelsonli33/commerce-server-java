package com.shopflix.storefront.facades.order.data;

import java.io.Serializable;

public class CartModificationData implements Serializable
{
    private static final long serialVersionUID = 6299083117262881030L;

    private String cartCode;

    private String statusCode;

    private long quantityAdded;

    private long quantity;

    private OrderLineItemData lineItem;



    public String getCartCode()
    {
        return cartCode;
    }

    public void setCartCode(String cartCode)
    {
        this.cartCode = cartCode;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode = statusCode;
    }

    public long getQuantityAdded()
    {
        return quantityAdded;
    }

    public void setQuantityAdded(long quantityAdded)
    {
        this.quantityAdded = quantityAdded;
    }

    public long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(long quantity)
    {
        this.quantity = quantity;
    }

    public OrderLineItemData getLineItem()
    {
        return lineItem;
    }

    public void setLineItem(OrderLineItemData lineItem)
    {
        this.lineItem = lineItem;
    }
}
