package com.shopflix.core.model.order.delivery;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shopflix.core.enums.DeliveryModeSubType;
import com.shopflix.core.enums.DeliveryModeType;
import com.shopflix.core.enums.TemperatureType;
import com.shopflix.core.model.ItemModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "deliverymodes")
public class DeliveryModeModel extends ItemModel
{
    private String code;
    private String name;

    private DeliveryModeType modeType;
    private DeliveryModeSubType modeSubType;
    private TemperatureType temperatureType;
    private Boolean active;

    @JsonManagedReference
    @OneToMany(mappedBy = "deliveryMode", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DeliveryModeValueModel> tierConditions = new ArrayList<>();;

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

    public DeliveryModeType getModeType()
    {
        return modeType;
    }

    public void setModeType(DeliveryModeType modeType)
    {
        this.modeType = modeType;
    }

    public DeliveryModeSubType getModeSubType()
    {
        return modeSubType;
    }

    public void setModeSubType(DeliveryModeSubType modeSubType)
    {
        this.modeSubType = modeSubType;
    }

    public TemperatureType getTemperatureType()
    {
        return temperatureType;
    }

    public void setTemperatureType(TemperatureType temperatureType)
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

    public List<DeliveryModeValueModel> getTierConditions()
    {
        return tierConditions;
    }

    public void setTierConditions(List<DeliveryModeValueModel> tierConditions)
    {
        this.tierConditions = tierConditions;
    }


}
