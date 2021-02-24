package com.shopflix.storefront.controllers.checkout;

import com.shopflix.core.controllers.AbstractController;
import com.shopflix.core.response.ApiResult;
import com.shopflix.storefront.data.order.DeliveryModeData;
import com.shopflix.storefront.facades.order.CheckoutFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/v1/checkout/delivery-methods")
public class DeliveryMethodCheckoutController extends AbstractController
{
    @Resource(name = "checkoutFacade")
    private CheckoutFacade checkoutFacade;

    @GetMapping
    public ResponseEntity<ApiResult<List<DeliveryModeData>>> deliveryMethods()
    {

        final List<DeliveryModeData> supportedDeliveryModes = checkoutFacade.getSupportedDeliveryModes();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(supportedDeliveryModes));
    }

    @PostMapping(value = "/select")
    public ResponseEntity<ApiResult<Boolean>> doSelectDeliveryMethod(@RequestBody Map<String, String> payload)
    {
        final String deliveryModeCode = payload.get("deliveryMethod");

        boolean ret = false;

        if (StringUtils.isNotEmpty(deliveryModeCode))
        {
            ret = checkoutFacade.setDeliveryMode(deliveryModeCode);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(ret));
    }
}
