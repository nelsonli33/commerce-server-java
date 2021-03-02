package com.shopflix.ecpayb2cinvoiceservices.data;

public class InvoiceInvalidRequestData
{
    private String invoiceNumber;

    private String reason;

    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }
}
