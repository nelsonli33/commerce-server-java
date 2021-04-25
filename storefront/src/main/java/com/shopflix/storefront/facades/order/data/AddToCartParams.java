package com.shopflix.storefront.facades.order.data;

import java.io.Serializable;

public class AddToCartParams implements Serializable
{
    private static final long serialVersionUID = -3138450948343863893L;

    private Long productId;

    private Long variantId;

    private int qty;

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

    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }
}
