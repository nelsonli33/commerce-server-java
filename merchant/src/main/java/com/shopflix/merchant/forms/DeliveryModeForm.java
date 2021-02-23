package com.shopflix.merchant.forms;

import java.util.List;

public class DeliveryModeForm
{
    private Long id;

    private String code;

    private String name;

    private String modeType;

    private String modeSubType;

    private String temperatureType;

    private Boolean active;

    private List<DeliveryModeValueForm> tierConditions;

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

    public List<DeliveryModeValueForm> getTierConditions()
    {
        return tierConditions;
    }

    public void setTierConditions(List<DeliveryModeValueForm> tierConditions)
    {
        this.tierConditions = tierConditions;
    }
}
