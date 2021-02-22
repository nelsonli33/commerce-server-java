package com.shopflix.storefront.services.strategies.impl;

import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.storefront.services.strategies.ModifiableChecker;
import org.apache.commons.lang3.BooleanUtils;

public class OrderLineItemModifiableChecker implements ModifiableChecker<AbstractOrderLineItemModel>
{
    @Override
    public boolean canModify(AbstractOrderLineItemModel given)
    {
        return BooleanUtils.isNotTrue(given.getGiveAway());
    }
}
