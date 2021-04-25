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
    protected static final int DEFAULT_FORCE_IN_STOCK_MAX_QUANTITY = 9999;

    protected int forceInStockMaxQuantity = DEFAULT_FORCE_IN_STOCK_MAX_QUANTITY;

    private ModelService modelService;
    private CartService cartService;
    private ModifiableChecker<AbstractOrderLineItemModel> orderLineItemModifiableChecker;
    private CommerceCartCalculationStrategy commerceCartCalculationStrategy;

    protected int getAllowedCartQtyAdjustmentForProduct(final CartModel cartModel, final SKUProduct skuProduct,
                                                      final int quantityToAdd)
    {
        final int cartLevel = checkCartLevel(cartModel, skuProduct);
        final int stockLevel = getAvailableSellingStockLevel(skuProduct);

        // How many will we have in our cart if we add quantity
        final int newTotalQuantity = cartLevel + quantityToAdd;

        // Now limit that to the total available in stock
        final int newTotalQuantityAfterStockLimit = Math.min(newTotalQuantity, stockLevel);

        // So now work out what the maximum allowed to be added
        final Integer maxOrderQuantity = skuProduct.getProduct().getMaxOrderQuantity();

        if (isMaxOrderQuantitySet(maxOrderQuantity))
        {
            final int newTotalQuantityAfterProductMaxOrder = Math
                    .min(newTotalQuantityAfterStockLimit, maxOrderQuantity);

            return newTotalQuantityAfterProductMaxOrder - cartLevel;
        }
        return newTotalQuantityAfterStockLimit - cartLevel;
    }

    protected int checkCartLevel(final CartModel cartModel, final SKUProduct skuProduct)
    {
        int cartLevel = 0;

        CartLineItemModel lineItem = getCartService().getLineItemForSKUProduct(cartModel, skuProduct);

        if (lineItem != null)
        {
            cartLevel += lineItem.getQuantity();
        }

        return cartLevel;
    }


    protected int getAvailableSellingStockLevel(final SKUProduct skuProduct)
    {
        final Integer availableSellingStockLevel;

        availableSellingStockLevel = skuProduct.getQuantity();

        if (availableSellingStockLevel == null)
        {
            return getForceInStockMaxQuantity();
        }
        else
        {
            return availableSellingStockLevel;
        }
    }

    protected boolean isMaxOrderQuantitySet(final Integer maxOrderQuantity) {
        return maxOrderQuantity != null;
    }


    public int getForceInStockMaxQuantity()
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
