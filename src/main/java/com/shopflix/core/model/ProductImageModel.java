package com.shopflix.core.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productimages")
public class ProductImageModel extends ItemModel {
    private String image;
    private String alt;
    private Integer position;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductOptionValueModel optionValue;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductModel product;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public ProductOptionValueModel getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(ProductOptionValueModel optionValue) {
        this.optionValue = optionValue;
    }
}
