package com.shopflix.merchant.data;

import com.shopflix.core.enums.PersistableStringEnum;

public enum ProductOption implements PersistableStringEnum
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
