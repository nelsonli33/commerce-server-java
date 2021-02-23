package com.shopflix.storefront.services.order.strategies;

import com.shopflix.storefront.data.order.CommerceCartParameter;

public interface CommerceRemoveLineItemsStrategy
{
    void removeAllLineItems(final CommerceCartParameter parameter);
}
