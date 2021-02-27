package com.shopflix.core.enums;

import com.shopflix.core.converters.AbstractIntegerEnumConverter;

public enum DeliveryAddressType implements PersistableIntegerEnum
{
    HOME(1),

    CVSUNIMART(3),

    CVSFAMI(5),

    CVSHILIFE(7),

    CVSOKMART(9);


    DeliveryAddressType(Integer value)
    {
        this.value = value;
    }

    private Integer value;

    @Override
    public Integer getValue()
    {
        return value;
    }


    public static DeliveryAddressType from(Integer value) {
        for(final DeliveryAddressType e : DeliveryAddressType.values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return null;
    }

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractIntegerEnumConverter<DeliveryAddressType, Integer>
    {
        public Converter() {
            super(DeliveryAddressType.class);
        }
    }
}
