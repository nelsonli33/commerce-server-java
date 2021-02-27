package com.shopflix.storefront.facades.order;

import com.shopflix.storefront.facades.order.data.CartData;
import com.shopflix.storefront.facades.order.data.CartModificationData;

public interface CartFacade
{
    CartData getCart();

    CartModificationData addToCart(final Long productId, Long variantId, final long quantity);

    CartModificationData updateCartLineItem(final Long lineItemId, long quantity);

    void clearCart();
}
