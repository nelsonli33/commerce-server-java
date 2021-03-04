package com.shopflix.core.model.base;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shopflix.core.enums.DeliveryAddressType;
import com.shopflix.core.model.ItemModel;
import com.shopflix.core.model.base.CountryModel;
import com.shopflix.core.model.order.AbstractOrderModel;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "address_type", discriminatorType = DiscriminatorType.STRING)
public class AddressModel extends ItemModel
{
    private static final long serialVersionUID = 1717857156704638320L;

    @OneToOne
    private CountryModel country;
    private String city;
    private String district;
    private String zipcode;
    private String address1;
    private String address2;
    private String storeId;
    private String storeName;
    private String name;
    private String phone;
    private DeliveryAddressType deliveryAddressType;



    public CountryModel getCountry()
    {
        return country;
    }

    public void setCountry(CountryModel country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }

    public String getAddress1()
    {
        return address1;
    }

    public void setAddress1(String address1)
    {
        this.address1 = address1;
    }

    public String getAddress2()
    {
        return address2;
    }

    public void setAddress2(String address2)
    {
        this.address2 = address2;
    }

    public String getStoreId()
    {
        return storeId;
    }

    public void setStoreId(String storeId)
    {
        this.storeId = storeId;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public DeliveryAddressType getDeliveryAddressType()
    {
        return deliveryAddressType;
    }

    public void setDeliveryAddressType(DeliveryAddressType deliveryAddressType)
    {
        this.deliveryAddressType = deliveryAddressType;
    }
}
