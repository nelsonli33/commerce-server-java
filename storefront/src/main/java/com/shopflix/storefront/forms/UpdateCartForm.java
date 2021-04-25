package com.shopflix.storefront.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateCartForm
{
    @NotNull(message = "{basket.error.lineItem.notNull}")
    private Long lineItemId;

    @NotNull(message = "{basket.error.quantity.notNull}")
    @Min(value = 0, message = "{basket.error.quantity.invalid}")
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
