package com.shopflix.merchant.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopflix.core.data.MediaImageData;

public class CategoryData
{
    private Long id;
    private String code;
    private String name;
    private String description;
    private Integer sortOrder;
    private Long parentId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long imageId;
    private MediaImageData image;
    private String metaTitle;
    private String metaDescription;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getImageId()
    {
        return imageId;
    }

    public void setImageId(Long imageId)
    {
        this.imageId = imageId;
    }

    public MediaImageData getImage()
    {
        return image;
    }

    public void setImage(MediaImageData image)
    {
        this.image = image;
    }

    public String getMetaTitle()
    {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle)
    {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription()
    {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription)
    {
        this.metaDescription = metaDescription;
    }
}
