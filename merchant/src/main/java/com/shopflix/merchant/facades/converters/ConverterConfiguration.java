package com.shopflix.merchant.facades.converters;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.converters.impl.PopulatingConverter;
import com.shopflix.core.data.MediaImageData;
import com.shopflix.core.model.category.CategoryModel;
import com.shopflix.core.model.media.MediaImageModel;
import com.shopflix.core.model.navigation.CMSNavigationLinkModel;
import com.shopflix.core.model.navigation.CMSNavigationModel;
import com.shopflix.merchant.data.CategoryData;
import com.shopflix.merchant.data.NavigationData;
import com.shopflix.merchant.data.NavigationLinkData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan(value = "com.shopflix.merchant")
public class ConverterConfiguration
{


    @Bean(name = "mediaImageConverter")
    public Converter<MediaImageModel, MediaImageData> mediaImageConverter(
            @Qualifier(value = "mediaImagePopulator") Populator<MediaImageModel, MediaImageData> mediaImagePopulator
    ) {
        PopulatingConverter<MediaImageModel, MediaImageData> converter = new PopulatingConverter<>();
        converter.setTargetClass(MediaImageData.class);
        converter.setPopulators(Arrays.asList(mediaImagePopulator));
        return converter;
    }

    @Bean(name = "categoryConverter")
    public Converter<CategoryModel, CategoryData> categoryConverter(
            @Qualifier("categoryPopulator") Populator<CategoryModel, CategoryData> categoryPopulator
    ) {
        PopulatingConverter<CategoryModel, CategoryData> converter = new PopulatingConverter<>();
        converter.setTargetClass(CategoryData.class);
        converter.setPopulators(Arrays.asList(categoryPopulator));
        return converter;
    }

    @Bean(name = "navigationConverter")
    public Converter<CMSNavigationModel, NavigationData> navigationConverter(
            @Qualifier("navigationPopulator") Populator<CMSNavigationModel, NavigationData> navigationPopulator
    ) {
        PopulatingConverter<CMSNavigationModel, NavigationData> converter = new PopulatingConverter<>();
        converter.setTargetClass(NavigationData.class);
        converter.setPopulators(Arrays.asList(navigationPopulator));
        return converter;
    }

    @Bean(name = "navigationLinkConverter")
    public Converter<CMSNavigationLinkModel, NavigationLinkData> navigationLinkConverter(
            @Qualifier("navigationLinkPopulator") Populator<CMSNavigationLinkModel, NavigationLinkData> navigationLinkPopulator
    ) {
        PopulatingConverter<CMSNavigationLinkModel, NavigationLinkData> converter = new PopulatingConverter<>();
        converter.setTargetClass(NavigationLinkData.class);
        converter.setPopulators(Arrays.asList(navigationLinkPopulator));
        return converter;
    }


}
