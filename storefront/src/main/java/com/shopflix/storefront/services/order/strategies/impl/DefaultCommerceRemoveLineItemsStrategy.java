package com.shopflix.storefront.services.order.strategies.impl;

import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.service.ModelService;
import com.shopflix.storefront.data.order.CommerceCartParameter;
import com.shopflix.storefront.services.order.strategies.CommerceRemoveLineItemsStrategy;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;

public class DefaultCommerceRemoveLineItemsStrategy implements CommerceRemoveLineItemsStrategy
{
    private ModelService modelService;

    @Override
    public void removeAllLineItems(CommerceCartParameter parameter)
    {
        final CartModel cartModel = parameter.getCart();
        validateParameterNotNull(cartModel, "Cart model cannot be null");

        cartModel.getLineItems().clear();
        getModelService().save(cartModel);
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }
}
