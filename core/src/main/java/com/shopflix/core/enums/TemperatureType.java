package com.shopflix.core.enums;

import com.shopflix.core.converters.AbstractEnumConverter;

public enum TemperatureType implements PersistableEnum
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
        // TODO: add enum converter exeption
        return null;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractEnumConverter<TemperatureType, String>
    {
        public Converter() {
            super(TemperatureType.class);
        }
    }
}
