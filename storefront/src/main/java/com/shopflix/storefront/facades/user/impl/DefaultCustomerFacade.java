package com.shopflix.storefront.facades.user.impl;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.enums.DeliveryAddressType;
import com.shopflix.core.enums.InvoiceType;
import com.shopflix.core.model.user.CustomerAddressModel;
import com.shopflix.core.model.user.CustomerModel;
import com.shopflix.core.model.user.InvoiceSettingModel;
import com.shopflix.storefront.facades.user.CustomerFacade;
import com.shopflix.storefront.facades.user.data.CustomerAddressData;
import com.shopflix.storefront.facades.user.data.InvoiceSettingData;
import com.shopflix.storefront.facades.user.data.RegisterData;
import com.shopflix.storefront.services.customer.CustomerAccountService;
import com.shopflix.storefront.services.customer.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.*;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

public class DefaultCustomerFacade implements CustomerFacade
{
    private CustomerService customerService;
    private CustomerAccountService customerAccountService;
    private Populator<CustomerAddressData, CustomerAddressModel> customerAddressReversePopulator;
    private Converter<CustomerAddressModel, CustomerAddressData> customerAddressConverter;
    private Populator<InvoiceSettingData, InvoiceSettingModel> invoiceSettingReversePopulator;
    private Converter<InvoiceSettingModel, InvoiceSettingData> invoiceSettingConverter;
    private Populator<InvoiceSettingModel, InvoiceSettingData> invoiceSettingPopulator;

    @Override
    public void register(RegisterData registerData)
    {
        validateParameterNotNullStandardMessage("registerData", registerData);
        Assert.hasText(registerData.getUsername(), "The field [Username] cannot be empty");
        Assert.hasText(registerData.getUid(), "The field [Uid] cannot be empty");

        CustomerModel newCustomer = new CustomerModel();
        newCustomer.setUsername(registerData.getUsername());
        newCustomer.setUid(registerData.getUid().toLowerCase());
        newCustomer.setContactEmail(registerData.getUid());

        getCustomerAccountService().register(newCustomer, registerData.getPassword());
    }

    @Override
    public List<CustomerAddressData> getAddressBook()
    {
        final CustomerModel currentCustomer = getCustomerService().getCurrentCustomer();
        final List<CustomerAddressModel> addressModels = getCustomerAccountService().getAllAddressEntries(currentCustomer);

        return getCustomerAddressConverter().convertAll(addressModels);
    }

    @Override
    public CustomerAddressData getAddress(final CustomerAddressData customerAddressData)
    {
        final CustomerModel currentCustomer = getCustomerService().getCurrentCustomer();
        final CustomerAddressModel addressModel = getCustomerAccountService().getAddressForId(currentCustomer, customerAddressData.getId());

        if (addressModel != null)
        {
            return getCustomerAddressConverter().convert(addressModel);
        }

        return null;
    }

    @Override
    public CustomerAddressData addAddress(final CustomerAddressData customerAddressData)
    {
        validateParameterNotNullStandardMessage("customerAddressData", customerAddressData);

        final CustomerModel currentCustomer = getCustomerService().getCurrentCustomer();

        // create the new address model
        final CustomerAddressModel newAddress = new CustomerAddressModel();
        getCustomerAddressReversePopulator().populate(customerAddressData, newAddress);

        // store the address against the customer
        getCustomerAccountService().saveAddressEntry(currentCustomer, newAddress);

        final boolean isMakeThisDefaultAddress = isHomeDeliveryAddress(newAddress) &&
                (customerAddressData.isDefaultAddress() || currentCustomer.getDefaultShipmentAddress() == null);

        if (isMakeThisDefaultAddress)
        {
            getCustomerAccountService().setDefaultAddressEntry(currentCustomer, newAddress);
        }

        return getCustomerAddressConverter().convert(newAddress);
    }

    @Override
    public CustomerAddressData editAddress(CustomerAddressData customerAddressData)
    {
        validateParameterNotNullStandardMessage("customerAddressData", customerAddressData);

        final CustomerModel currentCustomer = getCustomerService().getCurrentCustomer();

        final CustomerAddressModel addressModel = getCustomerAccountService().getAddressForId(currentCustomer, customerAddressData.getId());
        getCustomerAddressReversePopulator().populate(customerAddressData, addressModel);
        getCustomerAccountService().saveAddressEntry(currentCustomer, addressModel);

        if (isHomeDeliveryAddress(addressModel))
        {
            if (customerAddressData.isDefaultAddress())
            {
                getCustomerAccountService().setDefaultAddressEntry(currentCustomer, addressModel);
            }
            else if (addressModel.equals(currentCustomer.getDefaultShipmentAddress()))
            {
                getCustomerAccountService().clearDefaultAddressEntry(currentCustomer);
            }
        }

        return getCustomerAddressConverter().convert(addressModel);

    }

    @Override
    public void removeAddress(CustomerAddressData customerAddressData)
    {
        validateParameterNotNullStandardMessage("customerAddressData", customerAddressData);

        final CustomerModel currentCustomer = getCustomerService().getCurrentCustomer();

        for (final CustomerAddressModel addressModel : getCustomerAccountService().getAllAddressEntries(currentCustomer))
        {
            if (addressModel.getId().equals(customerAddressData.getId()))
            {
                getCustomerAccountService().deleteAddressEntry(currentCustomer, addressModel);
                break;
            }
        }
    }

