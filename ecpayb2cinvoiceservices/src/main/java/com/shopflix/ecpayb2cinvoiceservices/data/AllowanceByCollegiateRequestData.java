package com.shopflix.ecpayb2cinvoiceservices.data;

public class AllowanceByCollegiateRequestData
{
    private String invoiceNo;

    private String customerName;

    private String NotifyMail;

    private String AllowanceAmount; // int 折讓單總金 額(含稅)

    private String itemName; // 預設不可為空字串且格式為名稱 1|名稱 2|名稱 3|...|名稱 n

    private String itemCount; // 商品數量

    private String itemUnit; // 商品單位

    private String itemPrice; // 商品售價

    private String itemAmount; // 商品合計

    private String returnURL;

    public String getInvoiceNo()
    {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo)
    {
        this.invoiceNo = invoiceNo;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getNotifyMail()
    {
        return NotifyMail;
    }

    public void setNotifyMail(String notifyMail)
    {
        NotifyMail = notifyMail;
    }

    public String getAllowanceAmount()
    {
        return AllowanceAmount;
    }

    public void setAllowanceAmount(String allowanceAmount)
    {
        AllowanceAmount = allowanceAmount;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemCount()
    {
        return itemCount;
    }

    public void setItemCount(String itemCount)
    {
        this.itemCount = itemCount;
    }

    public String getItemUnit()
    {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit)
    {
        this.itemUnit = itemUnit;
    }

    public String getItemPrice()
    {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public String getItemAmount()
    {
        return itemAmount;
    }

    public void setItemAmount(String itemAmount)
    {
        this.itemAmount = itemAmount;
    }

    public String getReturnURL()
    {
        return returnURL;
    }

    public void setReturnURL(String returnURL)
    {
        this.returnURL = returnURL;
    }
}
