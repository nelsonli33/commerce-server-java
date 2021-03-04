package com.shopflix.core.model.order;

import com.shopflix.core.enums.InvoiceType;
import com.shopflix.core.model.ItemModel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "orderinvoices")
public class OrderInvoiceModel extends ItemModel
{
    private String invoiceTitle;
    private String businessNumber;
    private InvoiceType invoiceType;
    private String loveCode;
    private String invoiceNumber;
    private Date invoiceDate;

    @OneToOne(fetch = FetchType.LAZY)
    private AbstractOrderModel order;



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

    public InvoiceType getInvoiceType()
    {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType)
    {
        this.invoiceType = invoiceType;
    }

    public String getLoveCode()
    {
        return loveCode;
    }

    public void setLoveCode(String loveCode)
    {
        this.loveCode = loveCode;
    }

    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate()
    {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate)
    {
        this.invoiceDate = invoiceDate;
    }

    public AbstractOrderModel getOrder()
    {
        return order;
    }

    public void setOrder(AbstractOrderModel order)
    {
        this.order = order;
    }
}
