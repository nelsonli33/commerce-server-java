package com.shopflix.storefront.facades.order.converters.populator;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.storefront.facades.order.data.CartModificationData;
import com.shopflix.storefront.facades.order.data.CommerceCartModification;
import com.shopflix.storefront.facades.order.data.OrderLineItemData;
import org.springframework.util.Assert;

public class CartModificationPopulator implements Populator<CommerceCartModification, CartModificationData>
{
    private Converter<AbstractOrderLineItemModel, OrderLineItemData> orderLineItemConverter;

    @Override
    public void populate(CommerceCartModification source, CartModificationData target) throws ConversionException
    {
        Assert.notNull(source, "Parameter source cannot be null.");
        Assert.notNull(target, "Parameter target cannot be null.");

        if (source.getLineItem() != null)
        {
            target.setLineItem(getOrderLineItemConverter().convert(source.getLineItem()));

            if (source.getLineItem().getOrder() != null)
            {
                target.setCartCode(source.getLineItem().getOrder().getCode());
            }
        }
        target.setStatusCode(source.getStatusCode());
        target.setQuantity(source.getQuantity());
        target.setQuantityAdded(source.getQuantityAdded());
    }

    public Converter<AbstractOrderLineItemModel, OrderLineItemData> getOrderLineItemConverter()
    {
        return orderLineItemConverter;
    }

    public void setOrderLineItemConverter(Converter<AbstractOrderLineItemModel, OrderLineItemData> orderLineItemConverter)
    {
        this.orderLineItemConverter = orderLineItemConverter;
    }
}
