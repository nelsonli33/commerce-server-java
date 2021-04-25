package com.shopflix.storefront.facades.order;

import com.shopflix.storefront.facades.order.data.CartData;
import com.shopflix.storefront.facades.order.data.CartModificationData;

public interface CartFacade
{
    CartData getCart();

    CartModificationData addToCart(final Long productId, Long variantId, final int quantity);

    CartModificationData updateCartLineItem(final Long lineItemId, int quantity);

    void clearCart();
}
