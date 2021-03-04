package com.shopflix.core.enums;

import com.shopflix.core.converters.AbstractStringEnumConverter;

public enum InvoiceType implements PersistableStringEnum
{
    PERSON("Person"),

    COMPANY("Company"),

    DONATION("Donation");

    private String code;

    InvoiceType(String code)
    {
        this.code = code;
    }

    @Override
    public String getCode()
    {
        return code;
    }

    public static InvoiceType from(String code) {
        for(final InvoiceType e : InvoiceType.values()) {
            if (e.getCode().equalsIgnoreCase(code)) {
                return e;
            }
        }
        return null;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractStringEnumConverter<InvoiceType, String>
    {
        public Converter() {
            super(InvoiceType.class);
        }
    }
}
