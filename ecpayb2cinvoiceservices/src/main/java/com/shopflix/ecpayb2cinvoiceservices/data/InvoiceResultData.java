package com.shopflix.ecpayb2cinvoiceservices.data;

public class InvoiceResultData extends BaseResultData
{
    /**
     * InvoiceDate
     * 發票開立日期
     */
    private String InvoiceDate;

    /**
     * InvoiceNumber
     * 發票號碼
     */
    private String InvoiceNumber;

    /**
     * RandomNumber
     * 隨機碼
     */
    private String RandomNumber;



    public String getInvoiceDate()
    {
        return InvoiceDate;
    }

    public void setInvoiceDate(String invoiceDate)
    {
        InvoiceDate = invoiceDate;
    }

    public String getInvoiceNumber()
    {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        InvoiceNumber = invoiceNumber;
    }

    public String getRandomNumber()
    {
        return RandomNumber;
    }

    public void setRandomNumber(String randomNumber)
    {
        RandomNumber = randomNumber;
    }
}
