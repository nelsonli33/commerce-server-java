package com.shopflix.storefront.services.order.strategies.impl;

import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.core.model.order.CartLineItemModel;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.service.ModelService;
import com.shopflix.storefront.facades.order.data.SKUProduct;
import com.shopflix.storefront.services.order.CartService;
import com.shopflix.storefront.services.order.strategies.CommerceCartCalculationStrategy;
import com.shopflix.storefront.services.strategies.ModifiableChecker;

import javax.annotation.Resource;

public abstract class AbstractCommerceCartStrategy
{
    protected static final long DEFAULT_FORCE_IN_STOCK_MAX_QUANTITY = 9999;

    protected long forceInStockMaxQuantity = DEFAULT_FORCE_IN_STOCK_MAX_QUANTITY;

    private ModelService modelService;
    private CartService cartService;
    private ModifiableChecker<AbstractOrderLineItemModel> orderLineItemModifiableChecker;
    private CommerceCartCalculationStrategy commerceCartCalculationStrategy;

    protected long getAllowedCartQtyAdjustmentForProduct(final CartModel cartModel, final SKUProduct skuProduct,
                                                      final long quantityToAdd)
    {
        final long cartLevel = checkCartLevel(cartModel, skuProduct);
        final long stockLevel = getAvailableStockLevel(skuProduct);

        // How many will we have in our cart if we add quantity
        final long newTotalQuantity = cartLevel + quantityToAdd;

        // Now limit that to the total available in stock
        final long newTotalQuantityAfterStockLimit = Math.min(newTotalQuantity, stockLevel);

        // So now work out what the maximum allowed to be added is (note that
        // this may be negative!)
        final Integer maxOrderQuantity = skuProduct.getProduct().getMaxOrderQuantity();

        if (isMaxOrderQuantitySet(maxOrderQuantity))
        {
            final long newTotalQuantityAfterProductMaxOrder = Math
                    .min(newTotalQuantityAfterStockLimit, maxOrderQuantity.longValue());

            return newTotalQuantityAfterProductMaxOrder - cartLevel;
        }
        return newTotalQuantityAfterStockLimit - cartLevel;
    }

    protected long checkCartLevel(final CartModel cartModel, final SKUProduct skuProduct)
    {
        long cartLevel = 0;

        CartLineItemModel lineItem = getCartService().getLineItemForSKUProduct(cartModel, skuProduct);

        if (lineItem != null)
        {
            cartLevel += lineItem.getQuantity();
        }

        return cartLevel;
    }


    protected long getAvailableStockLevel(final SKUProduct skuProduct)
    {
        final Long availableStockLevel;

        availableStockLevel = skuProduct.getQuantity();

        if (availableStockLevel == null)
        {
            return getForceInStockMaxQuantity();
        }
        else
        {
            return availableStockLevel;
        }
    }

    protected boolean isMaxOrderQuantitySet(final Integer maxOrderQuantity) {
        return maxOrderQuantity != null;
    }


    public long getForceInStockMaxQuantity()
    {
        return forceInStockMaxQuantity;
    }

    public CartService getCartService()
    {
        return cartService;
    }

    @Resource(name = "cartService")
    public void setCartService(CartService cartService)
    {
        this.cartService = cartService;
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    @Resource(name = "modelService")
    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    public ModifiableChecker<AbstractOrderLineItemModel> getOrderLineItemModifiableChecker()
    {
        return orderLineItemModifiableChecker;
    }

    @Resource(name = "orderLineItemModifiableChecker")
    public void setOrderLineItemModifiableChecker(ModifiableChecker<AbstractOrderLineItemModel> orderLineItemModifiableChecker)
    {
        this.orderLineItemModifiableChecker = orderLineItemModifiableChecker;
    }

    public CommerceCartCalculationStrategy getCommerceCartCalculationStrategy()
    {
        return commerceCartCalculationStrategy;
    }

    @Resource(name = "commerceCartCalculationStrategy")
    public void setCommerceCartCalculationStrategy(CommerceCartCalculationStrategy commerceCartCalculationStrategy)
    {
        this.commerceCartCalculationStrategy = commerceCartCalculationStrategy;
    }
}
