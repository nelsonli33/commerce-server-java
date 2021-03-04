package com.shopflix.storefront.facades.user;

import com.shopflix.core.data.user.AddressData;
import com.shopflix.storefront.facades.user.data.CustomerAddressData;
import com.shopflix.storefront.facades.user.data.InvoiceSettingData;
import com.shopflix.storefront.facades.user.data.RegisterData;

import java.util.List;
import java.util.Map;

/**
 * Defines an API to perform various customer related operations
 */
public interface CustomerFacade {

    /**
     * Register a user with given parameters
     *
     * @param registerData
     *           the user data the user will be registered with
     */
    void register(RegisterData registerData);

    List<CustomerAddressData> getAddressBook();

    CustomerAddressData getAddress(final CustomerAddressData customerAddressData);

    CustomerAddressData addAddress(final CustomerAddressData customerAddressData);

    CustomerAddressData editAddress(final CustomerAddressData customerAddressData);

    void removeAddress(final CustomerAddressData customerAddressData);

    Map<String, InvoiceSettingData> getInvoiceSettings();

    Map<String, InvoiceSettingData> addInvoiceSetting(InvoiceSettingData invoiceSettingData);
}
