package com.shopflix.storefront.services.order.impl;

import com.shopflix.storefront.data.order.CommerceCartModification;
import com.shopflix.storefront.data.order.CommerceCartParameter;
import com.shopflix.storefront.exceptions.CommerceCartModificationException;
import com.shopflix.storefront.services.order.CommerceCartService;
import com.shopflix.storefront.services.order.strategies.CommerceAddToCartStrategy;

public class DefaultCommerceCartService implements CommerceCartService {

    private CommerceAddToCartStrategy commerceAddToCartStrategy;

    @Override
    public CommerceCartModification addToCart(CommerceCartParameter parameter) throws CommerceCartModificationException {
        return getCommerceAddToCartStrategy().addToCart(parameter);
    }

    @Override
    public CommerceCartModification updateQuantityForCartLineItem(CommerceCartParameter parameter) throws CommerceCartModificationException {
        return null;
    }

    public CommerceAddToCartStrategy getCommerceAddToCartStrategy()
    {
        return commerceAddToCartStrategy;
    }

    public void setCommerceAddToCartStrategy(CommerceAddToCartStrategy commerceAddToCartStrategy)
    {
        this.commerceAddToCartStrategy = commerceAddToCartStrategy;
    }
}
