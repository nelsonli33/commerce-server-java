package com.shopflix.core.data;

import java.io.Serializable;
import java.util.List;

public class ProductOptionValueData implements Serializable {

    private static final long serialVersionUID = -8878626044036346949L;

    public String label;
    public Integer position;
    public List<ProductImageData> images;

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

    public List<ProductImageData> getImages() {
        return images;
    }

    public void setImages(List<ProductImageData> images) {
        this.images = images;
    }
}
