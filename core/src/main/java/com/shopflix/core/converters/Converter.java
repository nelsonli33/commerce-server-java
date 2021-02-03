package com.shopflix.core.converters;


import com.shopflix.core.exception.ConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;

public interface Converter<SOURCE, TARGET> {

    TARGET convert(SOURCE var1) throws ConversionException;

    default List<TARGET> convertAll(Collection<? extends SOURCE> sources) throws ConversionException {
        if (CollectionUtils.isEmpty(sources)) {
            return Collections.emptyList();
        } else {
            List<TARGET> result = new ArrayList<>(sources.size());

            for (SOURCE source : sources) {
                result.add(this.convert(source));
            }

            return result;
        }
    }

    default List<TARGET> convertAllIgnoreExceptions(Collection<? extends SOURCE> sources) {
        if (CollectionUtils.isEmpty(sources)) {
            return Collections.emptyList();
        } else {
            List<TARGET> result = new ArrayList<>(sources.size());

            for (SOURCE source : sources) {
                try {
                    result.add(this.convert(source));
                } catch (ConversionException var4) {
                    this.getLogger().warn("Exception while converting object!", var4);
                }
            }

            return result;
        }
    }

    default Logger getLogger() { return LoggerFactory.getLogger(this.getClass()); }


}
