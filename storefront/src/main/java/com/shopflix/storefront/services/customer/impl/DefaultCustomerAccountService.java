package com.shopflix.storefront.services.customer.impl;

import com.shopflix.core.model.base.AddressModel;
import com.shopflix.core.model.user.CustomerAddressModel;
import com.shopflix.core.model.user.CustomerModel;
import com.shopflix.core.model.user.InvoiceSettingModel;
import com.shopflix.core.repository.user.CustomerRepository;
import com.shopflix.core.service.ModelService;
import com.shopflix.storefront.exceptions.DuplicateUidException;
import com.shopflix.storefront.services.customer.CustomerAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;
import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

public class DefaultCustomerAccountService implements CustomerAccountService
{
    private ModelService modelService;
    private PasswordEncoder passwordEncoder;
    private CustomerRepository customerRepository;

    @Override
    public CustomerModel register(CustomerModel customerModel, String password)
    {
        validateParameterNotNullStandardMessage("customerModel", customerModel);

        CustomerModel customer = customerRepository.findByUid(customerModel.getUid());
        if (customer != null)
        {
            throw new DuplicateUidException(customerModel.getUid() + " already exist.");
        }

        if (password != null)
        {
            String encodePwd = passwordEncoder.encode(password);
            customerModel.setPassword(encodePwd);
        }

        return customerRepository.save(customerModel);
    }

    @Override
    public List<CustomerAddressModel> getAllAddressEntries(final CustomerModel customerModel)
    {
        validateParameterNotNull(customerModel, "Customer model cannot be null");
        final List<CustomerAddressModel> addressModels = new ArrayList<>();
        addressModels.addAll(customerModel.getAddresses());
        return addressModels;
    }

    @Override
    public CustomerAddressModel getAddressForId(CustomerModel customerModel, Long addressId)
    {
        validateParameterNotNull(customerModel, "Customer model cannot be null");

        for (final CustomerAddressModel addressModel : getAllAddressEntries(customerModel))
        {
            if (addressModel.getId().equals(addressId))
            {
                return addressModel;
            }
        }
        return null;
    }


    @Override
    public void saveAddressEntry(CustomerModel customerModel, CustomerAddressModel customerAddressModel)
    {
        validateParameterNotNullStandardMessage("customerModel", customerModel);
        validateParameterNotNullStandardMessage("CustomerAddressModel", customerAddressModel);

        if (customerModel.getAddresses().contains(customerAddressModel))
        {
            getModelService().save(customerAddressModel);
        }
        else
        {
            customerAddressModel.setCustomer(customerModel);
            customerModel.getAddresses().add(customerAddressModel);
            getModelService().save(customerAddressModel);
        }
        getModelService().save(customerModel);
        getModelService().refresh(customerModel);
    }


    @Override
    public void deleteAddressEntry(CustomerModel customerModel, CustomerAddressModel customerAddressModel)
    {
        validateParameterNotNullStandardMessage("customerModel", customerModel);
        validateParameterNotNullStandardMessage("CustomerAddressModel", customerAddressModel);

        if (customerModel.getAddresses().contains(customerAddressModel))
        {

            final boolean changeDefaultAddress = customerAddressModel.equals(getDefaultAddress(customerModel));

            customerModel.getAddresses().remove(customerAddressModel);
            getModelService().save(customerModel);
            getModelService().refresh(customerModel);

            final Iterator<CustomerAddressModel> addressModelIterator = customerModel.getAddresses().iterator();
            if (changeDefaultAddress && addressModelIterator.hasNext())
            {
                setDefaultAddressEntry(customerModel, addressModelIterator.next());
            }
        }
        else
        {
            throw new IllegalArgumentException("Customer Address " + customerAddressModel + " does not belong to the customer " + customerModel
                    + " and will not be removed.");
        }
    }

    @Override
    public CustomerAddressModel getDefaultAddress(final CustomerModel customerModel)
    {
        return customerModel.getDefaultShipmentAddress();
    }

    @Override
    public void setDefaultAddressEntry(final CustomerModel customerModel, final CustomerAddressModel customerAddressModel)
    {
        validateParameterNotNull(customerModel, "Customer model cannot be null");
        validateParameterNotNullStandardMessage("CustomerAddressModel", customerAddressModel);

        if (customerModel.getAddresses().contains(customerAddressModel))
        {
            customerModel.setDefaultShipmentAddress(customerAddressModel);
        }
        getModelService().save(customerModel);
        getModelService().refresh(customerModel);
    }

    @Override
    public void clearDefaultAddressEntry(final CustomerModel customerModel)
    {
        validateParameterNotNull(customerModel, "Customer model cannot be null");
        customerModel.setDefaultShipmentAddress(null);
        getModelService().save(customerModel);
    }

    @Override
    public List<AddressModel> getAddressEntriesForDeliAddressType(CustomerModel customerModel, Integer deliAddressType)
    {
        validateParameterNotNull(customerModel, "Customer model cannot be null");
        validateParameterNotNull(deliAddressType, "deliAddressType cannot be null");

        List<AddressModel> addresses = new ArrayList<>();

        for (final AddressModel address : customerModel.getAddresses())
        {
            if (address.getDeliveryAddressType().getValue().equals(deliAddressType))
            {
                addresses.add(address);
            }
        }

        return addresses;
    }

    @Override
    public InvoiceSettingModel saveInvoiceSettingEntry(CustomerModel customerModel, InvoiceSettingModel invoiceSettingModel)
    {
        validateParameterNotNullStandardMessage("customerModel", customerModel);
        validateParameterNotNullStandardMessage("invoiceSettingModel", invoiceSettingModel);

        if (customerModel.getInvoiceSettings().contains(invoiceSettingModel))
        {
            getModelService().save(invoiceSettingModel);
        }
        else
        {
            invoiceSettingModel.setCustomer(customerModel);
            customerModel.getInvoiceSettings().add(invoiceSettingModel);
            getModelService().save(invoiceSettingModel);
        }
        getModelService().save(customerModel);
        getModelService().refresh(customerModel);

        return invoiceSettingModel;
    }



    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    public void setCustomerRepository(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    public PasswordEncoder getPasswordEncoder()
    {
        return passwordEncoder;
    }

    public CustomerRepository getCustomerRepository()
    {
        return customerRepository;
    }


}
