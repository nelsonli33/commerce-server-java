package com.shopflix.ecpayb2cinvoiceservices.data;

public enum InvoiceTag
{
    ISSUE("I"),

    ISSUE_INVALID("II"),

    ALLOWANCE("A"),

    ALLOWANCE_INVALID("AI"),

    WIN_LOTTERY("AW");

    private String code;

    InvoiceTag(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }
}
