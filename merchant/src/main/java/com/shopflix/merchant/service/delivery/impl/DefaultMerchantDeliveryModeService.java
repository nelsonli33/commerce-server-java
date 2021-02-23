package com.shopflix.merchant.service.delivery.impl;

import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.core.model.order.delivery.DeliveryModeValueModel;
import com.shopflix.core.repository.delivery.DeliveryModeRepository;
import com.shopflix.merchant.service.delivery.MerchantDeliveryModeService;

import java.util.List;
import java.util.Optional;

public class DefaultMerchantDeliveryModeService implements MerchantDeliveryModeService
{
    private DeliveryModeRepository deliveryModeRepository;

    @Override
    public List<DeliveryModeModel> getAllDeliveryModes()
    {
        return deliveryModeRepository.findAll();
    }

    @Override
    public DeliveryModeModel getDeliveryModeForId(Long id)
    {
        final Optional<DeliveryModeModel> deliveryModeModel = deliveryModeRepository.findById(id);
        if (deliveryModeModel.isPresent()) {
            return deliveryModeModel.get();
        }
        throw new ModelNotFoundException("Delivery Mode with id "+id+" requested for the object does not exists in the system");
    }

    @Override
    public DeliveryModeModel save(DeliveryModeModel model)
    {
        return getDeliveryModeRepository().save(model);
    }

    @Override
    public void removeDeliveryMode(DeliveryModeModel model)
    {
        getDeliveryModeRepository().delete(model);
    }

    @Override
    public DeliveryModeValueModel getDeliveryModeValueForId(DeliveryModeModel mode, Long modeValueId)
    {
        for(DeliveryModeValueModel valueModel : mode.getTierConditions()) {
            if (valueModel.getId().equals(modeValueId)) {
                return valueModel;
            }
        }
        throw new ModelNotFoundException("Delivery Mode Value with id "+modeValueId+" requested for the object does not " +
                "exists in the Delivery Mode with id " + mode.getId() + " or system.");
    }

    public DeliveryModeRepository getDeliveryModeRepository()
    {
        return deliveryModeRepository;
    }

    public void setDeliveryModeRepository(DeliveryModeRepository deliveryModeRepository)
    {
        this.deliveryModeRepository = deliveryModeRepository;
    }
}
