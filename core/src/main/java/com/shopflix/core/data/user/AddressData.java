package com.shopflix.core.data.user;

import java.io.Serializable;

public class AddressData implements Serializable
{
    private static final long serialVersionUID = -8410447198730770509L;

    private Long id;

    private String name;

    private String phone;

    private String city;

    private String district;

    private String zipcode;

    private String address;

    private String storeId;

    private String storeName;

    private boolean defaultAddress;

    private Integer deliveryAddressType;


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
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

    public boolean isDefaultAddress()
    {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress)
    {
        this.defaultAddress = defaultAddress;
    }


    public Integer getDeliveryAddressType()
    {
        return deliveryAddressType;
    }

    public void setDeliveryAddressType(Integer deliveryAddressType)
    {
        this.deliveryAddressType = deliveryAddressType;
    }
}
