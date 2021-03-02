package com.shopflix.ecpayb2cinvoiceservices.data;

public class AllowanceByCollegiateResultData extends BaseResultData
{
    private String IA_Allow_No; // 折讓單號

    private String IA_Invoice_No; // 當初開立發票號碼

    private String IA_TempDate; // 線上折讓時間

    private String IA_TempExpireDate; // 線上折讓同意到期日

    private String IA_Remain_Allowance_Amt; // 折讓剩餘金額

    public String getIA_Allow_No()
    {
        return IA_Allow_No;
    }

    public void setIA_Allow_No(String IA_Allow_No)
    {
        this.IA_Allow_No = IA_Allow_No;
    }

    public String getIA_Invoice_No()
    {
        return IA_Invoice_No;
    }

    public void setIA_Invoice_No(String IA_Invoice_No)
    {
        this.IA_Invoice_No = IA_Invoice_No;
    }

    public String getIA_TempDate()
    {
        return IA_TempDate;
    }

    public void setIA_TempDate(String IA_TempDate)
    {
        this.IA_TempDate = IA_TempDate;
    }

    public String getIA_TempExpireDate()
    {
        return IA_TempExpireDate;
    }

    public void setIA_TempExpireDate(String IA_TempExpireDate)
    {
        this.IA_TempExpireDate = IA_TempExpireDate;
    }

    public String getIA_Remain_Allowance_Amt()
    {
        return IA_Remain_Allowance_Amt;
    }

    public void setIA_Remain_Allowance_Amt(String IA_Remain_Allowance_Amt)
    {
        this.IA_Remain_Allowance_Amt = IA_Remain_Allowance_Amt;
    }
}
