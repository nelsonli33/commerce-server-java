package com.shopflix.storefront.facades.order.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.storefront.data.order.OrderLineItemData;
import com.shopflix.storefront.services.strategies.ModifiableChecker;
import org.springframework.util.Assert;

public class OrderLineItemPopulator implements Populator<AbstractOrderLineItemModel, OrderLineItemData>
{
    ModifiableChecker<AbstractOrderLineItemModel> orderLineItemModelModifiableChecker;

    @Override
    public void populate(AbstractOrderLineItemModel source, OrderLineItemData target) throws ConversionException
    {
        Assert.notNull(source, "Parameter source cannot be null.");
        Assert.notNull(target, "Parameter target cannot be null.");

        addCommon(source, target);
        addProduct(source, target);
        addTotals(source, target);
    }

    protected void addCommon(final AbstractOrderLineItemModel source, final OrderLineItemData target)
    {
        target.setUpdateable(getOrderLineItemModelModifiableChecker().canModify(source));
    }


    protected void addProduct(final AbstractOrderLineItemModel source, final OrderLineItemData target)
    {
        target.setName(source.getName());
        target.setProductId(source.getProductId());
        target.setProductName(source.getProductName());
        target.setVariantId(source.getVariantId());
        target.setVariantName(source.getVariantName());
        target.setPrice(source.getPrice());
        target.setQuantity(source.getQuantity());
    }

    protected void addTotals(final AbstractOrderLineItemModel source, final OrderLineItemData target)
    {
        target.setSubtotal(source.getSubtotal());
        target.setTotalDiscounts(source.getTotalDiscounts());
        target.setTotalPrice(source.getTotalPrice());
    }

    public ModifiableChecker<AbstractOrderLineItemModel> getOrderLineItemModelModifiableChecker()
    {
        return orderLineItemModelModifiableChecker;
    }

    public void setOrderLineItemModelModifiableChecker(ModifiableChecker<AbstractOrderLineItemModel> orderLineItemModelModifiableChecker)
    {
        this.orderLineItemModelModifiableChecker = orderLineItemModelModifiableChecker;
    }
}
