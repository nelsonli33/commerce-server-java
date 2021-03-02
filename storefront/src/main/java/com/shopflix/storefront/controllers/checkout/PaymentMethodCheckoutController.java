package com.shopflix.storefront.controllers.checkout;

import com.shopflix.core.controllers.AbstractController;
import com.shopflix.core.enums.PaymentModeType;
import com.shopflix.core.response.ApiResult;
import com.shopflix.storefront.facades.order.CheckoutFacade;
import com.shopflix.storefront.facades.order.PaymentFacade;
import com.shopflix.storefront.facades.order.data.DeliveryModeData;
import com.shopflix.storefront.facades.order.data.SelectOption;
import com.shopflix.storefront.forms.PaymentDetailForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/checkout/payment-methods")
public class PaymentMethodCheckoutController extends AbstractController
{
    @Resource(name = "paymentFacade")
    public PaymentFacade paymentFacade;

    @Resource(name = "checkoutFacade")
    public CheckoutFacade checkoutFacade;

    @GetMapping
    public ResponseEntity<ApiResult<List<SelectOption>>> paymentMethods()
    {
        final List<SelectOption> supportedPaymentModes = paymentFacade.getSupportedPaymentModes();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(supportedPaymentModes));
    }


    @PostMapping("/select")
    public ResponseEntity doSelectPaymentMethod(@RequestBody @Valid PaymentDetailForm form, BindingResult bindingResult) {

        doBindingResult(bindingResult);

        final String paymentModeCode = form.getPaymentModeCode();

        final boolean paymentMode = checkoutFacade.setPaymentMode(paymentModeCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(paymentMode));
    }
}
