package com.shopflix.storefront.services.order.strategies;

import com.shopflix.storefront.data.order.CommerceCheckoutParameter;

public interface CommerceDeliveryModeStrategy
{
    boolean setDeliveryMode(CommerceCheckoutParameter parameter);
}
