package com.shopflix.ecpaypaymentservices.services;

import com.shopflix.ecpaypaymentservices.data.EcpayCreditCheckoutParameter;

public interface EcpayPaymentService
{
    String genCreditOnceCheckoutForm(EcpayCreditCheckoutParameter parameter);
}
