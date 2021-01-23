package com.shopflix.core.model.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shopflix.core.model.ItemModel;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productoptionvalues")
@DynamicUpdate
public class ProductOptionValueModel extends ItemModel {
    private String label;
    private Integer position;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private ProductOptionModel option;

    @OneToMany(mappedBy = "optionValue")
    private List<ProductImageModel> images = new ArrayList<>();

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

    public List<ProductImageModel> getImages() {
        return images;
    }

    public void setImages(List<ProductImageModel> images) {
        this.images = images;
    }

    public ProductOptionModel getOption() {
        return option;
    }

    public void setOption(ProductOptionModel option) {
        this.option = option;
    }


}
