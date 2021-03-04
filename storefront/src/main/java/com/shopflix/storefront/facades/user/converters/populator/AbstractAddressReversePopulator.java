package com.shopflix.storefront.facades.user.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.user.AddressData;
import com.shopflix.core.enums.DeliveryAddressType;
import com.shopflix.core.model.base.AddressModel;

public abstract class AbstractAddressReversePopulator<SOURCE extends AddressData, TARGET extends AddressModel> implements
        Populator<SOURCE, TARGET>
{
    protected void addCommon(final AddressData addressData, final AddressModel addressModel)
    {
        addressModel.setName(addressData.getName());
        addressModel.setPhone(addressData.getPhone());
        addressModel.setCity(addressData.getCity());
        addressModel.setDistrict(addressData.getDistrict());
        addressModel.setZipcode(addressData.getZipcode());
        addressModel.setAddress1(addressData.getAddress());

        addressModel.setStoreId(addressData.getStoreId());
        addressModel.setStoreName(addressData.getStoreName());

        addressModel.setDeliveryAddressType(DeliveryAddressType.from(addressData.getDeliveryAddressType()));
    }
}
