package com.shopflix.core.data.form;

import java.math.BigDecimal;

public class ProductVariantForm {
    public Long id;
    public String name;
    public BigDecimal price;
    public Integer quantity;
    public String sku;
    public Integer position;
    public String value1;
    public String value2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getValue1()
    {
        return value1;
    }

    public void setValue1(String value1)
    {
        this.value1 = value1;
    }

    public String getValue2()
    {
        return value2;
    }

    public void setValue2(String value2)
    {
        this.value2 = value2;
    }
}
