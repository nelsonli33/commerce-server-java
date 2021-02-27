package com.shopflix.storefront.services.order.strategies;

import com.shopflix.storefront.facades.order.data.CommerceCartParameter;

public interface CommerceRemoveLineItemsStrategy
{
    void removeAllLineItems(final CommerceCartParameter parameter);
}
