package com.shopflix.storefront.services.order.impl;

import com.shopflix.core.model.order.*;
import com.shopflix.storefront.data.order.SKUProduct;
import com.shopflix.storefront.services.order.AbstractOrderService;

import java.util.List;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

public abstract class DefaultAbstractOrderService<O extends AbstractOrderModel, E extends AbstractOrderLineItemModel>
        implements AbstractOrderService<O, E>
{
    @Override
    public E addNewLineItem(O order, SKUProduct skuProduct, long qty)
    {
        validateParameterNotNullStandardMessage("order", order);
        validateParameterNotNullStandardMessage("skuProduct", skuProduct);

        if (qty <= 0)
        {
            throw new IllegalArgumentException("Quantity must be a positive non-zero value");
        }

        AbstractOrderLineItemModel ret = getLineItemForSKUProduct(order, skuProduct);;

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
            if (lineItem != null &&
                    lineItem.getProduct() != null && lineItem.getProduct().equals(skuProduct.getProduct()) &&
                    lineItem.getVariant().equals(skuProduct.getVariant()))
            {
                return (E) lineItem;
            }
        }

        return null;
    }
}
