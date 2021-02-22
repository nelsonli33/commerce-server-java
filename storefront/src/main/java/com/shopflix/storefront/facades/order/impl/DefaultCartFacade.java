package com.shopflix.storefront.facades.order.impl;

import com.shopflix.core.converters.Converter;
import com.shopflix.storefront.data.order.AddToCartParams;
import com.shopflix.storefront.data.order.CartModificationData;
import com.shopflix.storefront.data.order.CommerceCartModification;
import com.shopflix.storefront.data.order.CommerceCartParameter;
import com.shopflix.storefront.facades.order.CartFacade;
import com.shopflix.storefront.services.order.CommerceCartService;

/**
 * Implementation for {@line CartFacade}. Delivers main functionality for cart.
 */
public class DefaultCartFacade implements CartFacade
{
    private CommerceCartService commerceCartService;
    private Converter<AddToCartParams, CommerceCartParameter> commerceCartParameterConverter;
    private Converter<CommerceCartModification, CartModificationData> cartModificationConverter;

    @Override
    public CartModificationData addToCart(final Long productId, Long variantId, final long quantity)
    {
        final AddToCartParams params = new AddToCartParams();
        params.setProductId(productId);
        params.setVariantId(variantId);
        params.setQty(quantity);

        return addToCart(params);
    }

    public CartModificationData addToCart(final AddToCartParams addToCartParams)
    {
        final CommerceCartParameter parameter = getCommerceCartParameterConverter().convert(addToCartParams);
        final CommerceCartModification modification = getCommerceCartService().addToCart(parameter);

        return getCartModificationConverter().convert(modification);
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
}
