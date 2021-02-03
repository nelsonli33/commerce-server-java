package com.shopflix.core.converters.impl;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.converters.PopulatorList;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Populating converter that uses a list of configured populators to populate the target during conversion.
 *
 * @param <SOURCE> the type of the source object
 * @param <TARGET> the type of the destination object
 */


public class PopulatingConverter<SOURCE, TARGET> extends AbstractConverter<SOURCE, TARGET> implements
        PopulatorList<SOURCE, TARGET>
{
    private final static Logger LOG = LoggerFactory.getLogger(PopulatingConverter.class);
    private List<Populator<SOURCE, TARGET>> populators;

    @Override
    public List<Populator<SOURCE, TARGET>> getPopulators()
    {
        return populators;
    }

    @Override
    public void setPopulators(List<Populator<SOURCE, TARGET>> populators)
    {
        this.populators = populators;
    }

    @Override
    public void populate(SOURCE source, TARGET target)
    {
        final List<Populator<SOURCE, TARGET>> populatorList = getPopulators();

        if (populatorList == null)
        {
            return;
        }

        for (final Populator<SOURCE, TARGET> populator : populatorList)
        {
            if (populator != null)
            {
                populator.populate(source, target);
            }
        }
    }


    // execute when BEAN name is known
    @PostConstruct
    public void removePopulatorsDuplicates()
    {
        // check for populators duplicates
        if (CollectionUtils.isNotEmpty(populators))
        {
            final LinkedHashSet<Populator<SOURCE, TARGET>> distinctPopulators = new LinkedHashSet<>();

            for (final Populator<SOURCE, TARGET> populator : populators)
            {
                if (!distinctPopulators.add(populator))
                {
                    LOG.warn("Duplicate populator entry [" + populator.getClass().getName() + "] found for converter "
                            + getMyBeanName() + "! The duplication has been removed.");
                }
            }
            this.populators = new ArrayList<>(distinctPopulators);
        }
        else
        {
            LOG.warn("Empty populators list found for converter " + getMyBeanName() + "!");
        }
    }
}
