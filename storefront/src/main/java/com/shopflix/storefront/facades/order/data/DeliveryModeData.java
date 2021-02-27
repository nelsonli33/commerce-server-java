package com.shopflix.storefront.facades.order.data;

import java.io.Serializable;

public class DeliveryModeData implements Serializable
{
    private static final long serialVersionUID = 6064093177832213143L;

    private String code;

    private String name;

    private String description;

    private Double deliveryCost;


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

    public Double getDeliveryCost()
    {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost)
    {
        this.deliveryCost = deliveryCost;
    }
}
