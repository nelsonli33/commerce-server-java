package com.shopflix.storefront.services.order.strategies;

import com.shopflix.storefront.data.order.CommerceCartModification;
import com.shopflix.storefront.data.order.CommerceCartParameter;
import com.shopflix.storefront.exceptions.CommerceCartModificationException;

public interface CommerceUpdateCartLineItemStrategy
{
    CommerceCartModification updateQuantityForCartLineItem(CommerceCartParameter parameter)
            throws CommerceCartModificationException;
}
