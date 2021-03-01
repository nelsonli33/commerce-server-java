package com.shopflix.ecpaypaymentservices.data;

public class EcpayCreditCheckoutParameter
{
    private String orderCode; // Order 訂單編號 (不能重複)

    private String totalAmount; // 交易金額：整數字串，不可有小數點，僅限台幣

    private String tradeDesc; // 交易描述

    private String itemName; // 商品依符號#分隔

    private String returnUrl;

    private String clientBackUrl;

    private String orderResultUrl; // 測試階段時可先不要設定此參數，可將畫面停留在綠界，看見綠界所提供的錯誤訊息 debug

    private String customerUid;

    public EcpayCreditCheckoutParameter(String orderCode, String totalAmount, String tradeDesc, String itemName, String returnUrl, String clientBackUrl, String orderResultUrl, String customerUid)
    {
        this.orderCode = orderCode;
        this.totalAmount = totalAmount;
        this.tradeDesc = tradeDesc;
        this.itemName = itemName;
        this.returnUrl = returnUrl;
        this.clientBackUrl = clientBackUrl;
        this.orderResultUrl = orderResultUrl;
        this.customerUid = customerUid;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getTradeDesc()
    {
        return tradeDesc;
    }

    public void setTradeDesc(String tradeDesc)
    {
        this.tradeDesc = tradeDesc;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getReturnUrl()
    {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl)
    {
        this.returnUrl = returnUrl;
    }

    public String getClientBackUrl()
    {
        return clientBackUrl;
    }

    public void setClientBackUrl(String clientBackUrl)
    {
        this.clientBackUrl = clientBackUrl;
    }

    public String getOrderResultUrl()
    {
        return orderResultUrl;
    }

    public void setOrderResultUrl(String orderResultUrl)
    {
        this.orderResultUrl = orderResultUrl;
    }

    public String getCustomerUid()
    {
        return customerUid;
    }

    public void setCustomerUid(String customerUid)
    {
        this.customerUid = customerUid;
    }
}
