package com.shopflix.storefront.services.order;

import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.core.model.order.AbstractOrderModel;
import com.shopflix.storefront.data.order.SKUProduct;


public interface AbstractOrderService<O extends AbstractOrderModel, E extends AbstractOrderLineItemModel>
{
    E addNewLineItem(final O order, final SKUProduct skuProduct, final long qty);

    E getLineItemForSKUProduct(final O order, final SKUProduct skuProduct);

    E getLineItemForId(final O order, final Long lineItemId);
}
