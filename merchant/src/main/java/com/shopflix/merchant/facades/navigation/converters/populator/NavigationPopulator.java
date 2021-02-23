package com.shopflix.merchant.facades.navigation.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.navigation.CMSNavigationModel;
import com.shopflix.merchant.data.NavigationData;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

public class NavigationPopulator implements Populator<CMSNavigationModel, NavigationData>
{
    @Override
    public void populate(CMSNavigationModel source, NavigationData target) throws ConversionException
    {
        Assert.notNull(source, "Parameter cmsnavigationmodel cannot be null.");
        Assert.notNull(target, "Parameter navigationdata cannot be null.");

        target.setId(source.getId());
        target.setCode(source.getCode());
        target.setName(source.getName());
    }
}
