package com.shopflix.storefront.services.order.strategies.impl;

import com.shopflix.core.model.order.CartLineItemModel;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.storefront.facades.order.data.CommerceCartModification;
import com.shopflix.storefront.facades.order.data.CommerceCartModificationStatus;
import com.shopflix.storefront.facades.order.data.CommerceCartParameter;
import com.shopflix.storefront.facades.order.data.SKUProduct;
import com.shopflix.storefront.exceptions.CommerceCartModificationException;
import com.shopflix.storefront.services.order.strategies.CommerceAddToCartStrategy;
import org.springframework.util.CollectionUtils;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;

public class DefaultCommerceAddToCartStrategy extends AbstractCommerceCartStrategy implements CommerceAddToCartStrategy
{

    @Override
    public CommerceCartModification addToCart(CommerceCartParameter parameter) throws CommerceCartModificationException
    {
        final CommerceCartModification modification = doAddToCart(parameter);
        getCommerceCartCalculationStrategy().calculateCart(parameter);
        return modification;
    }

    protected CommerceCartModification doAddToCart(final CommerceCartParameter parameter) throws CommerceCartModificationException
    {
        CommerceCartModification modification;

        final CartModel cartModel = parameter.getCart();
        final SKUProduct skuProduct = parameter.getSkuProduct();
        final int quantityToAdd = parameter.getQuantity();

        validateAddToCart(parameter);

        // So now work out what the maximum allowed to be added is (note that this may be negative!)
        final int actualAllowedQuantityChange = getAllowedCartQtyAdjustmentForProduct(cartModel, skuProduct, quantityToAdd);

        final Integer maxOrderQuantity = skuProduct.getProduct().getMaxOrderQuantity();
        final int cartLevel = checkCartLevel(cartModel, skuProduct);
        final int cartLevelAfterQuantityChange = actualAllowedQuantityChange + cartLevel;

        if (actualAllowedQuantityChange > 0)
        {
            // We are allowed to add items to the cart
            final CartLineItemModel lineItemModel = getCartService().addNewLineItem(cartModel, skuProduct, actualAllowedQuantityChange);
            getModelService().save(lineItemModel);

            final String statusCode = getStatusCodeAllowedQuantityChange(actualAllowedQuantityChange, maxOrderQuantity,
                    quantityToAdd, cartLevelAfterQuantityChange);

            modification = createAddToCartResp(parameter, statusCode, lineItemModel, actualAllowedQuantityChange);
        }
        else
        {
            // Not allowed to add any quantity, or maybe even asked to reduce the quantity
            // Do nothing!
            final String status = getStatusCodeForNotAllowedQuantityChange(maxOrderQuantity, maxOrderQuantity);

            modification = createAddToCartResp(parameter, status, null, 0);

        }

        return modification;
    }

    protected void validateAddToCart(final CommerceCartParameter parameter) throws CommerceCartModificationException
    {
        final CartModel cartModel = parameter.getCart();
        final SKUProduct skuProduct = parameter.getSkuProduct();

        validateParameterNotNull(cartModel, "Cart model cannot be null");
        validateParameterNotNull(skuProduct, "SKUProduct cannot be null");
        validateParameterNotNull(skuProduct.getProduct(), "ProductModel cannot be null");

        if (!CollectionUtils.isEmpty(skuProduct.getProduct().getVariants()) && skuProduct.getVariant() == null)
        {
            throw new CommerceCartModificationException("Need to choose a variant");
        }

        if (parameter.getQuantity() < 1)
        {
            throw new CommerceCartModificationException("Quantity must not be less than one");
        }
    }

    protected String getStatusCodeAllowedQuantityChange(final long actualAllowedQuantityChange, final Integer maxOrderQuantity,
                                                        final long quantityToAdd, final long cartLevelAfterQuantityChange)
    {
        // Are we able to add the quantity we requested?
        if (isMaxOrderQuantitySet(maxOrderQuantity) && (actualAllowedQuantityChange < quantityToAdd)
                && (cartLevelAfterQuantityChange == maxOrderQuantity.longValue()))
        {
            return CommerceCartModificationStatus.MAX_ORDER_QUANTITY_EXCEEDED;
        }
        else if (actualAllowedQuantityChange == quantityToAdd)
        {
            return CommerceCartModificationStatus.SUCCESS;
        }
        else
        {
            return CommerceCartModificationStatus.LOW_STOCK;
        }
    }

    protected String getStatusCodeForNotAllowedQuantityChange(final Integer maxOrderQuantity,
                                                              final Integer cartLevelAfterQuantityChange)
    {

        if (isMaxOrderQuantitySet(maxOrderQuantity) && (cartLevelAfterQuantityChange.longValue() == maxOrderQuantity.longValue()))
        {
            return CommerceCartModificationStatus.MAX_ORDER_QUANTITY_EXCEEDED;
        }
        else
        {
            return CommerceCartModificationStatus.NO_STOCK;
        }
    }

    protected CommerceCartModification createAddToCartResp(final CommerceCartParameter parameter, final String status,
                                                           final CartLineItemModel lineItem, final int quantityAdded)
    {
        final int quantityToAdd = parameter.getQuantity();

        final CommerceCartModification modification = new CommerceCartModification();
        modification.setStatusCode(status);
        modification.setQuantityAdded(quantityAdded);
        modification.setQuantity(quantityToAdd);
        modification.setLineItem(lineItem);
        return modification;
    }


}
