package com.shopflix.storefront.services.order.strategies.impl;

import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.service.ModelService;
import com.shopflix.storefront.facades.order.data.CommerceCartModification;
import com.shopflix.storefront.facades.order.data.CommerceCartModificationStatus;
import com.shopflix.storefront.facades.order.data.CommerceCartParameter;
import com.shopflix.storefront.facades.order.data.SKUProduct;
import com.shopflix.storefront.exceptions.CommerceCartModificationException;
import com.shopflix.storefront.services.order.strategies.CommerceUpdateCartLineItemStrategy;


import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;

public class DefaultCommerceUpdateCartLineItemStrategy extends AbstractCommerceCartStrategy
        implements CommerceUpdateCartLineItemStrategy
{

    @Override
    public CommerceCartModification updateQuantityForCartLineItem(CommerceCartParameter parameter)
            throws CommerceCartModificationException
    {
        final CartModel cartModel = parameter.getCart();
        final int newQuantity = parameter.getQuantity();
        final Long lineItemId = parameter.getLineItemId();


        validateParameterNotNull(cartModel, "Cart model cannot be null");
        validateParameterNotNull(lineItemId, "lineItemId cannot be null");

        final AbstractOrderLineItemModel lineItemToUpdate = getCartService().getLineItemForId(cartModel, lineItemId);
        validateLineItemBeforeModification(newQuantity, lineItemToUpdate);


        final Integer maxOrderQuantity = lineItemToUpdate.getProduct().getMaxOrderQuantity();
        // Work out how many we want to add (could be negative if we are removing items)
        final int quantityToAdd = newQuantity - lineItemToUpdate.getQuantity();

        if (isStockLevelSufficient(cartModel, parameter.getSkuProduct(), quantityToAdd))
        {
            // So now work out what the maximum allowed to be added is (note that
            // this may be negative!)
            final int actualAllowedQuantityChange = getAllowedCartQtyAdjustmentForProduct(cartModel, parameter.getSkuProduct(),
                    quantityToAdd);
            // Now do the actual cartModification
            return modifyLineItem(cartModel, lineItemToUpdate, actualAllowedQuantityChange, newQuantity, maxOrderQuantity);
        }
        else
        {
            throw new CommerceCartModificationException("Insufficient stock level");
        }
    }

    protected void validateLineItemBeforeModification(final long newQuantity, final AbstractOrderLineItemModel lineItemToUpdate)
            throws CommerceCartModificationException
    {
        if (newQuantity < 0)
        {
            throw new CommerceCartModificationException("New quantity must not be less than zero");
        }

        if (lineItemToUpdate == null)
        {
            throw new CommerceCartModificationException("Unknown lineItem id");
        }

        if (!isOrderLineItemUpdatable(lineItemToUpdate))
        {
            throw new CommerceCartModificationException("LineItem is not updatable");
        }
    }

    protected boolean isOrderLineItemUpdatable(final AbstractOrderLineItemModel entryToUpdate)
    {
        return getOrderLineItemModifiableChecker().canModify(entryToUpdate);
    }

    protected CommerceCartModification modifyLineItem(final CartModel cartModel, final AbstractOrderLineItemModel lineItemToUpdate,
                                                      final int actualAllowedQuantityChange, final int newQuantity, final Integer maxOrderQuantity)
    {
        // Now work out how many that leaves us with on this entry
        final int lineItemNewQuantity = lineItemToUpdate.getQuantity() + actualAllowedQuantityChange;

        final ModelService modelService = getModelService();

        if (lineItemNewQuantity <= 0)
        {
            // The allowed new line item quantity is zero or negative
            // just remove the line item
            cartModel.getLineItems().remove(lineItemToUpdate);
            modelService.save(cartModel);

            final CommerceCartParameter parameter = new CommerceCartParameter();
            parameter.setCart(cartModel);
            getCommerceCartCalculationStrategy().calculateCart(parameter);

            // Return an empty modification
            final CommerceCartModification modification = new CommerceCartModification();
            modification.setLineItem(null);
            modification.setQuantity(0);
            // We removed all the quantity from this row
            modification.setQuantityAdded(-lineItemToUpdate.getQuantity());

            if (newQuantity == 0)
            {
                modification.setStatusCode(CommerceCartModificationStatus.SUCCESS);
            }
            else
            {
                modification.setStatusCode(CommerceCartModificationStatus.LOW_STOCK);
            }

            return modification;
        }
        else
        {
            // Adjust the entry quantity to the new value
            lineItemToUpdate.setQuantity(lineItemNewQuantity);
            modelService.save(lineItemToUpdate);
            modelService.refresh(cartModel);
            final CommerceCartParameter parameter = new CommerceCartParameter();
            parameter.setCart(cartModel);
            getCommerceCartCalculationStrategy().calculateCart(parameter);
            modelService.refresh(lineItemToUpdate);

            // Return the modification data
            final CommerceCartModification modification = new CommerceCartModification();
            modification.setQuantityAdded(actualAllowedQuantityChange);
            modification.setLineItem(lineItemToUpdate);
            modification.setQuantity(lineItemNewQuantity);

            if (isMaxOrderQuantitySet(maxOrderQuantity) && lineItemNewQuantity == maxOrderQuantity.longValue())
            {
                modification.setStatusCode(CommerceCartModificationStatus.MAX_ORDER_QUANTITY_EXCEEDED);
            }
            else if (newQuantity == lineItemNewQuantity)
            {
                modification.setStatusCode(CommerceCartModificationStatus.SUCCESS);
            }
            else
            {
                modification.setStatusCode(CommerceCartModificationStatus.LOW_STOCK);
            }

            return modification;
        }
    }

    protected boolean isStockLevelSufficient(final CartModel cartModel, final SKUProduct skuProduct,
                                             final long quantityToAdd)
    {
        final long cartLevel = checkCartLevel(cartModel, skuProduct);
        final long stockLevel = getAvailableSellingStockLevel(skuProduct);

        final long newTotalQuantity = cartLevel + quantityToAdd;
        return newTotalQuantity <= stockLevel;
    }

}
