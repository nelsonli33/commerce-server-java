package com.shopflix.core.converters;
import com.shopflix.core.enums.PersistableEnum;
import javax.persistence.AttributeConverter;

public abstract class AbstractEnumConverter<T extends Enum<T> & PersistableEnum, E>
        implements AttributeConverter<T, String>
{
    private final Class<T> clazz;

    public AbstractEnumConverter(Class<T> clazz) {
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
        T[] enums = clazz.getEnumConstants();
        for (T e : enums) {
            if (e.getCode().toLowerCase().equals(dbData)) {
                return e;
            }
        }

        throw new UnsupportedOperationException(
                String.format("%s cannot convert to %s enum value.", dbData, clazz));
    }
}
