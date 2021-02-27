package com.shopflix.storefront.services.order.impl;

import com.shopflix.storefront.facades.order.data.CommerceCartModification;
import com.shopflix.storefront.facades.order.data.CommerceCartParameter;
import com.shopflix.storefront.exceptions.CommerceCartModificationException;
import com.shopflix.storefront.services.order.CommerceCartService;
import com.shopflix.storefront.services.order.strategies.CommerceAddToCartStrategy;
import com.shopflix.storefront.services.order.strategies.CommerceCartCalculationStrategy;
import com.shopflix.storefront.services.order.strategies.CommerceRemoveLineItemsStrategy;
import com.shopflix.storefront.services.order.strategies.CommerceUpdateCartLineItemStrategy;

public class DefaultCommerceCartService implements CommerceCartService {

    private CommerceCartCalculationStrategy commerceCartCalculationStrategy;
    private CommerceAddToCartStrategy commerceAddToCartStrategy;
    private CommerceUpdateCartLineItemStrategy commerceUpdateCartLineItemStrategy;
    private CommerceRemoveLineItemsStrategy commerceRemoveLineItemsStrategy;

    @Override
    public CommerceCartModification addToCart(CommerceCartParameter parameter) throws CommerceCartModificationException {
        return getCommerceAddToCartStrategy().addToCart(parameter);
    }

    @Override
    public CommerceCartModification updateQuantityForCartLineItem(CommerceCartParameter parameter) throws CommerceCartModificationException {
        return getCommerceUpdateCartLineItemStrategy().updateQuantityForCartLineItem(parameter);
    }

    @Override
    public void removeAllLineItems(final CommerceCartParameter parameter) {
        getCommerceRemoveLineItemsStrategy().removeAllLineItems(parameter);
        getCommerceCartCalculationStrategy().calculateCart(parameter);
    }


    public CommerceCartCalculationStrategy getCommerceCartCalculationStrategy()
    {
        return commerceCartCalculationStrategy;
    }

    public void setCommerceCartCalculationStrategy(CommerceCartCalculationStrategy commerceCartCalculationStrategy)
    {
        this.commerceCartCalculationStrategy = commerceCartCalculationStrategy;
    }

    public CommerceAddToCartStrategy getCommerceAddToCartStrategy()
    {
        return commerceAddToCartStrategy;
    }

    public void setCommerceAddToCartStrategy(CommerceAddToCartStrategy commerceAddToCartStrategy)
    {
        this.commerceAddToCartStrategy = commerceAddToCartStrategy;
    }

    public CommerceUpdateCartLineItemStrategy getCommerceUpdateCartLineItemStrategy()
    {
        return commerceUpdateCartLineItemStrategy;
    }

    public void setCommerceUpdateCartLineItemStrategy(CommerceUpdateCartLineItemStrategy commerceUpdateCartLineItemStrategy)
    {
        this.commerceUpdateCartLineItemStrategy = commerceUpdateCartLineItemStrategy;
    }

    public CommerceRemoveLineItemsStrategy getCommerceRemoveLineItemsStrategy()
    {
        return commerceRemoveLineItemsStrategy;
    }

    public void setCommerceRemoveLineItemsStrategy(CommerceRemoveLineItemsStrategy commerceRemoveLineItemsStrategy)
    {
        this.commerceRemoveLineItemsStrategy = commerceRemoveLineItemsStrategy;
    }
}
