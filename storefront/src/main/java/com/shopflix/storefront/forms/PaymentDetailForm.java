package com.shopflix.storefront.forms;

import com.shopflix.core.enums.PaymentModeType;

import javax.validation.constraints.NotNull;

public class PaymentDetailForm
{
    @NotNull(message = "{checkout.paymentMethod.notNull}")
    private String paymentModeCode;

    public String getPaymentModeCode()
    {
        return paymentModeCode;
    }

    public void setPaymentModeCode(String paymentModeCode)
    {
        this.paymentModeCode = paymentModeCode;
    }
}
