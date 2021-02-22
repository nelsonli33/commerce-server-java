package com.shopflix.storefront.facades.order.converters.populator;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.storefront.data.order.CartData;
import com.shopflix.storefront.data.order.OrderLineItemData;
import org.springframework.util.Assert;

public class CartPopulator implements Populator<CartModel, CartData>
{
    private Converter<AbstractOrderLineItemModel, OrderLineItemData> orderLineItemConverter;

    @Override
    public void populate(CartModel source, CartData target) throws ConversionException
    {
        Assert.notNull(source, "Parameter source cannot be null.");
        Assert.notNull(target, "Parameter target cannot be null.");


        addCommon(source, target);
        addTotals(source, target);

    }


    protected void addCommon(final CartModel source, final CartData target) {
        target.setCode(source.getCode());
        target.setTotalItems(calcTotalItems(source));

        target.setItems(getOrderLineItemConverter().convertAll(source.getLineItems()));
    }
    protected Integer calcTotalItems(final CartModel source)
    {
        Integer totalItems = 0;
        for (AbstractOrderLineItemModel lineItem : source.getLineItems())
        {
            totalItems += lineItem.getQuantity().intValue();
        }
        return totalItems;
    }

    protected void addTotals(final CartModel source, final CartData target)
    {
        target.setSubtotal(source.getSubtotal());
        target.setTotalDiscounts(source.getTotalDiscounts());
        target.setTotalPrice(source.getTotalPrice());
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
