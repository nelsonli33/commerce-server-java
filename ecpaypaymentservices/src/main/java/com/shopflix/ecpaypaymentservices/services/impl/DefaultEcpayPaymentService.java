package com.shopflix.ecpaypaymentservices.services.impl;

import com.shopflix.ecpaypaymentservices.data.EcpayCreditCheckoutParameter;
import com.shopflix.ecpaypaymentservices.services.EcpayPaymentService;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

import java.util.Date;

public class DefaultEcpayPaymentService implements EcpayPaymentService
{
    private final static Logger LOG = LoggerFactory.getLogger(DefaultEcpayPaymentService.class);

    private AllInOne allInOne;

    public String genCreditOnceCheckoutForm(EcpayCreditCheckoutParameter parameter) {
        final AioCheckOutOneTime obj = new AioCheckOutOneTime();
        obj.setMerchantTradeNo(parameter.getOrderCode()); // Order 訂單編號 (不能重複)
        obj.setMerchantTradeDate(getCurrentDateTime());
        obj.setTotalAmount(parameter.getTotalAmount()); // 交易金額：整數字串，不可有小數點，僅限台幣
        obj.setTradeDesc("");
        obj.setItemName(""); // 符號#分隔
        obj.setReturnURL(parameter.getReturnUrl());
        obj.setClientBackURL(parameter.getClientBackUrl());
        obj.setOrderResultURL(parameter.getOrderResultUrl()); // 測試階段時可先不要設定此參數，可將畫面停留在綠界，看見綠界所提供的錯誤訊息 debug
        obj.setNeedExtraPaidInfo("N");
        obj.setBidingCard("1");
        obj.setMerchantMemberID(allInOne.getMerchantID().concat(parameter.getCustomerUid()));
        obj.setRedeem("N");
        obj.setUnionPay("2");
        return allInOne.aioCheckOut(obj, null);
    }



    protected String getCurrentDateTime() {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return formatter.format(new Date());
    }

    public AllInOne getAllInOne()
    {
        return allInOne;
    }

    public void setAllInOne(AllInOne allInOne)
    {
        this.allInOne = allInOne;
    }
}
