package com.shopflix.storefront.services.order.strategies.impl;

import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.model.order.delivery.DeliveryAddressModel;
import com.shopflix.core.model.user.AddressModel;
import com.shopflix.core.service.ModelService;
import com.shopflix.storefront.facades.order.data.CommerceCheckoutParameter;
import com.shopflix.storefront.services.order.strategies.CommerceDeliveryAddressStrategy;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;

public class DefaultCommerceDeliveryAddressStrategy implements CommerceDeliveryAddressStrategy
{
    private ModelService modelService;

    @Override
    public boolean storeDeliveryAddress(CommerceCheckoutParameter parameter)
    {
        final CartModel cartModel = parameter.getCart();
        final DeliveryAddressModel deliveryAddress = parameter.getDeliveryAddress();

        getModelService().refresh(cartModel);
        validateParameterNotNull(cartModel, "Cart model cannot be null");

        cartModel.setDeliveryAddress(deliveryAddress);
        getModelService().save(cartModel);
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
}
