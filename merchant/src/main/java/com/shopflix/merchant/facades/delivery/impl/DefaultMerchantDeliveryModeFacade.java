package com.shopflix.merchant.facades.delivery.impl;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.enums.DeliveryModeSubType;
import com.shopflix.core.enums.DeliveryModeType;
import com.shopflix.core.enums.TemperatureType;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.core.model.order.delivery.DeliveryModeValueModel;
import com.shopflix.merchant.facades.delivery.MerchantDeliveryModeFacade;
import com.shopflix.merchant.facades.delivery.data.DeliveryModeData;
import com.shopflix.merchant.facades.delivery.data.DeliveryModeValueData;
import com.shopflix.merchant.service.delivery.MerchantDeliveryModeService;


import java.util.ArrayList;
import java.util.List;

public class DefaultMerchantDeliveryModeFacade implements MerchantDeliveryModeFacade
{
    private MerchantDeliveryModeService merchantDeliveryModeService;
    private Converter<DeliveryModeModel, DeliveryModeData> merchantDeliveryModeConverter;

    @Override
    public List<DeliveryModeData> getAllDeliveryModes()
    {
        final List<DeliveryModeModel> allDeliveryModes = getMerchantDeliveryModeService().getAllDeliveryModes();
        return getMerchantDeliveryModeConverter().convertAll(allDeliveryModes);
    }

    @Override
    public DeliveryModeData getDeliveryModeForId(Long id)
    {
        final DeliveryModeModel deliveryModeModel = getMerchantDeliveryModeService().getDeliveryModeForId(id);
        return getMerchantDeliveryModeConverter().convert(deliveryModeModel);
    }

    @Override
    public DeliveryModeData postDeliveryMode(DeliveryModeData deliveryModeData)
    {
        DeliveryModeModel model = new DeliveryModeModel();
        model.setCode(deliveryModeData.getCode());
        model.setName(deliveryModeData.getName());
        model.setModeType(DeliveryModeType.from(deliveryModeData.getModeType()));
        model.setModeSubType(DeliveryModeSubType.from(deliveryModeData.getModeSubType()));
        model.setTemperatureType(TemperatureType.from(deliveryModeData.getTemperatureType()));
        model.setActive(model.getActive());

        List<DeliveryModeValueModel> modeValueModels = new ArrayList<>();

        for(final DeliveryModeValueData valueData : deliveryModeData.getTierConditions()) {
            DeliveryModeValueModel modeValueModel = new DeliveryModeValueModel();
            modeValueModel.setMinOrderTotal(valueData.getMinOrderTotal());
            modeValueModel.setDeliveryCost(valueData.getDeliveryCost());
            modeValueModel.setDeliveryMode(model);

            modeValueModels.add(modeValueModel);
        }

        model.getTierConditions().addAll(modeValueModels);

        final DeliveryModeModel savedDeliveryMode = getMerchantDeliveryModeService().save(model);
        return getMerchantDeliveryModeConverter().convert(savedDeliveryMode);
    }

    @Override
    public DeliveryModeData editDeliveryMode(Long id, DeliveryModeData deliveryModeData)
    {
        final DeliveryModeModel model = getMerchantDeliveryModeService().getDeliveryModeForId(id);
        model.setActive(deliveryModeData.getActive());


        List<DeliveryModeValueModel> modeValueModels = new ArrayList<>();

        for(final DeliveryModeValueData valueData : deliveryModeData.getTierConditions()) {
            final DeliveryModeValueModel modeValue;

            if (valueData.getId() != null) {
                modeValue = getMerchantDeliveryModeService().getDeliveryModeValueForId(model, valueData.getId());
            } else {
                modeValue = new DeliveryModeValueModel();
            }

            modeValue.setMinOrderTotal(valueData.getMinOrderTotal());
            modeValue.setDeliveryCost(valueData.getDeliveryCost());
            modeValue.setDeliveryMode(model);

            modeValueModels.add(modeValue);
        }

        model.getTierConditions().clear();
        model.getTierConditions().addAll(modeValueModels);

        final DeliveryModeModel savedDeliveryMode = getMerchantDeliveryModeService().save(model);
        return getMerchantDeliveryModeConverter().convert(savedDeliveryMode);
    }

    @Override
    public void removeDeliveryMode(Long id)
    {
        final DeliveryModeModel deliveryModeModel = getMerchantDeliveryModeService().getDeliveryModeForId(id);
        getMerchantDeliveryModeService().removeDeliveryMode(deliveryModeModel);
    }


    public MerchantDeliveryModeService getMerchantDeliveryModeService()
    {
        return merchantDeliveryModeService;
    }

    public void setMerchantDeliveryModeService(MerchantDeliveryModeService merchantDeliveryModeService)
    {
        this.merchantDeliveryModeService = merchantDeliveryModeService;
    }

    public Converter<DeliveryModeModel, DeliveryModeData> getMerchantDeliveryModeConverter()
    {
        return merchantDeliveryModeConverter;
    }

    public void setMerchantDeliveryModeConverter(Converter<DeliveryModeModel, DeliveryModeData> merchantDeliveryModeConverter)
    {
        this.merchantDeliveryModeConverter = merchantDeliveryModeConverter;
    }
}
