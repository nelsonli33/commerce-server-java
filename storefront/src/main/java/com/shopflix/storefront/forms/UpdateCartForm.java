package com.shopflix.storefront.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateCartForm
{
    @NotNull(message = "{basket.error.lineItem.notNull}")
    private Long lineItemId;

    @NotNull(message = "{basket.error.quantity.notNull}")
    @Min(value = 0, message = "{basket.error.quantity.invalid}")
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