    @Override
    public Map<String, InvoiceSettingData> getInvoiceSettings()
    {
        final CustomerModel currentCustomer = getCustomerService().getCurrentCustomer();

        final Map<String, InvoiceSettingData> invoiceSettingMap = getInitInvoiceSettingsForCustomer(currentCustomer);

        for (final InvoiceSettingModel invoiceSettingModel : currentCustomer.getInvoiceSettings())
        {
            final InvoiceType invoiceType = invoiceSettingModel.getInvoiceType();
            if (invoiceType != null)
            {
                final InvoiceSettingData invoiceSettingData = invoiceSettingMap.get(invoiceType.getCode());
                getInvoiceSettingPopulator().populate(invoiceSettingModel, invoiceSettingData);
            }
        }

        return invoiceSettingMap;
    }

    private Map<String, InvoiceSettingData> getInitInvoiceSettingsForCustomer(final CustomerModel currentCustomer)
    {

        Map<String, InvoiceSettingData> map = new LinkedHashMap<>(3);

        InvoiceSettingData person = new InvoiceSettingData();
        person.setInvoiceType(InvoiceType.PERSON.getCode());
        person.setContactEmail(currentCustomer.getUid());
        map.put(InvoiceType.PERSON.getCode(), person);

        InvoiceSettingData company = new InvoiceSettingData();
        company.setInvoiceType(InvoiceType.COMPANY.getCode());
        company.setContactEmail(currentCustomer.getUid());
        map.put(InvoiceType.COMPANY.getCode(), company);

        InvoiceSettingData donation = new InvoiceSettingData();
        donation.setInvoiceType(InvoiceType.DONATION.getCode());
        map.put(InvoiceType.DONATION.getCode(), donation);

        return map;
    }

    @Override
    public Map<String, InvoiceSettingData> addInvoiceSetting(InvoiceSettingData invoiceSettingData)
    {
        validateParameterNotNullStandardMessage("invoiceSettingData", invoiceSettingData);

        final CustomerModel currentCustomer = getCustomerService().getCurrentCustomer();

        InvoiceSettingModel invoiceSettingModel = new InvoiceSettingModel();
        getInvoiceSettingReversePopulator().populate(invoiceSettingData, invoiceSettingModel);

        final InvoiceSettingModel savedModel = getCustomerAccountService().saveInvoiceSettingEntry(currentCustomer, invoiceSettingModel);
        return convert(savedModel);
    }

    private Map<String, InvoiceSettingData> convert(InvoiceSettingModel model)
    {
        Map<String, InvoiceSettingData> result = new LinkedHashMap<>();
        final InvoiceSettingData data = getInvoiceSettingConverter().convert(model);
        result.put(data.getInvoiceType(), data);
        return result;
    }




    protected boolean isHomeDeliveryAddress(final CustomerAddressModel addressModel)
    {
        return DeliveryAddressType.HOME.equals(addressModel.getDeliveryAddressType());
    }

    public CustomerService getCustomerService()
    {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public void setCustomerAccountService(CustomerAccountService customerAccountService)
    {
        this.customerAccountService = customerAccountService;
    }

    public CustomerAccountService getCustomerAccountService()
    {
        return customerAccountService;
    }

    public Populator<CustomerAddressData, CustomerAddressModel> getCustomerAddressReversePopulator()
    {
        return customerAddressReversePopulator;
    }

    public void setCustomerAddressReversePopulator(Populator<CustomerAddressData, CustomerAddressModel> customerAddressReversePopulator)
    {
        this.customerAddressReversePopulator = customerAddressReversePopulator;
    }

    public Converter<CustomerAddressModel, CustomerAddressData> getCustomerAddressConverter()
    {
        return customerAddressConverter;
    }

    public void setCustomerAddressConverter(Converter<CustomerAddressModel, CustomerAddressData> customerAddressConverter)
    {
        this.customerAddressConverter = customerAddressConverter;
    }

    public Populator<InvoiceSettingData, InvoiceSettingModel> getInvoiceSettingReversePopulator()
    {
        return invoiceSettingReversePopulator;
    }

    public void setInvoiceSettingReversePopulator(Populator<InvoiceSettingData, InvoiceSettingModel> invoiceSettingReversePopulator)
    {
        this.invoiceSettingReversePopulator = invoiceSettingReversePopulator;
    }

    public Converter<InvoiceSettingModel, InvoiceSettingData> getInvoiceSettingConverter()
    {
        return invoiceSettingConverter;
    }

    public void setInvoiceSettingConverter(Converter<InvoiceSettingModel, InvoiceSettingData> invoiceSettingConverter)
    {
        this.invoiceSettingConverter = invoiceSettingConverter;
    }

    public Populator<InvoiceSettingModel, InvoiceSettingData> getInvoiceSettingPopulator()
    {
        return invoiceSettingPopulator;
    }

    public void setInvoiceSettingPopulator(Populator<InvoiceSettingModel, InvoiceSettingData> invoiceSettingPopulator)
    {
        this.invoiceSettingPopulator = invoiceSettingPopulator;
    }
}
