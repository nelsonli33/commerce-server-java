package com.shopflix.merchant.data;

import com.shopflix.core.enums.PersistableEnum;

public enum ProductOption implements PersistableEnum
{
    BASIC("BASIC"),
    FULL("FULL");

    private String code;

    ProductOption(String code)
    {
        this.code = code.intern();
    }

    @Override
    public String getCode()
    {
        return this.code;
    }
}
