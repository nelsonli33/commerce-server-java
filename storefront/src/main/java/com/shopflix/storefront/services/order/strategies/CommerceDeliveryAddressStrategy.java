package com.shopflix.storefront.services.order.strategies;

import com.shopflix.storefront.facades.order.data.CommerceCheckoutParameter;

public interface CommerceDeliveryAddressStrategy
{
    boolean storeDeliveryAddress(CommerceCheckoutParameter parameter);
}
