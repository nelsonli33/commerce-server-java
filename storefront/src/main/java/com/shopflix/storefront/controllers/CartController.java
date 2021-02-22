package com.shopflix.storefront.controllers;

import com.shopflix.core.controllers.AbstractController;
import com.shopflix.core.model.user.CustomerModel;
import com.shopflix.core.response.ApiResult;
import com.shopflix.storefront.data.order.CartData;
import com.shopflix.storefront.data.order.CartModificationData;
import com.shopflix.storefront.facades.order.CartFacade;
import com.shopflix.storefront.forms.AddToCartForm;
import com.shopflix.storefront.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/cart")
public class CartController extends AbstractController
{
    @Resource(name = "cartFacade")
    private CartFacade cartFacade;

    @GetMapping
    public ResponseEntity<ApiResult<CartData>> showCart() {
        final CartData cart = cartFacade.getCart();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(cart));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ApiResult<CartModificationData>> addToCart(@RequestBody @Valid final AddToCartForm form, final BindingResult bindingResult) {

        doBindingResult(bindingResult);

        final CartModificationData cartModificationData =
                cartFacade.addToCart(form.getProductId(), form.getVariantId(), form.getQty());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(cartModificationData));
    }
}
