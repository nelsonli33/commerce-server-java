package com.shopflix.storefront.facades.order.impl;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.storefront.data.order.CommerceCheckoutParameter;
import com.shopflix.storefront.data.order.DeliveryModeData;
import com.shopflix.storefront.facades.order.CheckoutFacade;
import com.shopflix.storefront.services.delivery.DeliveryService;
import com.shopflix.storefront.services.order.CartService;
import com.shopflix.storefront.services.order.CommerceCheckoutService;
import com.shopflix.storefront.services.order.strategies.CommerceDeliveryModeStrategy;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

public class DefaultCheckoutFacade implements CheckoutFacade
{
    private CartService cartService;
    private CommerceCheckoutService commerceCheckoutService;
    private DeliveryService deliveryService;
    private Converter<DeliveryModeModel, DeliveryModeData> deliveryModeConverter;

    @Override
    public List<DeliveryModeData> getSupportedDeliveryModes()
    {
        final List<DeliveryModeData> result = new ArrayList<>();
        final CartModel cart = getCart();
        if (cart != null)
        {
            for (final DeliveryModeModel deliveryModeModel : getDeliveryService().getSupportedDeliveryModesForOrder(cart))
            {
                result.add(convert(deliveryModeModel));
            }
        }

        return result;
    }

    @Override
    public boolean setDeliveryMode(String deliveryModeCode)
    {
        validateParameterNotNullStandardMessage("deliveryModeCode", deliveryModeCode);

        final CartModel cart = getCart();
        if (cart != null && isSupportedDeliveryMode(deliveryModeCode, cart))
        {
            final DeliveryModeModel deliveryMode = getDeliveryService().getDeliveryModeForCode(deliveryModeCode);

            if (deliveryMode != null)
            {
                CommerceCheckoutParameter parameter = createCommerceCheckoutParameter(cart);
                parameter.setDeliveryMode(deliveryMode);
                return getCommerceCheckoutService().setDeliveryMode(parameter);
            }
        }
        return false;
    }


    protected boolean isSupportedDeliveryMode(final String deliveryModeCode, final CartModel cartModel)
    {
        for (final DeliveryModeModel supportedDeliveryMode : getDeliveryService().getSupportedDeliveryModesForOrder(cartModel))
        {
            if (deliveryModeCode.equalsIgnoreCase(supportedDeliveryMode.getCode()))
            {
                return true;
            }
        }
        return false;
    }


    protected CartModel getCart()
    {
        return getCartService().getCartForCurrentCustomer();
    }


    protected DeliveryModeData convert(final DeliveryModeModel deliveryModeModel)
    {

        final DeliveryModeData deliveryModeData = getDeliveryModeConverter().convert(deliveryModeModel);

        final CartModel cart = getCart();

        final Double deliveryCost = getDeliveryService().getDeliveryCostForDeliveryModeAndOrder(deliveryModeModel, cart);

        deliveryModeData.setDeliveryCost(deliveryCost);

        return deliveryModeData;
    }


    protected CommerceCheckoutParameter createCommerceCheckoutParameter(final CartModel cart)
    {
        final CommerceCheckoutParameter parameter = new CommerceCheckoutParameter();
        parameter.setCart(cart);
        return parameter;
    }

    public CartService getCartService()
    {
        return cartService;
    }

    public void setCartService(CartService cartService)
    {
        this.cartService = cartService;
    }

    public CommerceCheckoutService getCommerceCheckoutService()
    {
        return commerceCheckoutService;
    }

    @Required
    public void setCommerceCheckoutService(CommerceCheckoutService commerceCheckoutService)
    {
        this.commerceCheckoutService = commerceCheckoutService;
    }

    public DeliveryService getDeliveryService()
    {
        return deliveryService;
    }

    public void setDeliveryService(DeliveryService deliveryService)
    {
        this.deliveryService = deliveryService;
    }

    public Converter<DeliveryModeModel, DeliveryModeData> getDeliveryModeConverter()
    {
        return deliveryModeConverter;
    }

    public void setDeliveryModeConverter(Converter<DeliveryModeModel, DeliveryModeData> deliveryModeConverter)
    {
        this.deliveryModeConverter = deliveryModeConverter;
    }
}
