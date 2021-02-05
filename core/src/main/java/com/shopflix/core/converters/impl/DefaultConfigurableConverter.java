package com.shopflix.core.converters.impl;


import com.shopflix.core.converters.ConfigurableConverter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;

@Component(value = "cxConverter")
public class DefaultConfigurableConverter<SOURCE, TARGET, OPTION> implements ConfigurableConverter<SOURCE, TARGET, OPTION>
{
    private static final Logger LOG = LoggerFactory.getLogger(DefaultConfigurableConverter.class);
    private Collection<OPTION> defaultOptions;
    private Class<TARGET> targetClass;
    private Map<OPTION, Populator<SOURCE, TARGET>> populators = new ConcurrentHashMap<>();

    @Override
    public TARGET convert(SOURCE source, TARGET target, Collection<OPTION> options)
    {
        this.populate(source, target, options);
        return target;
    }

    public void populate(SOURCE source, TARGET target, Collection<OPTION> options) {
        Assert.notNull(source, "Parameter [source] must not be null");
        Assert.notNull(target, "Parameter [target] must not be null");
        Assert.notEmpty(options, "Parameter [options] must not be empty");

        if (!CollectionUtils.isEmpty(this.getPopulators())) {
            Iterator<OPTION> iterator = options.iterator();

            while (iterator.hasNext()) {
                OPTION option = iterator.next();
                Populator<SOURCE, TARGET> populator = this.getPopulators().get(option);
                if (populator == null) {
                    LOG.warn("No populator configured for option [{}]", option);
                } else {
                    populator.populate(source, target);
                }
            }
        }
    }

    @Override
    public TARGET getTargetInstance(SOURCE var1)
    {
        return this.getTargetInstance();
    }

    public TARGET getTargetInstance() {
        try {
            return this.targetClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException var2) {
            throw new ConfigurationException("Instance of target class can't be created using default constructor", var2);
        }
    }

    @Override
    public Collection<OPTION> getDefaultOptions()
    {
        return this.defaultOptions;
    }

    public void setDefaultOptions(Collection<OPTION> defaultOptions)
    {
        Assert.notEmpty(defaultOptions, "Parameter [defaultOptions] must not be empty");
        this.defaultOptions = defaultOptions;
    }

    public Class<TARGET> getTargetClass()
    {
        return targetClass;
    }

    public void setTargetClass(Class<TARGET> targetClass)
    {
        validateParameterNotNull(targetClass, "targetClass can't be null");
        this.targetClass = targetClass;
        this.getTargetInstance();
    }

    public Map<OPTION, Populator<SOURCE, TARGET>> getPopulators()
    {
        return populators;
    }

    public void setPopulators(Map<OPTION, Populator<SOURCE, TARGET>> populators)
    {
        this.populators = populators;
    }
}
