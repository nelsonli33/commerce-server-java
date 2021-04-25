package com.shopflix.storefront.facades.order.data;

import com.shopflix.core.model.order.CartModel;

import java.io.Serializable;

public class CommerceCartParameter implements Serializable {

    private static final long serialVersionUID = -3253577633685150047L;

    private CartModel cart;

    private SKUProduct skuProduct;

    private int quantity;

    private Long lineItemId;



    public CartModel getCart() {
        return cart;
    }

    public void setCart(CartModel cart) {
        this.cart = cart;
    }

    public SKUProduct getSkuProduct() {
        return skuProduct;
    }

    public void setSkuProduct(SKUProduct skuProduct) {
        this.skuProduct = skuProduct;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public Long getLineItemId()
    {
        return lineItemId;
    }

    public void setLineItemId(Long lineItemId)
    {
        this.lineItemId = lineItemId;
    }
}
