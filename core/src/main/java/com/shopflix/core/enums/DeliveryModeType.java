package com.shopflix.core.enums;

import com.shopflix.core.converters.AbstractStringEnumConverter;

public enum DeliveryModeType implements PersistableStringEnum
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
        return null;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractStringEnumConverter<DeliveryModeType, String>
    {
        public Converter() {
            super(DeliveryModeType.class);
        }
    }
}
