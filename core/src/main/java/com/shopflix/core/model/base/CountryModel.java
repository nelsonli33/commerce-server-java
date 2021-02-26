package com.shopflix.core.model.base;

import com.shopflix.core.model.ItemModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class CountryModel extends ItemModel
{
    private String isocode;
    private Boolean active;


    public String getIsocode()
    {
        return isocode;
    }

    public void setIsocode(String isocode)
    {
        this.isocode = isocode;
    }

    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }
}
