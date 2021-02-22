package com.shopflix.storefront.services.order.strategies;

import com.shopflix.storefront.data.order.CommerceCartModification;
import com.shopflix.storefront.data.order.CommerceCartParameter;
import com.shopflix.storefront.exceptions.CommerceCartModificationException;

/**
 * A strategy interface for adding products to cart.
 */
public interface CommerceAddToCartStrategy {

    CommerceCartModification addToCart(CommerceCartParameter parameter) throws CommerceCartModificationException;

}
