package com.shopflix.storefront.services.order;

import com.shopflix.storefront.facades.order.data.CommerceCheckoutParameter;

public interface CommerceCheckoutService
{
    /**
     * Sets the given delivery mode on the cart
     * @param parameter - A parameter object for the cart and deliverymode
     * @return true if successful
     */
    boolean setDeliveryMode(CommerceCheckoutParameter parameter);

    /**
     * Sets the given address as delivery address on the cart and also marks the address as delivery address. A null
     * address will cause removal of deliveryAddress from the cart.
     * @param parameter A parameter object
     * @return true if successful
     */
    boolean setDeliveryAddress(final CommerceCheckoutParameter parameter);
}
