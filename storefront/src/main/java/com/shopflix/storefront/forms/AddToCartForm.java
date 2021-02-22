package com.shopflix.storefront.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddToCartForm
{
    @NotNull(message = "{basket.error.product.notNull}")
    private Long productId;
    private Long variantId;

    @NotNull(message = "{basket.error.quantity.notNull}")
    @Min(value = 1, message = "{basket.error.quantity.invalid}")
    private long qty;

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getVariantId()
    {
        return variantId;
    }

    public void setVariantId(Long variantId)
    {
        this.variantId = variantId;
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
