package com.shopflix.storefront.services.delivery.impl;

import com.shopflix.core.model.order.AbstractOrderModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.core.repository.delivery.DeliveryModeRepository;
import com.shopflix.storefront.services.delivery.DeliveryService;
import com.shopflix.storefront.services.delivery.strategies.DeliveryModeLookupStrategy;
import com.shopflix.storefront.services.order.strategies.calculation.FindDeliveryCostStrategy;

import java.util.List;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;

public class DefaultDeliveryService implements DeliveryService
{
    private DeliveryModeLookupStrategy deliveryModeLookupStrategy;
    private DeliveryModeRepository deliveryModeRepository;
    private FindDeliveryCostStrategy findDeliveryCostStrategy;

    @Override
    public List<DeliveryModeModel> getSupportedDeliveryModesForOrder(AbstractOrderModel abstractOrder)
    {
        validateParameterNotNull(abstractOrder, "abstractOrder model cannot be null");

        final List<DeliveryModeModel> deliveryModes = getDeliveryModeLookupStrategy().getSelectableDeliveryModesForOrder(abstractOrder);

        // TODO: sortDeliveryModes
        return deliveryModes;
    }

    @Override
    public DeliveryModeModel getDeliveryModeForCode(String code)
    {
        validateParameterNotNull(code, "Parameter code cannot be null");

        return getDeliveryModeRepository().findDeliveryModeModelByCode(code);
    }


    public Double getDeliveryCostForDeliveryModeAndOrder(final DeliveryModeModel deliveryMode, final AbstractOrderModel abstractOrder)
    {
        validateParameterNotNull(deliveryMode, "deliveryMode model cannot be null");
        validateParameterNotNull(abstractOrder, "abstractOrder model cannot be null");

        return getFindDeliveryCostStrategy().getDeliveryCost(deliveryMode, abstractOrder);
    }




    public DeliveryModeLookupStrategy getDeliveryModeLookupStrategy()
    {
        return deliveryModeLookupStrategy;
    }

    public void setDeliveryModeLookupStrategy(DeliveryModeLookupStrategy deliveryModeLookupStrategy)
    {
        this.deliveryModeLookupStrategy = deliveryModeLookupStrategy;
    }

    public DeliveryModeRepository getDeliveryModeRepository()
    {
        return deliveryModeRepository;
    }

    public void setDeliveryModeRepository(DeliveryModeRepository deliveryModeRepository)
    {
        this.deliveryModeRepository = deliveryModeRepository;
    }

    public FindDeliveryCostStrategy getFindDeliveryCostStrategy()
    {
        return findDeliveryCostStrategy;
    }

    public void setFindDeliveryCostStrategy(FindDeliveryCostStrategy findDeliveryCostStrategy)
    {
        this.findDeliveryCostStrategy = findDeliveryCostStrategy;
    }
}
