package com.shopflix.storefront.data.order;

public class UpdateCartParams
{
    private Long lineItemId;

    private long qty;

    public Long getLineItemId()
    {
        return lineItemId;
    }

    public void setLineItemId(Long lineItemId)
    {
        this.lineItemId = lineItemId;
    }

    public long getQty()
    {
        return qty;
    }

    public void setQty(long qty)
    {
        this.qty = qty;
    }
}
