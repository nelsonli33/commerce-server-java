package com.shopflix.core.converters;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public interface ConfigurableConverter<SOURCE, TARGET, OPTION> extends Converter<SOURCE, TARGET>
{
    default TARGET convert(SOURCE source)
    {
        return this.convert(source, this.getTargetInstance(source));
    }

    default TARGET convert(SOURCE source, TARGET target)
    {
        return this.convert(source, target, this.getDefaultOptions());
    }

    default TARGET convert(SOURCE source, Collection<OPTION> options)
    {
        return this.convert(source, this.getTargetInstance(source), options);
    }

    default List<TARGET> convertAll(Collection<? extends SOURCE> sources, OPTION... options)
    {
        return options != null ? this.convertAll(sources, Arrays.asList(options)) : Collections.emptyList();
    }

    default List<TARGET> convertAll(Collection<? extends SOURCE> sources, Collection<OPTION> options)
    {
        return !CollectionUtils.isEmpty(sources) && !CollectionUtils.isEmpty(options) ? sources.stream().map((s) ->
        {
            return this.convert(s, options);
        }).filter(Objects::nonNull).collect(Collectors.toList()) : Collections.emptyList();
    }

    TARGET convert(SOURCE var1, TARGET var2, Collection<OPTION> var3);

    TARGET getTargetInstance(SOURCE var1);

    Collection<OPTION> getDefaultOptions();
}
