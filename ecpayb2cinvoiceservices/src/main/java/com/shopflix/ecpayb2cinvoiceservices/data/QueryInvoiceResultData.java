package com.shopflix.ecpayb2cinvoiceservices.data;

public class QueryInvoiceResultData extends BaseResultData
{
    private String IIS_Mer_ID;  // 合作特店編號

    private String IIS_Number; // 發票號碼

    private String IIS_Relate_Number; // 合作特店自訂編號 (訂單號碼)

    private String IIS_Customer_ID;

    private String IIS_Identifier; // 買方統編

    private String IIS_Customer_Name;

    private String IIS_Customer_Addr;

    private String IIS_Customer_Phone;

    private String IIS_Customer_Email;

    private String IIS_Clearance_Mark;

    private String IIS_Type;

    private String IIS_Category;

    private String IIS_Tax_Type;

    private String IIS_Tax_Rate;

    private String IIS_Tax_Amount;

    private String IIS_Sales_Amount;

    private String IIS_Check_Number;

    private String IIS_Carruer_Type;

    private String IIS_Carruer_Num;

    private String IIS_Love_Code;

    private String IIS_IP;

    private String IIS_Create_Date;

    private String IIS_Issue_Status;

    private String IIS_Invalid_Status;

    private String IIS_Upload_Status;

    private String IIS_Upload_Date;

    private String IIS_Turnkey_Status;

    private String IIS_Remain_Allowance_Amt;

    private String IIS_Print_Flag;

    private String IIS_Award_Flag;

    private String IIS_Award_Type;

    private String ItemName;

    private String ItemCount;

    private String ItemWord;

    private String ItemPrice;

    private String ItemTaxType;

    private String ItemAmount;

    private String ItemRemark;

    private String IIS_Random_Number;

    private String InvoiceRemark;

    private String PosBarCode;  // 用於顯示電子發票 BARCODE 用。(此回傳參 數僅供 POS 廠商專用)。

    private String QRCode_Left; // 用於顯示電子發票 QRCODE 左邊用的，必須 先在綠界設定密碼種子才會協助壓碼回 傳。(此回傳參數僅供 POS 廠商專用)。

    private String QRCode_Right; // 用於顯示電子發票 QRCODE 右邊用的，必 須先在綠界設定密碼種子才會協助壓碼回 傳。(此回傳參數僅供 POS 廠商專用)。


    public String getIIS_Mer_ID()
    {
        return IIS_Mer_ID;
    }

    public void setIIS_Mer_ID(String IIS_Mer_ID)
    {
        this.IIS_Mer_ID = IIS_Mer_ID;
    }

    public String getIIS_Number()
    {
        return IIS_Number;
    }

    public void setIIS_Number(String IIS_Number)
    {
        this.IIS_Number = IIS_Number;
    }

    public String getIIS_Relate_Number()
    {
        return IIS_Relate_Number;
    }

    public void setIIS_Relate_Number(String IIS_Relate_Number)
    {
        this.IIS_Relate_Number = IIS_Relate_Number;
    }

    public String getIIS_Customer_ID()
    {
        return IIS_Customer_ID;
    }

    public void setIIS_Customer_ID(String IIS_Customer_ID)
    {
        this.IIS_Customer_ID = IIS_Customer_ID;
    }

    public String getIIS_Identifier()
    {
        return IIS_Identifier;
    }

    public void setIIS_Identifier(String IIS_Identifier)
    {
        this.IIS_Identifier = IIS_Identifier;
    }

    public String getIIS_Customer_Name()
    {
        return IIS_Customer_Name;
    }

    public void setIIS_Customer_Name(String IIS_Customer_Name)
    {
        this.IIS_Customer_Name = IIS_Customer_Name;
    }

    public String getIIS_Customer_Addr()
    {
        return IIS_Customer_Addr;
    }

    public void setIIS_Customer_Addr(String IIS_Customer_Addr)
    {
        this.IIS_Customer_Addr = IIS_Customer_Addr;
    }

