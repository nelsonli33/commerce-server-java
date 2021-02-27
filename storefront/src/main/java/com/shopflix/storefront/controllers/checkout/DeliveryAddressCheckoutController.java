package com.shopflix.storefront.controllers.checkout;

import com.shopflix.core.controllers.AbstractController;
import com.shopflix.core.data.user.AddressData;
import com.shopflix.core.response.ApiResult;
import com.shopflix.storefront.exceptions.AbstractOrderDeliveryAddressException;
import com.shopflix.storefront.facades.order.CheckoutFacade;
import com.shopflix.storefront.facades.order.data.DeliveryAddressData;
import com.shopflix.storefront.facades.user.CustomerFacade;
import com.shopflix.storefront.facades.user.data.CustomerAddressData;
import com.shopflix.storefront.forms.AddressForm;
import com.shopflix.storefront.forms.validation.AddressValidator;
import com.shopflix.storefront.services.delivery.DeliveryService;
import com.shopflix.storefront.util.AddressDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/checkout/delivery-address")
public class DeliveryAddressCheckoutController extends AbstractController
{
    @Resource(name = "customerFacade")
    private CustomerFacade customerFacade;

    @Resource(name = "checkoutFacade")
    private CheckoutFacade checkoutFacade;

    @Resource(name = "addressValidator")
    private AddressValidator addressValidator;

    @Resource(name = "addressDataUtil")
    private AddressDataUtil addressDataUtil;


    @PostMapping(value = "/add")
    public ResponseEntity<ApiResult<Boolean>> addAddress(@RequestBody final AddressForm addressForm, BindingResult bindingResult)
    {
        getAddressValidator().validate(addressForm, bindingResult);

        doBindingResult(bindingResult);

        final CustomerAddressData customerAddressData = addressDataUtil.convertToCustomerAddressData(addressForm);

        getCustomerFacade().addAddress(customerAddressData);

        getCheckoutFacade().setDeliveryAddress(customerAddressData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(true));
    }


    @PostMapping(value = "/select")
    public ResponseEntity<ApiResult<Boolean>> doSelectDeliveryAddress(@RequestBody final Map<String, String> payload, BindingResult bindingResult)
    {
        Long customerAddressId = Long.valueOf(payload.get("addressId"));

        CustomerAddressData customerAddressData = new CustomerAddressData();
        customerAddressData.setId(customerAddressId);

        final CustomerAddressData selectedAddressData = getCustomerFacade().getAddress(customerAddressData);

        final boolean hasSelectedAddressData = selectedAddressData != null;
        if (hasSelectedAddressData)
        {
            getCheckoutFacade().setDeliveryAddress(selectedAddressData);
        }
        else
        {
            throw new AbstractOrderDeliveryAddressException("Please select an delivery address");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(true));
    }


    public AddressValidator getAddressValidator()
    {
        return addressValidator;
    }

    public void setAddressValidator(AddressValidator addressValidator)
    {
        this.addressValidator = addressValidator;
    }

    public CustomerFacade getCustomerFacade()
    {
        return customerFacade;
    }

    public void setCustomerFacade(CustomerFacade customerFacade)
    {
        this.customerFacade = customerFacade;
    }

    public CheckoutFacade getCheckoutFacade()
    {
        return checkoutFacade;
    }

    public void setCheckoutFacade(CheckoutFacade checkoutFacade)
    {
        this.checkoutFacade = checkoutFacade;
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
