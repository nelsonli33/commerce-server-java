package com.shopflix.core.converters;

import com.shopflix.core.exception.ConversionException;

import java.util.Collection;

/**
 * Interface for a configurable populator. A populator sets values in a target instance based on values in the source
 * instance. Populators are similar to converters except that unlike converters the target instance must already exist.
 * The collection of options is used to control what data is populated.
 *
 * @param <SOURCE>
 *           the type of the source object
 * @param <TARGET>
 *           the type of the destination object
 * @param <OPTION>
 *           the type of the options list
 */
public interface ConfigurablePopulator<SOURCE, TARGET, OPTION>
{
    /**
     * Populate the target instance from the source instance. The collection of options is used to control what data is
     * populated.
     *
     * @param source
     *           the source object
     * @param target
     *           the target to fill
     * @param options
     *           options used to control what data is populated
     * @throws com.shopflix.core.exception.ConversionException
     *            if an error occurs
     */
    void populate(SOURCE source, TARGET target, Collection<OPTION> options) throws ConversionException;
}
