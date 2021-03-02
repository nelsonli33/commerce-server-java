package com.shopflix.ecpayb2cinvoiceservices.data;

public class BaseResultData
{
    private String RtnCode;

    private String RtnMsg;

    /**
     * CheckMacValue 檢查碼
     */
    private String CheckMacValue;



    public String getRtnCode()
    {
        return RtnCode;
    }

    public void setRtnCode(String rtnCode)
    {
        RtnCode = rtnCode;
    }

    public String getRtnMsg()
    {
        return RtnMsg;
    }

    public void setRtnMsg(String rtnMsg)
    {
        RtnMsg = rtnMsg;
    }

    public String getCheckMacValue()
    {
        return CheckMacValue;
    }

    public void setCheckMacValue(String checkMacValue)
    {
        CheckMacValue = checkMacValue;
    }
}