    public String getIIS_Customer_Phone()
    {
        return IIS_Customer_Phone;
    }

    public void setIIS_Customer_Phone(String IIS_Customer_Phone)
    {
        this.IIS_Customer_Phone = IIS_Customer_Phone;
    }

    public String getIIS_Customer_Email()
    {
        return IIS_Customer_Email;
    }

    public void setIIS_Customer_Email(String IIS_Customer_Email)
    {
        this.IIS_Customer_Email = IIS_Customer_Email;
    }

    public String getIIS_Clearance_Mark()
    {
        return IIS_Clearance_Mark;
    }

    public void setIIS_Clearance_Mark(String IIS_Clearance_Mark)
    {
        this.IIS_Clearance_Mark = IIS_Clearance_Mark;
    }

    public String getIIS_Type()
    {
        return IIS_Type;
    }

    public void setIIS_Type(String IIS_Type)
    {
        this.IIS_Type = IIS_Type;
    }

    public String getIIS_Category()
    {
        return IIS_Category;
    }

    public void setIIS_Category(String IIS_Category)
    {
        this.IIS_Category = IIS_Category;
    }

    public String getIIS_Tax_Type()
    {
        return IIS_Tax_Type;
    }

    public void setIIS_Tax_Type(String IIS_Tax_Type)
    {
        this.IIS_Tax_Type = IIS_Tax_Type;
    }

    public String getIIS_Tax_Rate()
    {
        return IIS_Tax_Rate;
    }

    public void setIIS_Tax_Rate(String IIS_Tax_Rate)
    {
        this.IIS_Tax_Rate = IIS_Tax_Rate;
    }

    public String getIIS_Tax_Amount()
    {
        return IIS_Tax_Amount;
    }

    public void setIIS_Tax_Amount(String IIS_Tax_Amount)
    {
        this.IIS_Tax_Amount = IIS_Tax_Amount;
    }

    public String getIIS_Sales_Amount()
    {
        return IIS_Sales_Amount;
    }

    public void setIIS_Sales_Amount(String IIS_Sales_Amount)
    {
        this.IIS_Sales_Amount = IIS_Sales_Amount;
    }

    public String getIIS_Check_Number()
    {
        return IIS_Check_Number;
    }

    public void setIIS_Check_Number(String IIS_Check_Number)
    {
        this.IIS_Check_Number = IIS_Check_Number;
    }

    public String getIIS_Carruer_Type()
    {
        return IIS_Carruer_Type;
    }

    public void setIIS_Carruer_Type(String IIS_Carruer_Type)
    {
        this.IIS_Carruer_Type = IIS_Carruer_Type;
    }

    public String getIIS_Carruer_Num()
    {
        return IIS_Carruer_Num;
    }

    public void setIIS_Carruer_Num(String IIS_Carruer_Num)
    {
        this.IIS_Carruer_Num = IIS_Carruer_Num;
    }

    public String getIIS_Love_Code()
    {
        return IIS_Love_Code;
    }

    public void setIIS_Love_Code(String IIS_Love_Code)
    {
        this.IIS_Love_Code = IIS_Love_Code;
    }

    public String getIIS_IP()
    {
        return IIS_IP;
    }

    public void setIIS_IP(String IIS_IP)
    {
        this.IIS_IP = IIS_IP;
    }

    public String getIIS_Create_Date()
    {
        return IIS_Create_Date;
    }

    public void setIIS_Create_Date(String IIS_Create_Date)
    {
        this.IIS_Create_Date = IIS_Create_Date;
    }

    public String getIIS_Issue_Status()
    {
        return IIS_Issue_Status;
    }

    public void setIIS_Issue_Status(String IIS_Issue_Status)
    {
        this.IIS_Issue_Status = IIS_Issue_Status;
    }

    public String getIIS_Invalid_Status()
    {
        return IIS_Invalid_Status;
    }

