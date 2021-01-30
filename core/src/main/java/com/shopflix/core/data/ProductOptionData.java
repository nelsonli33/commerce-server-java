package com.shopflix.core.data;

import java.io.Serializable;
import java.util.List;

public class ProductOptionData implements Serializable {

    private static final long serialVersionUID = 4514913635353943488L;

    public String displayName;
    public List<ProductOptionValueData[]> optionValues;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<ProductOptionValueData[]> getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(List<ProductOptionValueData[]> optionValues) {
        this.optionValues = optionValues;
    }
}
