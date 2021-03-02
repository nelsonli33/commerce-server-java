package com.shopflix.ecpayb2cinvoiceservices.data;

public class InvoiceRequestData
{
    private String orderCode;

    private String customerName;

    private String customerAddress;

    private String businessNumber;

    private String customerPhone;

    private String customerEmail;

    private Boolean donation;

    private String loveCode;

    private int salesAmount;  // 僅限新台幣, 金額不可為 0 元

    private String itemName; // 預設不可為空字串且格式為名稱 1|名稱 2|名稱 3|...|名稱 n

    private String itemCount; // 商品數量

    private String itemUnit; // 商品單位

    private String itemPrice; // 商品售價

    private String itemAmount; // 商品合計


    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCustomerAddress()
    {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress)
    {
        this.customerAddress = customerAddress;
    }

    public String getBusinessNumber()
    {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber)
    {
        this.businessNumber = businessNumber;
    }

    public String getCustomerPhone()
    {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone)
    {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail()
    {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail)
    {
        this.customerEmail = customerEmail;
    }

    public Boolean getDonation()
    {
        return donation;
    }

    public void setDonation(Boolean donation)
    {
        this.donation = donation;
    }


    public int getSalesAmount()
    {
        return salesAmount;
    }

    public void setSalesAmount(int salesAmount)
    {
        this.salesAmount = salesAmount;
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

    public String getLoveCode()
    {
        return loveCode;
    }

    public void setLoveCode(String loveCode)
    {
        this.loveCode = loveCode;
    }
}
