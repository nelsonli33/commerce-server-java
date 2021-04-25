package com.shopflix.storefront.services.order;

import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.core.model.order.AbstractOrderModel;
import com.shopflix.storefront.facades.order.data.SKUProduct;


public interface AbstractOrderService<O extends AbstractOrderModel, E extends AbstractOrderLineItemModel>
{
    E addNewLineItem(final O order, final SKUProduct skuProduct, final int qty);

    E getLineItemForSKUProduct(final O order, final SKUProduct skuProduct);

    E getLineItemForId(final O order, final Long lineItemId);
}
