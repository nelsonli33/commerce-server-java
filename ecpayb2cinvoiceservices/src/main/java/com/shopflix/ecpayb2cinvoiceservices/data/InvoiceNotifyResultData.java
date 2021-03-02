package com.shopflix.ecpayb2cinvoiceservices.data;

public class InvoiceNotifyResultData extends BaseResultData
{
    private String MerchantID;

    public String getMerchantID()
    {
        return MerchantID;
    }

    public void setMerchantID(String merchantID)
    {
        MerchantID = merchantID;
    }
}
