package com.shopflix.storefront.services.order.impl;

import com.shopflix.storefront.facades.order.data.CommerceCheckoutParameter;
import com.shopflix.storefront.services.order.CommerceCheckoutService;
import com.shopflix.storefront.services.order.strategies.CommerceDeliveryAddressStrategy;
import com.shopflix.storefront.services.order.strategies.CommerceDeliveryModeStrategy;

public class DefaultCommerceCheckoutService implements CommerceCheckoutService
{
    private CommerceDeliveryModeStrategy commerceDeliveryModeStrategy;
    private CommerceDeliveryAddressStrategy commerceDeliveryAddressStrategy;

    @Override
    public boolean setDeliveryMode(CommerceCheckoutParameter parameter)
    {
        return getCommerceDeliveryModeStrategy().setDeliveryMode(parameter);
    }

    @Override
    public boolean setDeliveryAddress(CommerceCheckoutParameter parameter)
    {
        return getCommerceDeliveryAddressStrategy().storeDeliveryAddress(parameter);
    }


    public CommerceDeliveryModeStrategy getCommerceDeliveryModeStrategy()
    {
        return commerceDeliveryModeStrategy;
    }

    public void setCommerceDeliveryModeStrategy(CommerceDeliveryModeStrategy commerceDeliveryModeStrategy)
    {
        this.commerceDeliveryModeStrategy = commerceDeliveryModeStrategy;
    }

    public CommerceDeliveryAddressStrategy getCommerceDeliveryAddressStrategy()
    {
        return commerceDeliveryAddressStrategy;
    }

    public void setCommerceDeliveryAddressStrategy(CommerceDeliveryAddressStrategy commerceDeliveryAddressStrategy)
    {
        this.commerceDeliveryAddressStrategy = commerceDeliveryAddressStrategy;
    }
}
