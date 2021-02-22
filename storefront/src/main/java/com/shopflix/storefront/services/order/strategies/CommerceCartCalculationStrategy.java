package com.shopflix.storefront.services.order.strategies;

import com.shopflix.storefront.data.order.CommerceCartParameter;

/**
 * Default strategy to calculate the cart when not in checkout status.
 */
public interface CommerceCartCalculationStrategy
{
    void calculateCart(CommerceCartParameter parameter);
}
