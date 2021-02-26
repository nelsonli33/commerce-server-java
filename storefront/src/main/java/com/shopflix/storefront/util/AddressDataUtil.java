package com.shopflix.storefront.util;

import com.shopflix.core.data.user.AddressData;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.storefront.facades.user.data.CustomerAddressData;
import com.shopflix.storefront.forms.AddressForm;
import com.shopflix.storefront.services.delivery.DeliveryService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component(value = "addressDataUtil")
public class AddressDataUtil
{
    private DeliveryService deliveryService;

    public CustomerAddressData convertToCustomerAddressData(AddressForm addressForm) {
        CustomerAddressData customerAddressData = new CustomerAddressData();
        populateAddressData(customerAddressData, addressForm);
        return customerAddressData;
    }

    private void populateAddressData(AddressData addressData, AddressForm addressForm) {
        addressData.setId(addressForm.getId());
        addressData.setName(addressForm.getName());
        addressData.setPhone(addressForm.getPhone());
        addressData.setCity(addressForm.getCity());
        addressData.setDistrict(addressForm.getDistrict());
        addressData.setZipcode(addressForm.getZipcode());
        addressData.setAddress(addressForm.getAddress());
        addressData.setStoreId(addressForm.getStoreId());
        addressData.setStoreName(addressForm.getStoreName());
        addressData.setDefaultAddress(addressForm.getDefaultAddress());

        final DeliveryModeModel deliveryMode = getDeliveryService().getDeliveryModeForCode(addressForm.getDeliveryModeCode());
        final Integer deliAddressType = getDeliveryService().getAddressTypeForDeliveryMode(deliveryMode);
        addressData.setDeliveryAddressType(deliAddressType);
    }

    public DeliveryService getDeliveryService()
    {
        return deliveryService;
    }

    @Resource(name = "deliveryService")
    public void setDeliveryService(DeliveryService deliveryService)
    {
        this.deliveryService = deliveryService;
    }
}
