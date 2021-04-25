package com.shopflix.storefront.facades.order.data;

public class UpdateCartParams
{
    private Long lineItemId;

    private int qty;

    public Long getLineItemId()
    {
        return lineItemId;
    }

    public void setLineItemId(Long lineItemId)
    {
        this.lineItemId = lineItemId;
    }

    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }
}
