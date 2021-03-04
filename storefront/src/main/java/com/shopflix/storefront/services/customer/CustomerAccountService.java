package com.shopflix.storefront.services.customer;
import com.shopflix.core.model.base.AddressModel;
import com.shopflix.core.model.user.CustomerAddressModel;
import com.shopflix.core.model.user.CustomerModel;
import com.shopflix.core.model.user.InvoiceSettingModel;

import java.util.List;

public interface CustomerAccountService {

    CustomerModel register(CustomerModel customerModel, String password);

    List<CustomerAddressModel> getAllAddressEntries(final CustomerModel customerModel);

    CustomerAddressModel getAddressForId(CustomerModel customerModel, Long addressId);

    void saveAddressEntry(CustomerModel customerModel, CustomerAddressModel customerAddressModel);

    void deleteAddressEntry(CustomerModel customerModel, CustomerAddressModel customerAddressModel);

    CustomerAddressModel getDefaultAddress(final CustomerModel customerModel);

    void setDefaultAddressEntry(final CustomerModel customerModel, final CustomerAddressModel customerAddressModel);

    void clearDefaultAddressEntry(final CustomerModel customerModel);

    List<AddressModel> getAddressEntriesForDeliAddressType(final CustomerModel customerModel, final Integer deliAddressType);

    InvoiceSettingModel saveInvoiceSettingEntry(CustomerModel customerModel, InvoiceSettingModel invoiceSettingModel);
}
