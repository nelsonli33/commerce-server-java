package com.shopflix.merchant.facades.converters;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.converters.impl.PopulatingConverter;
import com.shopflix.core.model.navigation.CMSNavigationLinkModel;
import com.shopflix.core.model.navigation.CMSNavigationModel;
import com.shopflix.merchant.data.NavigationData;
import com.shopflix.merchant.data.NavigationLinkData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Arrays;

@Configuration
public class ConverterConfiguration
{

    @Resource(name = "navigationPopulator")
    private Populator<CMSNavigationModel, NavigationData> navigationPopulator;

    @Resource(name = "navigationLinkPopulator")
    private Populator<CMSNavigationLinkModel, NavigationLinkData> navigationLinkPopulator;

    @Bean(name = "navigationConverter")
    public Converter<CMSNavigationModel, NavigationData> navigationConverter() {
        PopulatingConverter<CMSNavigationModel, NavigationData> converter = new PopulatingConverter<>();
        converter.setTargetClass(NavigationData.class);
        converter.setPopulators(Arrays.asList(navigationPopulator));
        return converter;
    }

    @Bean(name = "navigationLinkConverter")
    public Converter<CMSNavigationLinkModel, NavigationLinkData> navigationLinkConverter() {
        PopulatingConverter<CMSNavigationLinkModel, NavigationLinkData> converter = new PopulatingConverter<>();
        converter.setTargetClass(NavigationLinkData.class);
        converter.setPopulators(Arrays.asList(navigationLinkPopulator));
        return converter;
    }

}