    public void setIIS_Invalid_Status(String IIS_Invalid_Status)
    {
        this.IIS_Invalid_Status = IIS_Invalid_Status;
    }

    public String getIIS_Upload_Status()
    {
        return IIS_Upload_Status;
    }

    public void setIIS_Upload_Status(String IIS_Upload_Status)
    {
        this.IIS_Upload_Status = IIS_Upload_Status;
    }

    public String getIIS_Upload_Date()
    {
        return IIS_Upload_Date;
    }

    public void setIIS_Upload_Date(String IIS_Upload_Date)
    {
        this.IIS_Upload_Date = IIS_Upload_Date;
    }

    public String getIIS_Turnkey_Status()
    {
        return IIS_Turnkey_Status;
    }

    public void setIIS_Turnkey_Status(String IIS_Turnkey_Status)
    {
        this.IIS_Turnkey_Status = IIS_Turnkey_Status;
    }

    public String getIIS_Remain_Allowance_Amt()
    {
        return IIS_Remain_Allowance_Amt;
    }

    public void setIIS_Remain_Allowance_Amt(String IIS_Remain_Allowance_Amt)
    {
        this.IIS_Remain_Allowance_Amt = IIS_Remain_Allowance_Amt;
    }

    public String getIIS_Print_Flag()
    {
        return IIS_Print_Flag;
    }

    public void setIIS_Print_Flag(String IIS_Print_Flag)
    {
        this.IIS_Print_Flag = IIS_Print_Flag;
    }

    public String getIIS_Award_Flag()
    {
        return IIS_Award_Flag;
    }

    public void setIIS_Award_Flag(String IIS_Award_Flag)
    {
        this.IIS_Award_Flag = IIS_Award_Flag;
    }

    public String getIIS_Award_Type()
    {
        return IIS_Award_Type;
    }

    public void setIIS_Award_Type(String IIS_Award_Type)
    {
        this.IIS_Award_Type = IIS_Award_Type;
    }

    public String getItemName()
    {
        return ItemName;
    }

    public void setItemName(String itemName)
    {
        ItemName = itemName;
    }

    public String getItemCount()
    {
        return ItemCount;
    }

    public void setItemCount(String itemCount)
    {
        ItemCount = itemCount;
    }

    public String getItemWord()
    {
        return ItemWord;
    }

    public void setItemWord(String itemWord)
    {
        ItemWord = itemWord;
    }

    public String getItemPrice()
    {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice)
    {
        ItemPrice = itemPrice;
    }

    public String getItemTaxType()
    {
        return ItemTaxType;
    }

    public void setItemTaxType(String itemTaxType)
    {
        ItemTaxType = itemTaxType;
    }

    public String getItemAmount()
    {
        return ItemAmount;
    }

    public void setItemAmount(String itemAmount)
    {
        ItemAmount = itemAmount;
    }

    public String getItemRemark()
    {
        return ItemRemark;
    }

    public void setItemRemark(String itemRemark)
    {
        ItemRemark = itemRemark;
    }

    public String getIIS_Random_Number()
    {
        return IIS_Random_Number;
    }

    public void setIIS_Random_Number(String IIS_Random_Number)
    {
        this.IIS_Random_Number = IIS_Random_Number;
    }

    public String getInvoiceRemark()
    {
        return InvoiceRemark;
    }

    public void setInvoiceRemark(String invoiceRemark)
    {
        InvoiceRemark = invoiceRemark;
    }

    public String getPosBarCode()
    {
        return PosBarCode;
    }

    public void setPosBarCode(String posBarCode)
    {
        PosBarCode = posBarCode;
    }

    public String getQRCode_Left()
    {
        return QRCode_Left;
    }

    public void setQRCode_Left(String QRCode_Left)
    {
        this.QRCode_Left = QRCode_Left;
    }

    public String getQRCode_Right()
    {
        return QRCode_Right;
    }

    public void setQRCode_Right(String QRCode_Right)
    {
        this.QRCode_Right = QRCode_Right;
    }
}
