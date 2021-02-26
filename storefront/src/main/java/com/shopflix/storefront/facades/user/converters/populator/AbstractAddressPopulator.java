package com.shopflix.storefront.facades.user.converters.populator;


import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.user.AddressData;
import com.shopflix.core.model.user.AddressModel;
import com.shopflix.core.model.user.CustomerModel;
import com.shopflix.storefront.services.customer.CustomerService;

import javax.annotation.Resource;

public abstract class AbstractAddressPopulator<SOURCE extends AddressModel, TARGET extends AddressData>
        implements Populator<SOURCE, TARGET>
{

    private CustomerService customerService;

    protected void addCommon(final AddressModel addressModel, final AddressData addressData)
    {
        addressData.setId(addressModel.getId());
        addressData.setName(addressModel.getName());
        addressData.setPhone(addressModel.getPhone());
        addressData.setCity(addressModel.getCity());
        addressData.setDistrict(addressModel.getDistrict());
        addressData.setZipcode(addressModel.getZipcode());
        addressData.setAddress(addressModel.getAddress1());

        addressData.setStoreId(addressModel.getStoreId());
        addressData.setStoreName(addressModel.getStoreName());

        addressData.setDeliveryAddressType(addressModel.getDeliveryAddressType() != null ? addressModel.getDeliveryAddressType().getValue() : null);

        addDefaultAddress(addressModel, addressData);
    }

    protected void addDefaultAddress(final AddressModel addressModel, final AddressData addressData)
    {
        final CustomerModel currentCustomer = getCustomerService().getCurrentCustomer();

        if (addressModel.equals(currentCustomer.getDefaultShipmentAddress()))
        {
            addressData.setDefaultAddress(Boolean.TRUE);
        }
        else
        {
            addressData.setDefaultAddress(Boolean.FALSE);
        }
    }

    public CustomerService getCustomerService()
    {
        return customerService;
    }

    @Resource(name = "customerService")
    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }
}
