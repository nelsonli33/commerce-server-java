package com.shopflix.merchant.data;

import com.shopflix.core.data.ProductImageData;

import java.util.List;

public class ProductOptionValueData
{
    private Long id;
    private String label;
    private Integer position;
    private List<ProductImageData> images;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public Integer getPosition()
    {
        return position;
    }

    public void setPosition(Integer position)
    {
        this.position = position;
    }

    public List<ProductImageData> getImages()
    {
        return images;
    }

    public void setImages(List<ProductImageData> images)
    {
        this.images = images;
    }
}
