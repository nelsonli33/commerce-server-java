package com.shopflix.storefront.controllers;

import com.shopflix.core.controllers.AbstractController;
import com.shopflix.core.model.user.CustomerModel;
import com.shopflix.core.response.ApiResult;
import com.shopflix.storefront.facades.user.CustomerFacade;
import com.shopflix.storefront.facades.user.data.CustomerAddressData;
import com.shopflix.storefront.forms.AddressForm;
import com.shopflix.storefront.forms.validation.AddressValidator;
import com.shopflix.storefront.services.customer.CustomerService;
import com.shopflix.storefront.util.AddressDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user/account")
public class UserAccountController extends AbstractController
{

    private static final String ADDRESS_ID_PATH_VARIABLE_PATTERN = "{addressId}";

    @Resource(name = "customerFacade")
    private CustomerFacade customerFacade;

    @Resource(name = "addressValidator")
    private AddressValidator addressValidator;

    @Resource(name = "addressDataUtil")
    private AddressDataUtil addressDataUtil;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/profile")
    public ResponseEntity<ApiResult<Object>> getUserProfile()
    {

        final CustomerModel currentCustomer = customerService.getCurrentCustomer();


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(currentCustomer.getUid()));
    }

    @GetMapping(value = "/addresses")
    public ResponseEntity<ApiResult<List<CustomerAddressData>>> getAddressBook()
    {

        final List<CustomerAddressData> addressBook = getCustomerFacade().getAddressBook();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(addressBook));
    }

    @GetMapping(value = "/addresses/" + ADDRESS_ID_PATH_VARIABLE_PATTERN)
    public ResponseEntity<ApiResult<CustomerAddressData>> getAddress(@PathVariable("addressId") Long addressId)
    {

        final CustomerAddressData customerAddressData = new CustomerAddressData();
        customerAddressData.setId(addressId);

        final CustomerAddressData address = getCustomerFacade().getAddress(customerAddressData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(address));
    }

    @PostMapping(value = "/addresses")
    public ResponseEntity<ApiResult<CustomerAddressData>> addAddress(@RequestBody final AddressForm addressForm, BindingResult bindingResult)
    {

        getAddressValidator().validate(addressForm, bindingResult);

        doBindingResult(bindingResult);

        final CustomerAddressData customerAddressData = addressDataUtil.convertToCustomerAddressData(addressForm);

        final CustomerAddressData newAddressData = customerFacade.addAddress(customerAddressData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(newAddressData));
    }

    @PutMapping(value = "/addresses/" + ADDRESS_ID_PATH_VARIABLE_PATTERN)
    public ResponseEntity<ApiResult<CustomerAddressData>> editAddress(@PathVariable("addressId") Long addressId,
                                                                      @RequestBody final AddressForm addressForm, BindingResult bindingResult)
    {
        getAddressValidator().validate(addressForm, bindingResult);

        doBindingResult(bindingResult);

        final CustomerAddressData customerAddressData = addressDataUtil.convertToCustomerAddressData(addressForm);
        customerAddressData.setId(addressId);

        if (Boolean.TRUE.equals(addressForm.getDefaultAddress()) || customerFacade.getAddressBook().size() <= 1)
        {
            customerAddressData.setDefaultAddress(true);
        }

        final CustomerAddressData newAddressData = customerFacade.editAddress(customerAddressData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(newAddressData));
    }

    @DeleteMapping(value = "/addresses/" + ADDRESS_ID_PATH_VARIABLE_PATTERN)
    public ResponseEntity<ApiResult<Object>> removeAddress(@PathVariable("addressId") Long addressId)
    {
        final CustomerAddressData customerAddressData = new CustomerAddressData();
        customerAddressData.setId(addressId);

        customerFacade.removeAddress(customerAddressData);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(ApiResult.success());
    }


    public CustomerFacade getCustomerFacade()
    {
        return customerFacade;
    }

    public void setCustomerFacade(CustomerFacade customerFacade)
    {
        this.customerFacade = customerFacade;
    }

    public AddressValidator getAddressValidator()
    {
        return addressValidator;
    }

    public void setAddressValidator(AddressValidator addressValidator)
    {
        this.addressValidator = addressValidator;
    }

    public AddressDataUtil getAddressDataUtil()
    {
        return addressDataUtil;
    }

    public void setAddressDataUtil(AddressDataUtil addressDataUtil)
    {
        this.addressDataUtil = addressDataUtil;
    }
}
