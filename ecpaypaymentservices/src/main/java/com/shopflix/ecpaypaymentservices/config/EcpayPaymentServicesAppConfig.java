package com.shopflix.ecpaypaymentservices.config;

import com.shopflix.ecpaypaymentservices.services.impl.DefaultEcpayPaymentService;
import ecpay.payment.integration.AllInOne;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EcpayPaymentServicesAppConfig
{
    @Bean
    public AllInOne allInOne() {
        return new AllInOne("");
    }

    @Bean
    public DefaultEcpayPaymentService ecpayPaymentService() {
        DefaultEcpayPaymentService ecpayPaymentService = new DefaultEcpayPaymentService();
        ecpayPaymentService.setAllInOne(allInOne());
        return ecpayPaymentService;
    }

}
