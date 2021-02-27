package com.shopflix.core.converters;

import com.shopflix.core.enums.PersistableIntegerEnum;
import com.shopflix.core.enums.PersistableStringEnum;

import javax.persistence.AttributeConverter;

public abstract class AbstractIntegerEnumConverter<T extends Enum<T> & PersistableIntegerEnum, E>
        implements AttributeConverter<T, Integer>
{
    private final Class<T> clazz;

    public AbstractIntegerEnumConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Integer convertToDatabaseColumn(T attribute)
    {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public T convertToEntityAttribute(Integer dbData)
    {
        if (dbData != null) {
            T[] enums = clazz.getEnumConstants();
            for (T e : enums) {
                if (e.getValue().equals(dbData)) {
                    return e;
                }
            }

            throw new UnsupportedOperationException(
                    String.format("%s cannot convert to %s enum value.", dbData, clazz));
        }
        return null;
    }
}
