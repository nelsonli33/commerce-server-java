package com.shopflix.core.converters;
import com.shopflix.core.enums.PersistableIntegerEnum;
import com.shopflix.core.enums.PersistableStringEnum;
import javax.persistence.AttributeConverter;

public abstract class AbstractStringEnumConverter<T extends Enum<T> & PersistableStringEnum, E>
        implements AttributeConverter<T, String>
{
    private final Class<T> clazz;

    public AbstractStringEnumConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String convertToDatabaseColumn(T attribute)
    {
        return attribute != null ? attribute.getCode().toLowerCase() : null;
    }

    @Override
    public T convertToEntityAttribute(String dbData)
    {
        if (dbData != null) {
            T[] enums = clazz.getEnumConstants();
            for (T e : enums) {
                if (e.getCode().toLowerCase().equals(dbData)) {
                    return e;
                }
            }

            throw new UnsupportedOperationException(
                    String.format("%s cannot convert to %s enum value.", dbData, clazz));
        }
        return null;
    }
}
