package com.shopflix.storefront.services.order.impl;

import com.shopflix.storefront.data.order.CommerceCheckoutParameter;
import com.shopflix.storefront.services.order.CommerceCheckoutService;
import com.shopflix.storefront.services.order.strategies.CommerceDeliveryModeStrategy;

public class DefaultCommerceCheckoutService implements CommerceCheckoutService
{
    private CommerceDeliveryModeStrategy commerceDeliveryModeStrategy;

    @Override
    public boolean setDeliveryMode(CommerceCheckoutParameter parameter)
    {
        return getCommerceDeliveryModeStrategy().setDeliveryMode(parameter);
    }



    public CommerceDeliveryModeStrategy getCommerceDeliveryModeStrategy()
    {
        return commerceDeliveryModeStrategy;
    }

    public void setCommerceDeliveryModeStrategy(CommerceDeliveryModeStrategy commerceDeliveryModeStrategy)
    {
        this.commerceDeliveryModeStrategy = commerceDeliveryModeStrategy;
    }
}
