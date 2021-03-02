package com.shopflix.ecpayb2cinvoiceservices.data;

public class QueryIssueInvalidResultData extends BaseResultData
{
    private String IIS_Mer_ID;

    private String II_Invoice_No;

    private String II_Date;

    private String II_Upload_Status;

    private String II_Upload_Date;

    private String Reason;

    private String II_Seller_Identifier;

    private String II_Buyer_Identifier;

    public String getIIS_Mer_ID()
    {
        return IIS_Mer_ID;
    }

    public void setIIS_Mer_ID(String IIS_Mer_ID)
    {
        this.IIS_Mer_ID = IIS_Mer_ID;
    }

    public String getII_Invoice_No()
    {
        return II_Invoice_No;
    }

    public void setII_Invoice_No(String II_Invoice_No)
    {
        this.II_Invoice_No = II_Invoice_No;
    }

    public String getII_Date()
    {
        return II_Date;
    }

    public void setII_Date(String II_Date)
    {
        this.II_Date = II_Date;
    }

    public String getII_Upload_Status()
    {
        return II_Upload_Status;
    }

    public void setII_Upload_Status(String II_Upload_Status)
    {
        this.II_Upload_Status = II_Upload_Status;
    }

    public String getII_Upload_Date()
    {
        return II_Upload_Date;
    }

    public void setII_Upload_Date(String II_Upload_Date)
    {
        this.II_Upload_Date = II_Upload_Date;
    }

    public String getReason()
    {
        return Reason;
    }

    public void setReason(String reason)
    {
        Reason = reason;
    }

    public String getII_Seller_Identifier()
    {
        return II_Seller_Identifier;
    }

    public void setII_Seller_Identifier(String II_Seller_Identifier)
    {
        this.II_Seller_Identifier = II_Seller_Identifier;
    }

    public String getII_Buyer_Identifier()
    {
        return II_Buyer_Identifier;
    }

    public void setII_Buyer_Identifier(String II_Buyer_Identifier)
    {
        this.II_Buyer_Identifier = II_Buyer_Identifier;
    }
}
