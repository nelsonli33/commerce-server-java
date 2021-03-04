package com.shopflix.core.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shopflix.core.enums.InvoiceType;
import com.shopflix.core.model.ItemModel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoicesettings")
public class InvoiceSettingModel extends ItemModel
{
    private InvoiceType invoiceType;
    private String invoiceTitle;
    private String businessNumber;
    private String contactEmail;
    private String loveCode;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerModel customer;

    public InvoiceType getInvoiceType()
    {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType)
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

    public CustomerModel getCustomer()
    {
        return customer;
    }

    public void setCustomer(CustomerModel customer)
    {
        this.customer = customer;
    }
}
