package com.shopflix.merchant.forms;

public class NavigationLinkForm
{
    private String type;
    private Integer valueId;
    private String name;
    private String url;
    private Boolean target;
    private Integer sortOrder;
    private Long parentId;


    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Integer getValueId()
    {
        return valueId;
    }

    public void setValueId(Integer valueId)
    {
        this.valueId = valueId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Boolean getTarget()
    {
        return target;
    }

    public void setTarget(Boolean target)
    {
        this.target = target;
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
}


