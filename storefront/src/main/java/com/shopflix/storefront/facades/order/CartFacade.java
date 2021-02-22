package com.shopflix.storefront.facades.order;

import com.shopflix.storefront.data.order.CartModificationData;

public interface CartFacade
{
    CartModificationData addToCart(final Long productId, Long variantId, final long quantity);
}
