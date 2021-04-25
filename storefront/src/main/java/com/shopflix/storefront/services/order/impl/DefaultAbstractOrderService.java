package com.shopflix.storefront.services.order.impl;

import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.order.*;
import com.shopflix.storefront.facades.order.data.SKUProduct;
import com.shopflix.storefront.services.order.AbstractOrderService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

public abstract class DefaultAbstractOrderService<O extends AbstractOrderModel, E extends AbstractOrderLineItemModel>
        implements AbstractOrderService<O, E>
{
    @Override
    public E addNewLineItem(O order, SKUProduct skuProduct, int qty)
    {
        validateParameterNotNullStandardMessage("order", order);
        validateParameterNotNullStandardMessage("skuProduct", skuProduct);

        if (qty <= 0)
        {
            throw new IllegalArgumentException("Quantity must be a positive non-zero value");
        }

        AbstractOrderLineItemModel ret = getLineItemForSKUProduct(order, skuProduct);
        ;

        if (ret != null && Boolean.FALSE.equals(ret.getGiveAway()))
        {
            ret.setQuantity(ret.getQuantity() + qty);
            return (E) ret;
        }
        else
        {
            if (order instanceof CartModel)
            {
                ret = new CartLineItemModel();
            }
            else if (order instanceof OrderModel)
            {
                ret = new OrderLineItemModel();
            }


            if (ret != null)
            {
                ret.setOrder(order);
                ret.setName(skuProduct.getName());
                ret.setPrice(skuProduct.getPrice());
                ret.setSku(skuProduct.getSku());
                ret.setProduct(skuProduct.getProduct());
                ret.setProductName(skuProduct.getProduct().getName());
                ret.setVariant(skuProduct.getVariant());
                ret.setVariantName(skuProduct.getVariant().getName());
                ret.setGiveAway(skuProduct.getGiveAway());
                ret.setQuantity(qty);
                order.getLineItems().add(ret);
                return (E) ret;
            }
        }

        return null;
    }

    @Override
    public E getLineItemForSKUProduct(O order, SKUProduct skuProduct)
    {
        validateParameterNotNullStandardMessage("order", order);
        validateParameterNotNullStandardMessage("skuProduct", skuProduct);

        List<AbstractOrderLineItemModel> lineItems = order.getLineItems();
        if (lineItems == null || lineItems.isEmpty() || skuProduct.getProduct() == null)
        {
            return null;
        }

        for (final AbstractOrderLineItemModel lineItem : lineItems)
        {
            if (lineItem != null && lineItem.getProductId().equals(skuProduct.getProductId()) &&
                    skuProduct.isVariantType() && lineItem.getVariantId().equals(skuProduct.getVariantId()))
            {
                return (E) lineItem;
            }
        }

        return null;
    }

    @Override
    public E getLineItemForId(O order, Long lineItemId)
    {
        final List<AbstractOrderLineItemModel> lineItems = order.getLineItems();
        if (CollectionUtils.isNotEmpty(lineItems))
        {
            for (final AbstractOrderLineItemModel lineItem : lineItems)
            {
                if (lineItem != null && lineItem.getId().equals(lineItemId)) {
                    return (E) lineItem;
                }
            }
        }

        throw new ModelNotFoundException("Line Item with id "+lineItemId+" requested for the object does not exists in the cart or system");
    }
}
