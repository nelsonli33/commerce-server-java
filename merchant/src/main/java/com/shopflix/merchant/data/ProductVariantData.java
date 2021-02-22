package com.shopflix.merchant.data;

import java.math.BigDecimal;
import java.util.List;

public class ProductVariantData
{
    private Long id;
    private String name;
    private Long quantity;
    private BigDecimal price;
    private String sku;
    private String value1;
    private String value2;
    private List<Long> valueIds;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Long quantity)
    {
        this.quantity = quantity;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public String getSku()
    {
        return sku;
    }

    public void setSku(String sku)
    {
        this.sku = sku;
    }

    public String getValue1()
    {
        return value1;
    }

    public void setValue1(String value1)
    {
        this.value1 = value1;
    }

    public String getValue2()
    {
        return value2;
    }

    public void setValue2(String value2)
    {
        this.value2 = value2;
    }

    public List<Long> getValueIds()
    {
        return valueIds;
    }

    public void setValueIds(List<Long> valueIds)
    {
        this.valueIds = valueIds;
    }
}
