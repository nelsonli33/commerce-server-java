package com.shopflix.core.enums;

import com.shopflix.core.converters.AbstractEnumConverter;

public enum DeliveryModeSubType implements PersistableEnum
{
    TCAT("TCAT"),

    FAMIC2C("FAMIC2C"),

    UNIMARTC2C("UNIMARTC2C"),

    HILIFEC2C("HILIFEC2C"),

    OKMARTC2C("OKMARTC2C");

    private String code;

    DeliveryModeSubType(String code)
    {
        this.code = code;
    }

    @Override
    public String getCode()
    {
        return code;
    }


    public static DeliveryModeSubType from(String code) {
        for(final DeliveryModeSubType e : DeliveryModeSubType.values()) {
            if (e.getCode().equalsIgnoreCase(code)) {
                return e;
            }
        }
        // TODO: add enum converter exeption
        return null;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractEnumConverter<DeliveryModeSubType, String>
    {
        public Converter() {
            super(DeliveryModeSubType.class);
        }
    }
}
