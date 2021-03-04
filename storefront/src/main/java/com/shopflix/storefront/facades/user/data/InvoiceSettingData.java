package com.shopflix.storefront.facades.user.data;

import com.shopflix.core.enums.InvoiceType;

import java.io.Serializable;

public class InvoiceSettingData implements Serializable
{
    private static final long serialVersionUID = -751694310366046343L;

    private Long id;

    private String invoiceType;

    private String invoiceTitle;

    private String businessNumber;

    private String contactEmail;

    private String loveCode;


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getInvoiceType()
    {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType)
    {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle()
    {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle)
    {
        this.invoiceTitle = invoiceTitle;
    }

    public String getBusinessNumber()
    {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber)
    {
        this.businessNumber = businessNumber;
    }

    public String getContactEmail()
    {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail)
    {
        this.contactEmail = contactEmail;
    }

    public String getLoveCode()
    {
        return loveCode;
    }

    public void setLoveCode(String loveCode)
    {
        this.loveCode = loveCode;
    }
}
