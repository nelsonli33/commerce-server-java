package com.shopflix.storefront.facades.order;

import com.shopflix.storefront.data.order.DeliveryModeData;

import java.util.List;

public interface CheckoutFacade
{
    List<DeliveryModeData> getSupportedDeliveryModes();

    /**
     * Set the delivery mode on the cart Checks if the deliveryMode code is supported. If the code is
     * not supported it does not get set and a false is returned.
     * @param deliveryModeCode - the delivery mode
     * @return true if successful
     */
    boolean setDeliveryMode(String deliveryModeCode);
}
