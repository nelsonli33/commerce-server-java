package com.shopflix.core.data.form;

import java.util.List;

public class ProductOptionForm {

    public Long id;
    public String displayName;
    public Integer position;
    public List<ProductOptionValueForm> optionValues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public List<ProductOptionValueForm> getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(List<ProductOptionValueForm> optionValues) {
        this.optionValues = optionValues;
    }
}
