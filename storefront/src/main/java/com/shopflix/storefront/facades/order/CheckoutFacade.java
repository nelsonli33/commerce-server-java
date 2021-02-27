package com.shopflix.storefront.facades.order;

import com.shopflix.core.data.user.AddressData;
import com.shopflix.storefront.facades.order.data.CartData;
import com.shopflix.storefront.facades.order.data.DeliveryModeData;
import com.shopflix.storefront.facades.user.data.CustomerAddressData;

import java.util.List;

public interface CheckoutFacade
{
    CartData getCheckoutCart();

    List<DeliveryModeData> getSupportedDeliveryModes();

    /**
     * Set the delivery mode on the cart Checks if the deliveryMode code is supported. If the code is
     * not supported it does not get set and a false is returned.
     * @param deliveryModeCode - the delivery mode
     * @return true if successful
     */
    boolean setDeliveryMode(String deliveryModeCode);


    boolean setDeliveryAddress(CustomerAddressData customerAddressData);
}
