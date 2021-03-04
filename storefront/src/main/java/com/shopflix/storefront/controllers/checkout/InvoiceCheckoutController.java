package com.shopflix.storefront.controllers.checkout;

import com.shopflix.core.controllers.AbstractController;
import com.shopflix.core.response.ApiResult;
import com.shopflix.storefront.facades.order.CheckoutFacade;
import com.shopflix.storefront.facades.order.PaymentFacade;
import com.shopflix.storefront.facades.order.data.SelectOption;
import com.shopflix.storefront.facades.user.CustomerFacade;
import com.shopflix.storefront.facades.user.data.InvoiceSettingData;
import com.shopflix.storefront.forms.InvoiceSettingForm;
import com.shopflix.storefront.forms.PaymentDetailForm;
import com.shopflix.storefront.forms.validation.InvoiceSettingValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/checkout/invoice")
public class InvoiceCheckoutController extends AbstractController
{
    @Resource(name = "checkoutFacade")
    public CheckoutFacade checkoutFacade;

    @Resource(name = "customerFacade")
    public CustomerFacade customerFacade;

    @Resource(name = "invoiceSettingValidator")
    public InvoiceSettingValidator invoiceSettingValidator;

    @GetMapping(value = "/settings")
    public ResponseEntity<ApiResult<Map<String, InvoiceSettingData>>> invoiceSettings() {

        final Map<String, InvoiceSettingData> invoiceSettings = customerFacade.getInvoiceSettings();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(invoiceSettings));
    }

    @PostMapping(value = "/settings/add")
    public ResponseEntity<ApiResult<Map<String, InvoiceSettingData>>> addInvoiceSetting(@RequestBody InvoiceSettingForm form, BindingResult bindingResult)
    {
        invoiceSettingValidator.validate(form, bindingResult);

        doBindingResult(bindingResult);

        final InvoiceSettingData invoiceSettingData = fillInvoiceSettingData(form);

        // set customer default invoice
        final Map<String, InvoiceSettingData> map = customerFacade.addInvoiceSetting(invoiceSettingData);

        // set invoice to order

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(map));
    }

    protected InvoiceSettingData fillInvoiceSettingData(final InvoiceSettingForm form) {
        InvoiceSettingData data = new InvoiceSettingData();
        data.setId(form.getId());
        data.setInvoiceType(form.getInvoiceType());
        data.setInvoiceTitle(form.getInvoiceTitle());
        data.setBusinessNumber(form.getBusinessNumber());
        data.setContactEmail(form.getContactEmail());
        data.setLoveCode(form.getLoveCode());
        return data;
    }

}
