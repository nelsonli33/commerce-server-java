package com.shopflix.storefront.services.order;

import com.shopflix.storefront.data.order.CommerceCheckoutParameter;

public interface CommerceCheckoutService
{
    /**
     * Sets the given delivery mode on the cart
     * @param parameter - A parameter object for the cart and deliverymode
     * @return true if successful
     */
    boolean setDeliveryMode(CommerceCheckoutParameter parameter);
}
