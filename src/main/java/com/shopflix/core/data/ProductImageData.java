package com.shopflix.core.data;

import java.io.Serializable;

public class ProductImageData implements Serializable {

    private static final long serialVersionUID = -4723196236176145251L;

    public String image;
    public String alt;
    public Integer position;

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
}
