package com.shopflix.storefront.controllers.checkout;

import com.shopflix.core.controllers.AbstractController;
import com.shopflix.core.response.ApiResult;
import com.shopflix.storefront.facades.order.PaymentFacade;
import com.shopflix.storefront.facades.order.data.DeliveryModeData;
import com.shopflix.storefront.facades.order.data.SelectOption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/checkout/payment-methods")
public class PaymentMethodCheckoutController extends AbstractController
{
    @Resource(name = "paymentFacade")
    public PaymentFacade paymentFacade;

    @GetMapping
    public ResponseEntity<ApiResult<List<SelectOption>>> deliveryMethods()
    {
        final List<SelectOption> supportedPaymentModes = paymentFacade.getSupportedPaymentModes();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(supportedPaymentModes));
    }
}
