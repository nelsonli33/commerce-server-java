package com.shopflix.merchant.config;

import com.shopflix.core.converters.ConfigurableConverter;
import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.converters.impl.DefaultConfigurableConverter;
import com.shopflix.core.converters.impl.DefaultPopulatorList;
import com.shopflix.core.converters.impl.PopulatingConverter;
import com.shopflix.core.data.MediaImageData;
import com.shopflix.core.data.ProductImageData;
import com.shopflix.core.model.category.CategoryModel;
import com.shopflix.core.model.media.MediaImageModel;
import com.shopflix.core.model.navigation.CMSNavigationLinkModel;
import com.shopflix.core.model.navigation.CMSNavigationModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.core.model.order.delivery.DeliveryModeValueModel;
import com.shopflix.core.model.product.ProductImageModel;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.model.product.ProductOptionModel;
import com.shopflix.core.model.product.ProductOptionValueModel;
import com.shopflix.core.repository.delivery.DeliveryModeRepository;
import com.shopflix.merchant.data.*;
import com.shopflix.merchant.facades.delivery.converters.populator.MerchantDeliveryModePopulator;
import com.shopflix.merchant.facades.delivery.converters.populator.MerchantDeliveryModeValuePopulator;
import com.shopflix.merchant.facades.delivery.data.DeliveryModeData;
import com.shopflix.merchant.facades.delivery.data.DeliveryModeValueData;
import com.shopflix.merchant.facades.delivery.impl.DefaultMerchantDeliveryModeFacade;
import com.shopflix.merchant.service.delivery.impl.DefaultMerchantDeliveryModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@ComponentScan(value = "com.shopflix.merchant")
public class MerchantAppConfig
{

    @Autowired
    public DeliveryModeRepository deliveryModeRepository;

    // <!--- services --->
    @Bean
    public DefaultMerchantDeliveryModeService merchantDeliveryModeService() {
        DefaultMerchantDeliveryModeService merchantDeliveryModeService = new DefaultMerchantDeliveryModeService();
        merchantDeliveryModeService.setDeliveryModeRepository(deliveryModeRepository);
        return merchantDeliveryModeService;
    }


    // <!--- facades --->
    @Bean
    public DefaultMerchantDeliveryModeFacade merchantDeliveryModeFacade() {
        DefaultMerchantDeliveryModeFacade merchantDeliveryModeFacade = new DefaultMerchantDeliveryModeFacade();
        merchantDeliveryModeFacade.setMerchantDeliveryModeService(merchantDeliveryModeService());
        merchantDeliveryModeFacade.setMerchantDeliveryModeConverter(merchantDeliveryModeConverter());
        return merchantDeliveryModeFacade;
    }





    // <!--- converters and populators --->
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




    // product
    @Bean(name = "productBasicPopulators")
    public Populator<ProductModel, ProductData> productBasicPopulators(
            @Qualifier("productBasicPopulator") Populator<ProductModel, ProductData> productBasicPopulator
    ) {
        DefaultPopulatorList<ProductModel, ProductData> populatorList = new DefaultPopulatorList<>();
        populatorList.setPopulators(Arrays.asList(productBasicPopulator));
        return populatorList;
    }

    @Bean(name = "productFullPopulators")
    public Populator<ProductModel, ProductData> productFullPopulators(
            @Qualifier("productBasicPopulator") Populator<ProductModel, ProductData> productBasicPopulator,
            @Qualifier("productDetailPopulator") Populator<ProductModel, ProductData> productDetailPopulator,
            @Qualifier("productCategoryPopulator") Populator<ProductModel, ProductData> productCategoryPopulator,
            @Qualifier("productSoldPopulator") Populator<ProductModel, ProductData> productSoldPopulator,
            @Qualifier("productOptionPopulator") Populator<ProductModel, ProductData> productOptionPopulator,
            @Qualifier("productVariantPopulator") Populator<ProductModel, ProductData> productVariantPopulator,
            @Qualifier("productImagePopulator") Populator<ProductModel, ProductData> productImagePopulator
    ) {
        DefaultPopulatorList<ProductModel, ProductData> populatorList = new DefaultPopulatorList<>();
        populatorList.setPopulators(Arrays.asList(
                productBasicPopulator,
                productDetailPopulator,
                productCategoryPopulator,
                productSoldPopulator,
                productOptionPopulator,
                productVariantPopulator,
                productImagePopulator
        ));
        return populatorList;
    }

