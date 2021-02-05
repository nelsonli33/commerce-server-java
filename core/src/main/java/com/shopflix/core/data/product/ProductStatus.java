package com.shopflix.core.data.product;


import com.shopflix.core.converters.AbstractEnumConverter;
import com.shopflix.core.enums.PersistableEnum;

public enum ProductStatus implements PersistableEnum
{
    ACTIVE("ACTIVE"),
    ARCHIVED("ARCHIVED"),
    DRAFT("DRAFT");

    private final String code;

    ProductStatus(String code)
    {
        this.code = code.intern();
    }

    public static ProductStatus from(String code) {
        return ProductStatus.valueOf(code.toUpperCase());
    }

    public String getCode()
    {
        return code;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractEnumConverter<ProductStatus, String>
    {
        public Converter() {
            super(ProductStatus.class);
        }
    }
}
