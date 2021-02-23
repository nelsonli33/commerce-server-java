package com.shopflix.core.enums;

import com.shopflix.core.converters.AbstractEnumConverter;

public enum DeliveryModeType implements PersistableEnum
{
    HOME("HOME"),

    CVS("CVS");

    private String code;

    DeliveryModeType(String code)
    {
        this.code = code;
    }

    @Override
    public String getCode()
    {
        return code;
    }

    public static DeliveryModeType from(String code) {
        for(final DeliveryModeType e : DeliveryModeType.values()) {
            if (e.getCode().equalsIgnoreCase(code)) {
                return e;
            }
        }
        // TODO: add enum converter exeption
        return null;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractEnumConverter<DeliveryModeType, String>
    {
        public Converter() {
            super(DeliveryModeType.class);
        }
    }
}