    @Bean(name = "productConverter")
    public ConfigurableConverter<ProductModel, ProductData, ProductOption> productConverter(
            @Qualifier("productBasicPopulators") Populator<ProductModel, ProductData> productBasicPopulators,
            @Qualifier("productFullPopulators") Populator<ProductModel, ProductData> productFullPopulators
    ) {
        DefaultConfigurableConverter<ProductModel, ProductData, ProductOption> cxConverter = new DefaultConfigurableConverter<>();
        cxConverter.setTargetClass(ProductData.class);
        cxConverter.setDefaultOptions(Collections.singletonList(ProductOption.BASIC));

        Map<ProductOption, Populator<ProductModel, ProductData>> map = new ConcurrentHashMap<>();
        map.put(ProductOption.BASIC, productBasicPopulators);
        map.put(ProductOption.FULL, productFullPopulators);
        cxConverter.setPopulators(map);

        return cxConverter;
    }


    @Bean(name = "productInnerOptionConverter")
    public Converter<ProductOptionModel, ProductOptionData> productInnerOptionConverter(
            @Qualifier("productInnerOptionPopulator") Populator<ProductOptionModel, ProductOptionData> productOptionPopulator
    ) {
        PopulatingConverter<ProductOptionModel, ProductOptionData> converter = new PopulatingConverter<>();
        converter.setTargetClass(ProductOptionData.class);
        converter.setPopulators(Arrays.asList(productOptionPopulator));
        return converter;
    }

    @Bean(name = "productInnerOptionValueConverter")
    public Converter<ProductOptionValueModel, ProductOptionValueData> productInnerOptionValueConverter(
            @Qualifier("productInnerOptionValuePopulator") Populator<ProductOptionValueModel, ProductOptionValueData> productOptionValuePopulator
    ) {
        PopulatingConverter<ProductOptionValueModel, ProductOptionValueData> converter = new PopulatingConverter<>();
        converter.setTargetClass(ProductOptionValueData.class);
        converter.setPopulators(Arrays.asList(productOptionValuePopulator));
        return converter;
    }

    @Bean(name = "productInnerImageConverter")
    public Converter<ProductImageModel, ProductImageData> productInnerImageConverter(
            @Qualifier("productInnerImagePopulator") Populator<ProductImageModel, ProductImageData> productInnerImagePopulator
    ) {
        PopulatingConverter<ProductImageModel, ProductImageData> converter = new PopulatingConverter<>();
        converter.setTargetClass(ProductImageData.class);
        converter.setPopulators(Arrays.asList(productInnerImagePopulator));
        return converter;
    }


    @Bean
    public MerchantDeliveryModePopulator merchantDeliveryModePopulator() {
        MerchantDeliveryModePopulator populator = new MerchantDeliveryModePopulator();
        populator.setMerchantDeliveryModeValueConverter(merchantDeliveryModeValueConverter());
        return populator;
    }

    @Bean
    public Converter<DeliveryModeModel, DeliveryModeData> merchantDeliveryModeConverter() {
        PopulatingConverter<DeliveryModeModel, DeliveryModeData> converter = new PopulatingConverter<>();
        converter.setTargetClass(DeliveryModeData.class);
        converter.setPopulators(Arrays.asList(merchantDeliveryModePopulator()));
        return converter;
    }

    @Bean
    public MerchantDeliveryModeValuePopulator merchantDeliveryModeValuePopulator() {
        return new MerchantDeliveryModeValuePopulator();
    }

    @Bean
    public Converter<DeliveryModeValueModel, DeliveryModeValueData> merchantDeliveryModeValueConverter() {
        PopulatingConverter<DeliveryModeValueModel, DeliveryModeValueData> converter = new PopulatingConverter<>();
        converter.setTargetClass(DeliveryModeValueData.class);
        converter.setPopulators(Arrays.asList(merchantDeliveryModeValuePopulator()));
        return converter;
    }

}
