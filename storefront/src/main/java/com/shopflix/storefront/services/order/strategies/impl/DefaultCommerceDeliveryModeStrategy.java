package com.shopflix.storefront.services.order.strategies.impl;

import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.core.service.ModelService;
import com.shopflix.storefront.data.order.CommerceCartParameter;
import com.shopflix.storefront.data.order.CommerceCheckoutParameter;
import com.shopflix.storefront.services.order.strategies.CommerceCartCalculationStrategy;
import com.shopflix.storefront.services.order.strategies.CommerceDeliveryModeStrategy;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;

public class DefaultCommerceDeliveryModeStrategy implements CommerceDeliveryModeStrategy
{
    private ModelService modelService;
    private CommerceCartCalculationStrategy commerceCartCalculationStrategy;

    @Override
    public boolean setDeliveryMode(CommerceCheckoutParameter parameter)
    {
        final DeliveryModeModel deliveryMode = parameter.getDeliveryMode();
        final CartModel cart = parameter.getCart();

        validateParameterNotNull(cart, "Cart model cannot be null");
        validateParameterNotNull(deliveryMode, "DeliveryMode model cannot be null");

        cart.setDeliveryMode(deliveryMode);
        getModelService().save(cart);

        final CommerceCartParameter commerceCartParameter = new CommerceCartParameter();
        commerceCartParameter.setCart(cart);
        getCommerceCartCalculationStrategy().calculateCart(commerceCartParameter);

        return true;
    }



    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    public CommerceCartCalculationStrategy getCommerceCartCalculationStrategy()
    {
        return commerceCartCalculationStrategy;
    }

    public void setCommerceCartCalculationStrategy(CommerceCartCalculationStrategy commerceCartCalculationStrategy)
    {
        this.commerceCartCalculationStrategy = commerceCartCalculationStrategy;
    }
}
