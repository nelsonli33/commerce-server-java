package com.shopflix.storefront.facades.order.impl;

import com.shopflix.core.enums.DeliveryModeType;
import com.shopflix.core.enums.PaymentModeType;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.core.service.I18NService;
import com.shopflix.core.util.ServicesUtil;
import com.shopflix.storefront.facades.order.PaymentFacade;
import com.shopflix.storefront.facades.order.data.SelectOption;
import com.shopflix.storefront.services.order.CartService;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;

public class DefaultPaymentFacade implements PaymentFacade
{

    @Value("${creditcard.installment.min.amount}")
    private Double minAmountCreditCardInstallment;

    private CartService cartService;
    private I18NService i18nService;


    @Override
    public List<SelectOption> getSupportedPaymentModes()
    {
        final CartModel cart = getCartService().getCartForCurrentCustomer();
        validateParameterNotNull(cart, "cart model cannot be null");

        List<SelectOption> paymentModes = new ArrayList<>();
        paymentModes.add(new SelectOption(PaymentModeType.CREDITCARDONCE.getCode(), getI18nService().getLocalizedMessage("checkout.paymentMethod.creditCard")));

        if (minAmountCreditCardInstallment != null) {
            final Double totalPrice = cart.getTotalPrice();

            if (totalPrice > minAmountCreditCardInstallment) {
                paymentModes.add(new SelectOption(PaymentModeType.CREDITCARDINSTALLMENT.getCode(), getI18nService().getLocalizedMessage("checkout.paymentMethod.creditCard.installment")));
            }
        }

        if (isHomeDelivery(cart)) {
            return paymentModes;
        }


        paymentModes.add(new SelectOption(PaymentModeType.COD.getCode(), getI18nService().getLocalizedMessage("checkout.paymentMethod.cod")));

        return paymentModes;
    }

    protected boolean isHomeDelivery(final CartModel cart)
    {
        final DeliveryModeModel deliveryMode = cart.getDeliveryMode();
        if (deliveryMode != null && DeliveryModeType.HOME.equals(deliveryMode.getModeType()))
        {
            return true;
        }
        return false;
    }

    public CartService getCartService()
    {
        return cartService;
    }

    public void setCartService(CartService cartService)
    {
        this.cartService = cartService;
    }

    public I18NService getI18nService()
    {
        return i18nService;
    }

    public void setI18nService(I18NService i18nService)
    {
        this.i18nService = i18nService;
    }
}
