package com.shopflix.ecpayb2cinvoiceservices.config;

import com.shopflix.ecpayb2cinvoiceservices.ecpay.InvoiceAllInOne;
import com.shopflix.ecpayb2cinvoiceservices.services.impl.DefaultEcpayInvoiceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"com.shopflix.ecpayb2cinvoiceservices", "ecpay.invoice.integration"})
public class EcpayB2CInvoiceConfig
{
    @Bean
    public InvoiceAllInOne invoiceAllInOne() {
        return new InvoiceAllInOne();
    }

    @Bean
    public DefaultEcpayInvoiceService ecpayInvoiceService() {
        DefaultEcpayInvoiceService ecpayInvoiceService = new DefaultEcpayInvoiceService();
        ecpayInvoiceService.setInvoiceAllInOne(invoiceAllInOne());
        return ecpayInvoiceService;
    }
}
