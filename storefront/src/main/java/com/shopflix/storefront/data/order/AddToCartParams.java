package com.shopflix.storefront.data.order;

import java.io.Serializable;

public class AddToCartParams implements Serializable
{
    private static final long serialVersionUID = -3138450948343863893L;

    private Long productId;

    private Long variantId;

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
