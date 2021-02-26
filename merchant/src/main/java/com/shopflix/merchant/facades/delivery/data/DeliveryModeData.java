package com.shopflix.merchant.facades.delivery.data;

import java.io.Serializable;
import java.util.List;

public class DeliveryModeData implements Serializable
{
    private static final long serialVersionUID = 4354964261941869246L;

    private Long id;

    private String code;

    private String name;

    private String modeType;

    private String modeSubType;

    private Integer deliveryAddressType;

    private String temperatureType;

    private Boolean active;

    private List<DeliveryModeValueData> tierConditions;

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

    public String getModeType()
    {
        return modeType;
    }

    public void setModeType(String modeType)
    {
        this.modeType = modeType;
    }

    public String getModeSubType()
    {
        return modeSubType;
    }

    public Integer getDeliveryAddressType()
    {
        return deliveryAddressType;
    }

    public void setDeliveryAddressType(Integer deliveryAddressType)
    {
        this.deliveryAddressType = deliveryAddressType;
    }

    public void setModeSubType(String modeSubType)
    {
        this.modeSubType = modeSubType;
    }

    public String getTemperatureType()
    {
        return temperatureType;
    }

    public void setTemperatureType(String temperatureType)
    {
        this.temperatureType = temperatureType;
    }

    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    public List<DeliveryModeValueData> getTierConditions()
    {
        return tierConditions;
    }

    public void setTierConditions(List<DeliveryModeValueData> tierConditions)
    {
        this.tierConditions = tierConditions;
    }
}
