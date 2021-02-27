package com.shopflix.core.enums;

import com.shopflix.core.converters.AbstractStringEnumConverter;

public enum TemperatureType implements PersistableStringEnum
{
    NORMAL("0001"),

    REFRIGERATOR("0002"),

    FREEZE("0003");

    private String code;

    TemperatureType(String code)
    {
        this.code = code;
    }

    @Override
    public String getCode()
    {
        return code;
    }

    public static TemperatureType from(String code) {
        for(final TemperatureType e : TemperatureType.values()) {
            if (e.getCode().equalsIgnoreCase(code)) {
                return e;
            }
        }
        return null;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractStringEnumConverter<TemperatureType, String>
    {
        public Converter() {
            super(TemperatureType.class);
        }
    }
}
