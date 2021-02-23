package com.shopflix.storefront.facades.order.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.order.CartLineItemModel;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.storefront.data.order.CommerceCartParameter;
import com.shopflix.storefront.data.order.SKUProduct;
import com.shopflix.storefront.data.order.UpdateCartParams;
import com.shopflix.storefront.services.order.CartService;
import com.shopflix.storefront.services.product.SKUProductFactory;

public class CommerceUpdateCartParameterPopulator implements Populator<UpdateCartParams, CommerceCartParameter>
{
    private CartService cartService;
    private SKUProductFactory skuProductFactory;

    @Override
    public void populate(UpdateCartParams updateCartParams, CommerceCartParameter parameter) throws ConversionException
    {
        final CartModel cart = getCartService().getCartForCurrentCustomer();

        final CartLineItemModel lineItemModel = getCartService().getLineItemForId(cart, updateCartParams.getLineItemId());

        final SKUProduct skuProduct = getSkuProductFactory().createSKUProduct(lineItemModel.getProduct(), lineItemModel.getVariant());

        parameter.setCart(cart);
        parameter.setSkuProduct(skuProduct);
        parameter.setQuantity(updateCartParams.getQty());
        parameter.setLineItemId(updateCartParams.getLineItemId());
    }

    public CartService getCartService()
    {
        return cartService;
    }

    public void setCartService(CartService cartService)
    {
        this.cartService = cartService;
    }

    public SKUProductFactory getSkuProductFactory()
    {
        return skuProductFactory;
    }

    public void setSkuProductFactory(SKUProductFactory skuProductFactory)
    {
        this.skuProductFactory = skuProductFactory;
    }
}
