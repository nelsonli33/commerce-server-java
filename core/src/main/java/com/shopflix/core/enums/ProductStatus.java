package com.shopflix.core.enums;


import com.shopflix.core.converters.AbstractStringEnumConverter;

public enum ProductStatus implements PersistableStringEnum
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
    public static class Converter extends AbstractStringEnumConverter<ProductStatus, String>
    {
        public Converter() {
            super(ProductStatus.class);
        }
    }
}
