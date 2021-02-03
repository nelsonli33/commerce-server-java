package com.shopflix.merchant.facades.navigation.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.navigation.CMSNavigationLinkModel;
import com.shopflix.merchant.data.NavigationLinkData;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component(value = "navigationLinkPopulator")
public class DefaultNavigationLinkPopulator implements Populator<CMSNavigationLinkModel, NavigationLinkData>
{
    @Override
    public void populate(CMSNavigationLinkModel source, NavigationLinkData target) throws ConversionException
    {
        Assert.notNull(source, "Parameter navigation link model cannot be null.");
        Assert.notNull(target, "Parameter navigation link data cannot be null.");

        target.setId(source.getId());
        target.setName(source.getName());
        target.setUrl(source.getUrl());
        target.setTarget(source.getTarget());
        target.setSortOrder(source.getSortOrder());
        target.setParentId(source.getParentId());
    }
}
