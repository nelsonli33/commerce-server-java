package com.shopflix.storefront.facades.user.impl;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.user.AddressData;
import com.shopflix.core.enums.DeliveryAddressType;
import com.shopflix.core.model.user.AddressModel;
import com.shopflix.core.model.user.CustomerAddressModel;
import com.shopflix.core.model.user.CustomerModel;
import com.shopflix.storefront.facades.user.CustomerFacade;
import com.shopflix.storefront.facades.user.data.CustomerAddressData;
import com.shopflix.storefront.facades.user.data.RegisterData;
import com.shopflix.storefront.services.customer.CustomerAccountService;
import com.shopflix.storefront.services.customer.CustomerService;
import org.springframework.util.Assert;

import java.util.List;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

public class DefaultCustomerFacade implements CustomerFacade
{

    private CustomerService customerService;
    private CustomerAccountService customerAccountService;
    private Populator<CustomerAddressData, CustomerAddressModel> customerAddressReversePopulator;
    private Converter<CustomerAddressModel, CustomerAddressData> customerAddressConverter;

    @Override
    public void register(RegisterData registerData)
    {
        validateParameterNotNullStandardMessage("registerData", registerData);
        Assert.hasText(registerData.getUsername(), "The field [Username] cannot be empty");
        Assert.hasText(registerData.getUid(), "The field [Uid] cannot be empty");

        CustomerModel newCustomer = new CustomerModel();
        newCustomer.setUsername(registerData.getUsername());
        newCustomer.setUid(registerData.getUid().toLowerCase());
        newCustomer.setEmail(registerData.getEmail());

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

        return getCustomerAddressConverter().convert(addressModel);
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
}
