package com.shopflix.storefront.facades.order;

import com.shopflix.storefront.data.order.CartData;
import com.shopflix.storefront.data.order.CartModificationData;

public interface CartFacade
{
    CartData getCart();

    CartModificationData addToCart(final Long productId, Long variantId, final long quantity);

    CartModificationData updateCartLineItem(final Long lineItemId, long quantity);

    void clearCart();
}
