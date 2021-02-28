package com.shopflix.storefront.facades.order;

import com.shopflix.storefront.facades.order.data.SelectOption;

import java.util.List;

public interface PaymentFacade
{
    List<SelectOption> getSupportedPaymentModes();
}
