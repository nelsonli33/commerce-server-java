package com.shopflix.core.data.form;

import java.util.List;

public class ProductOptionValueForm {

    public Long id;
    public String label;
    public Integer position;
    public List<ProductImageForm> images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public List<ProductImageForm> getImages()
    {
        return images;
    }

    public void setImages(List<ProductImageForm> images)
    {
        this.images = images;
    }
}
