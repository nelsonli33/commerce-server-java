package com.shopflix.storefront.facades.order.data;

import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.model.order.delivery.DeliveryAddressModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;

import java.io.Serializable;

public class CommerceCheckoutParameter implements Serializable
{
    private static final long serialVersionUID = -6459939354906604967L;

    private CartModel cart;

    private DeliveryModeModel deliveryMode;

    private DeliveryAddressModel deliveryAddress;



    public CartModel getCart()
    {
        return cart;
    }

    public void setCart(CartModel cart)
    {
        this.cart = cart;
    }

    public DeliveryModeModel getDeliveryMode()
    {
        return deliveryMode;
    }

    public void setDeliveryMode(DeliveryModeModel deliveryMode)
    {
        this.deliveryMode = deliveryMode;
    }

    public DeliveryAddressModel getDeliveryAddress()
    {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddressModel deliveryAddress)
    {
        this.deliveryAddress = deliveryAddress;
    }
}
