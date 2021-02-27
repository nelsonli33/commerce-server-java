package com.shopflix.storefront.facades.order.impl;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.storefront.facades.order.CartFacade;
import com.shopflix.storefront.facades.order.data.*;
import com.shopflix.storefront.services.order.CartService;
import com.shopflix.storefront.services.order.CommerceCartService;

/**
 * Implementation for {@line CartFacade}. Delivers main functionality for cart.
 */
public class DefaultCartFacade implements CartFacade
{
    private CartService cartService;
    private CommerceCartService commerceCartService;
    private Converter<CartModel, CartData> cartConverter;
    private Converter<AddToCartParams, CommerceCartParameter> commerceCartParameterConverter;
    private Converter<UpdateCartParams, CommerceCartParameter> commerceUpdateCartParameterConverter;

    private Converter<CommerceCartModification, CartModificationData> cartModificationConverter;


    public CartData getCart() {
        final CartModel cartModel = getCartService().getCartForCurrentCustomer();
        return getCartConverter().convert(cartModel);
    }

    @Override
    public CartModificationData addToCart(final Long productId, Long variantId, final long quantity)
    {
        final AddToCartParams params = new AddToCartParams();
        params.setProductId(productId);
        params.setVariantId(variantId);
        params.setQty(quantity);

        return addToCart(params);
    }

    protected CartModificationData addToCart(final AddToCartParams addToCartParams)
    {
        final CommerceCartParameter parameter = getCommerceCartParameterConverter().convert(addToCartParams);
        final CommerceCartModification modification = getCommerceCartService().addToCart(parameter);

        return getCartModificationConverter().convert(modification);
    }

    @Override
    public CartModificationData updateCartLineItem(Long lineItemId, long quantity)
    {
        UpdateCartParams params = new UpdateCartParams();
        params.setLineItemId(lineItemId);
        params.setQty(quantity);

        final CommerceCartParameter parameter = getCommerceUpdateCartParameterConverter().convert(params);
        final CommerceCartModification modification = getCommerceCartService().updateQuantityForCartLineItem(parameter);

        return getCartModificationConverter().convert(modification);
    }

    @Override
    public void clearCart()
    {
        final CommerceCartParameter parameter = new CommerceCartParameter();
        parameter.setCart(getCartService().getCartForCurrentCustomer());

        getCommerceCartService().removeAllLineItems(parameter);
    }


    public CartService getCartService()
    {
        return cartService;
    }

    public void setCartService(CartService cartService)
    {
        this.cartService = cartService;
    }

    public Converter<CartModel, CartData> getCartConverter()
    {
        return cartConverter;
    }

    public void setCartConverter(Converter<CartModel, CartData> cartConverter)
    {
        this.cartConverter = cartConverter;
    }

    public CommerceCartService getCommerceCartService()
    {
        return commerceCartService;
    }

    public void setCommerceCartService(CommerceCartService commerceCartService)
    {
        this.commerceCartService = commerceCartService;
    }

    public Converter<AddToCartParams, CommerceCartParameter> getCommerceCartParameterConverter()
    {
        return commerceCartParameterConverter;
    }

    public void setCommerceCartParameterConverter(Converter<AddToCartParams, CommerceCartParameter> commerceCartParameterConverter)
    {
        this.commerceCartParameterConverter = commerceCartParameterConverter;
    }

    public Converter<CommerceCartModification, CartModificationData> getCartModificationConverter()
    {
        return cartModificationConverter;
    }

    public void setCartModificationConverter(Converter<CommerceCartModification, CartModificationData> cartModificationConverter)
    {
        this.cartModificationConverter = cartModificationConverter;
    }

    public Converter<UpdateCartParams, CommerceCartParameter> getCommerceUpdateCartParameterConverter()
    {
        return commerceUpdateCartParameterConverter;
    }

    public void setCommerceUpdateCartParameterConverter(Converter<UpdateCartParams, CommerceCartParameter> commerceUpdateCartParameterConverter)
    {
        this.commerceUpdateCartParameterConverter = commerceUpdateCartParameterConverter;
    }
}
