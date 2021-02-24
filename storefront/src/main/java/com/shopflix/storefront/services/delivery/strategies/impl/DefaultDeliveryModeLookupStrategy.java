package com.shopflix.storefront.services.delivery.strategies.impl;

import com.google.common.collect.Lists;
import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.core.model.order.AbstractOrderModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.repository.delivery.DeliveryModeRepository;
import com.shopflix.storefront.services.delivery.strategies.DeliveryModeLookupStrategy;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultDeliveryModeLookupStrategy implements DeliveryModeLookupStrategy
{
    private DeliveryModeRepository deliveryModeRepository;

    @Override
    public List<DeliveryModeModel> getSelectableDeliveryModesForOrder(AbstractOrderModel order)
    {
        final List<DeliveryModeModel> enabledDeliveryModes = getDefaultEnableDeliveryModes();

        final List<ProductModel> productModels =
                order.getLineItems().stream().map(AbstractOrderLineItemModel::getProduct).collect(Collectors.toList());

        final List<DeliveryModeModel> supportedDeliveryModesForProducts = getSupportedDeliveryModesForProducts(productModels);


        if (CollectionUtils.isEmpty(enabledDeliveryModes)) {
            return Collections.emptyList();
        }

        enabledDeliveryModes.retainAll(supportedDeliveryModesForProducts);

        return enabledDeliveryModes;
    }


    protected List<DeliveryModeModel> getSupportedDeliveryModesForProducts(List<ProductModel> products) {

        if (CollectionUtils.isEmpty(products)) {
            return Collections.emptyList();
        }

        final Set<DeliveryModeModel> firstProductSupportedDeliModes = products.get(0).getDeliveryModes();

        for(int i = 1; i < products.size(); i++) {

            final ProductModel productModel = products.get(i);

            firstProductSupportedDeliModes.retainAll(productModel.getDeliveryModes());

        }

        if (CollectionUtils.isEmpty(firstProductSupportedDeliModes)) {
            return getDefaultEnableDeliveryModes();
        }

        return Lists.newArrayList(firstProductSupportedDeliModes);
    }


    protected List<DeliveryModeModel> getDefaultEnableDeliveryModes() {
        return getDeliveryModeRepository().findDeliveryModeModelsByActiveTrue();
    }



    public DeliveryModeRepository getDeliveryModeRepository()
    {
        return deliveryModeRepository;
    }

    @Resource(name = "deliveryModeRepository")
    public void setDeliveryModeRepository(DeliveryModeRepository deliveryModeRepository)
    {
        this.deliveryModeRepository = deliveryModeRepository;
    }
}
