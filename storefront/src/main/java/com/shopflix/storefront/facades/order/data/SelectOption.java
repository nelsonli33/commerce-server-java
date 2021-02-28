package com.shopflix.storefront.facades.order.data;

public class SelectOption
{
    private final String code;
    private final String name;

    public SelectOption(final String code, final String name)
    {
        this.code = code;
        this.name = name;
    }

    public String getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }
}
