package com.shopflix.ecpayb2cinvoiceservices.data;

public class InvoiceNotifyRequestData
{
    private String InvoiceNo;

    private String AllowanceNo;

    private String NotifyMail;

    private InvoiceTag InvoiceTag;

    public String getInvoiceNo()
    {
        return InvoiceNo;
    }

    public void setInvoiceNo(String invoiceNo)
    {
        InvoiceNo = invoiceNo;
    }

    public String getAllowanceNo()
    {
        return AllowanceNo;
    }

    public void setAllowanceNo(String allowanceNo)
    {
        AllowanceNo = allowanceNo;
    }

    public String getNotifyMail()
    {
        return NotifyMail;
    }

    public void setNotifyMail(String notifyMail)
    {
        NotifyMail = notifyMail;
    }

    public com.shopflix.ecpayb2cinvoiceservices.data.InvoiceTag getInvoiceTag()
    {
        return InvoiceTag;
    }

    public void setInvoiceTag(com.shopflix.ecpayb2cinvoiceservices.data.InvoiceTag invoiceTag)
    {
        InvoiceTag = invoiceTag;
    }
}
