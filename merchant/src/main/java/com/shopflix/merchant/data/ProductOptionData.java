package com.shopflix.merchant.data;

import java.util.List;

public class ProductOptionData
{
    private Long id;
    private String displayName;
    private Integer position;
    private List<ProductOptionValueData> optionValues;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public Integer getPosition()
    {
        return position;
    }

    public void setPosition(Integer position)
    {
        this.position = position;
    }

    public List<ProductOptionValueData> getOptionValues()
    {
        return optionValues;
    }

    public void setOptionValues(List<ProductOptionValueData> optionValues)
    {
        this.optionValues = optionValues;
    }
}
