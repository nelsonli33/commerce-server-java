package com.shopflix.storefront.services.order.strategies.impl;

import com.shopflix.core.model.order.CartModel;
import com.shopflix.storefront.facades.order.data.CommerceCartParameter;
import com.shopflix.storefront.services.order.CalculationService;
import com.shopflix.storefront.services.order.strategies.CommerceCartCalculationStrategy;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;

public class DefaultCommerceCartCalculationStrategy implements CommerceCartCalculationStrategy
{
    private CalculationService calculationService;

    @Override
    public void calculateCart(CommerceCartParameter parameter)
    {
        final CartModel cartModel = parameter.getCart();

        validateParameterNotNull(cartModel, "Cart model cannot be null");

        final CalculationService calcService = getCalculationService();

        calcService.calculate(cartModel);
    }

    public CalculationService getCalculationService()
    {
        return calculationService;
    }

    public void setCalculationService(CalculationService calculationService)
    {
        this.calculationService = calculationService;
    }
}
