package com.shopflix.storefront.services.order.strategies;

import com.shopflix.storefront.facades.order.data.CommerceCartModification;
import com.shopflix.storefront.facades.order.data.CommerceCartParameter;
import com.shopflix.storefront.exceptions.CommerceCartModificationException;

public interface CommerceUpdateCartLineItemStrategy
{
    CommerceCartModification updateQuantityForCartLineItem(CommerceCartParameter parameter)
            throws CommerceCartModificationException;
}
