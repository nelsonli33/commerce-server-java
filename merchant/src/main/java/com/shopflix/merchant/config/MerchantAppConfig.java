package com.shopflix.merchant.config;

import com.google.cloud.storage.Storage;
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
import com.shopflix.core.repository.category.CategoryRepository;
import com.shopflix.core.repository.delivery.DeliveryModeRepository;
import com.shopflix.core.repository.media.MediaImageRepository;
import com.shopflix.core.repository.media.MediaRepository;
import com.shopflix.core.repository.navigation.CMSNavigationLinkRepository;
import com.shopflix.core.repository.navigation.CMSNavigationRepository;
import com.shopflix.core.repository.product.*;
import com.shopflix.core.service.ModelService;
import com.shopflix.merchant.data.*;
import com.shopflix.merchant.facades.category.converters.populator.CategoryPopulator;
import com.shopflix.merchant.facades.category.converters.populator.CategoryReversePopulator;
import com.shopflix.merchant.facades.category.impl.DefaultMerchantCategoryFacade;
import com.shopflix.merchant.facades.delivery.converters.populator.MerchantDeliveryModePopulator;
import com.shopflix.merchant.facades.delivery.converters.populator.MerchantDeliveryModeValuePopulator;
import com.shopflix.merchant.facades.delivery.data.DeliveryModeData;
import com.shopflix.merchant.facades.delivery.data.DeliveryModeValueData;
import com.shopflix.merchant.facades.delivery.impl.DefaultMerchantDeliveryModeFacade;
import com.shopflix.merchant.facades.media.converters.populator.MediaImagePopulator;
import com.shopflix.merchant.facades.navigation.converters.populator.NavigationLinkPopulator;
import com.shopflix.merchant.facades.navigation.converters.populator.NavigationLinkReversePopulator;
import com.shopflix.merchant.facades.navigation.converters.populator.NavigationPopulator;
import com.shopflix.merchant.facades.navigation.impl.DefaultCMSNavigationFacade;
import com.shopflix.merchant.facades.product.converters.populator.*;
import com.shopflix.merchant.facades.product.impl.DefaultMerchantProductFacade;
import com.shopflix.merchant.service.category.impl.DefaultMerchantCategoryService;
import com.shopflix.merchant.service.delivery.impl.DefaultMerchantDeliveryModeService;
import com.shopflix.merchant.service.media.impl.DefaultMerchantMediaService;
import com.shopflix.merchant.service.media.impl.DefaultStorageService;
import com.shopflix.merchant.service.navigation.impl.DefaultMerchantNavigationService;
import com.shopflix.merchant.service.product.impl.DefaultMerchantProductImageService;
import com.shopflix.merchant.service.product.impl.DefaultMerchantProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@ComponentScan(value = "com.shopflix.merchant")
public class MerchantAppConfig
{
    @Value("${storage.bucketname}")
    private String BucketName;

    @Autowired
    private Storage storage;

    @Autowired
    public DeliveryModeRepository deliveryModeRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MediaImageRepository mediaImageRepository;

    @Autowired
    private CMSNavigationRepository cmsNavigationRepository;

    @Autowired
    private CMSNavigationLinkRepository cmsNavigationLinkRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private ProductOptionValueRepository productOptionValueRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private ModelService modelService;


    // <!--- services --->
    @Bean
    public DefaultMerchantCategoryService merchantCategoryService() {
        DefaultMerchantCategoryService merchantCategoryService = new DefaultMerchantCategoryService();
        merchantCategoryService.setCategoryRepository(categoryRepository);
        return merchantCategoryService;
    }

    @Bean
    public DefaultMerchantDeliveryModeService merchantDeliveryModeService()
    {
        DefaultMerchantDeliveryModeService merchantDeliveryModeService = new DefaultMerchantDeliveryModeService();
        merchantDeliveryModeService.setDeliveryModeRepository(deliveryModeRepository);
        return merchantDeliveryModeService;
    }

    @Bean
    public DefaultMerchantMediaService merchantMediaService() {
        DefaultMerchantMediaService merchantMediaService = new DefaultMerchantMediaService();
        merchantMediaService.setMediaRepository(mediaRepository);
        merchantMediaService.setMediaImageRepository(mediaImageRepository);
        return merchantMediaService;
    }

    @Bean
    public DefaultStorageService storageService() {
        DefaultStorageService storageService = new DefaultStorageService();
        storageService.setBucketName(BucketName);
        storageService.setStorage(storage);
        return storageService;
    }

    @Bean
    public DefaultMerchantNavigationService merchantNavigationService() {
        DefaultMerchantNavigationService merchantNavigationService = new DefaultMerchantNavigationService();
        merchantNavigationService.setCmsNavigationRepository(cmsNavigationRepository);
        merchantNavigationService.setCmsNavigationLinkRepository(cmsNavigationLinkRepository);
        return merchantNavigationService;
    }

    @Bean
    public DefaultMerchantProductImageService merchantProductImageService() {
        DefaultMerchantProductImageService merchantProductImageService = new DefaultMerchantProductImageService();
        merchantProductImageService.setProductImageRepository(productImageRepository);
        merchantProductImageService.setStorageService(storageService());
        return merchantProductImageService;
    }

    @Bean
    public DefaultMerchantProductService merchantProductService() {
        DefaultMerchantProductService merchantProductService = new DefaultMerchantProductService();
        merchantProductService.setProductRepository(productRepository);
        merchantProductService.setProductOptionRepository(productOptionRepository);
        merchantProductService.setProductOptionValueRepository(productOptionValueRepository);
        merchantProductService.setProductVariantRepository(productVariantRepository);
        return merchantProductService;
    }

    // <!--- facades --->
    @Bean
    public DefaultMerchantCategoryFacade merchantCategoryFacade() {
        DefaultMerchantCategoryFacade merchantCategoryFacade = new DefaultMerchantCategoryFacade();
        merchantCategoryFacade.setCategoryConverter(categoryConverter());
        merchantCategoryFacade.setMerchantMediaService(merchantMediaService());
        merchantCategoryFacade.setModelService(modelService);
        merchantCategoryFacade.setCategoryReversePopulator(categoryReversePopulator());
        merchantCategoryFacade.setMerchantCategoryService(merchantCategoryService());
        return merchantCategoryFacade;
    }

    @Bean
    public DefaultMerchantDeliveryModeFacade merchantDeliveryModeFacade()
    {
        DefaultMerchantDeliveryModeFacade merchantDeliveryModeFacade = new DefaultMerchantDeliveryModeFacade();
        merchantDeliveryModeFacade.setMerchantDeliveryModeService(merchantDeliveryModeService());
        merchantDeliveryModeFacade.setMerchantDeliveryModeConverter(merchantDeliveryModeConverter());
        return merchantDeliveryModeFacade;
    }

    @Bean
    public DefaultCMSNavigationFacade cmsNavigationFacade() {
        DefaultCMSNavigationFacade cmsNavigationFacade = new DefaultCMSNavigationFacade();
        cmsNavigationFacade.setNavigationConverter(navigationConverter());
        cmsNavigationFacade.setNavigationLinkConverter(navigationLinkConverter());
        cmsNavigationFacade.setNavigationLinkReversePopulator(navigationLinkReversePopulator());
        cmsNavigationFacade.setMerchantNavigationService(merchantNavigationService());
        return cmsNavigationFacade;
    }

    @Bean
    public DefaultMerchantProductFacade merchantProductFacade() {
        DefaultMerchantProductFacade merchantProductFacade = new DefaultMerchantProductFacade();
        merchantProductFacade.setMerchantProductService(merchantProductService());
        merchantProductFacade.setMerchantProductImageService(merchantProductImageService());
        merchantProductFacade.setMerchantCategoryService(merchantCategoryService());
        merchantProductFacade.setProductConverter(productConverter());
        merchantProductFacade.setProductInnerImageConverter(productInnerImageConverter());
        merchantProductFacade.setProductReversePopulator(productReversePopulator());
        return merchantProductFacade;
    }

    // <!--- converters and populators --->
    @Bean
    public MediaImagePopulator mediaImagePopulator()
    {
        return new MediaImagePopulator();
    }

    @Bean
    public Converter<MediaImageModel, MediaImageData> mediaImageConverter()
    {
        PopulatingConverter<MediaImageModel, MediaImageData> converter = new PopulatingConverter<>();
        converter.setTargetClass(MediaImageData.class);
        converter.setPopulators(Arrays.asList(mediaImagePopulator()));
        return converter;
    }

    @Bean
    public CategoryReversePopulator categoryReversePopulator() {
        return new CategoryReversePopulator();
    }

    @Bean
    public CategoryPopulator categoryPopulator()
    {
        CategoryPopulator populator = new CategoryPopulator();
        populator.setMediaImageConverter(mediaImageConverter());
        return populator;
    }

    @Bean
    public Converter<CategoryModel, CategoryData> categoryConverter()
    {
        PopulatingConverter<CategoryModel, CategoryData> converter = new PopulatingConverter<>();
        converter.setTargetClass(CategoryData.class);
        converter.setPopulators(Arrays.asList(categoryPopulator()));
        return converter;
    }



    @Bean
    public NavigationPopulator navigationPopulator()
    {
        return new NavigationPopulator();
    }

    @Bean
    public Converter<CMSNavigationModel, NavigationData> navigationConverter()
    {
        PopulatingConverter<CMSNavigationModel, NavigationData> converter = new PopulatingConverter<>();
        converter.setTargetClass(NavigationData.class);
        converter.setPopulators(Arrays.asList(navigationPopulator()));
        return converter;
    }

    @Bean
    public NavigationLinkReversePopulator navigationLinkReversePopulator() { return new NavigationLinkReversePopulator(); }

    @Bean
    public NavigationLinkPopulator navigationLinkPopulator()
    {
        return new NavigationLinkPopulator();
    }

    @Bean
    public Converter<CMSNavigationLinkModel, NavigationLinkData> navigationLinkConverter()
    {
        PopulatingConverter<CMSNavigationLinkModel, NavigationLinkData> converter = new PopulatingConverter<>();
        converter.setTargetClass(NavigationLinkData.class);
        converter.setPopulators(Arrays.asList(navigationLinkPopulator()));
        return converter;
    }


    // product
    @Bean
    public ProductReversePopulator productReversePopulator() {
        return new ProductReversePopulator();
    }

    @Bean
    public ProductBasicPopulator productBasicPopulator()
    {
        return new ProductBasicPopulator();
    }

    @Bean
    public ProductDetailPopulator productDetailPopulator()
    {
        return new ProductDetailPopulator();
    }

    @Bean
    public ProductCategoryPopulator productCategoryPopulator()
    {
        ProductCategoryPopulator populator = new ProductCategoryPopulator();
        populator.setCategoryConverter(categoryConverter());
        return populator;
    }

    @Bean
    public ProductSoldPopulator productSoldPopulator()
    {
        return new ProductSoldPopulator();
    }


    @Bean
    public ProductOptionPopulator productOptionPopulator()
    {
        ProductOptionPopulator populator = new ProductOptionPopulator();
        populator.setProductInnerOptionConverter(productInnerOptionConverter());
        return populator;
    }

    @Bean
    public ProductInnerOptionPopulator productInnerOptionPopulator()
    {
        ProductInnerOptionPopulator populator = new ProductInnerOptionPopulator();
        populator.setProductInnerOptionValueConverter(productInnerOptionValueConverter());
        return populator;
    }

    @Bean
    public Converter<ProductOptionModel, ProductOptionData> productInnerOptionConverter()
    {
        PopulatingConverter<ProductOptionModel, ProductOptionData> converter = new PopulatingConverter<>();
        converter.setTargetClass(ProductOptionData.class);
        converter.setPopulators(Arrays.asList((productInnerOptionPopulator())));
        return converter;
    }



    @Bean
    public ProductInnerOptionValuePopulator productInnerOptionValuePopulator()
    {
        ProductInnerOptionValuePopulator populator = new ProductInnerOptionValuePopulator();
        populator.setProductInnerImageConverter(productInnerImageConverter());
        return populator;
    }

    @Bean
    public Converter<ProductOptionValueModel, ProductOptionValueData> productInnerOptionValueConverter()
    {
        PopulatingConverter<ProductOptionValueModel, ProductOptionValueData> converter = new PopulatingConverter<>();
        converter.setTargetClass(ProductOptionValueData.class);
        converter.setPopulators(Arrays.asList(productInnerOptionValuePopulator()));
        return converter;
    }

    @Bean
    public ProductInnerImagePopulator productInnerImagePopulator()
    {
        return new ProductInnerImagePopulator();
    }

    @Bean
    public Converter<ProductImageModel, ProductImageData> productInnerImageConverter()
    {
        PopulatingConverter<ProductImageModel, ProductImageData> converter = new PopulatingConverter<>();
        converter.setTargetClass(ProductImageData.class);
        converter.setPopulators(Arrays.asList(productInnerImagePopulator()));
        return converter;
    }


    @Bean
    public ProductImagePopulator productImagePopulator()
    {
        ProductImagePopulator populator = new ProductImagePopulator();
        populator.setProductInnerImageConverter(productInnerImageConverter());
        return populator;
    }

    @Bean
    public ProductVariantPopulator productVariantPopulator()
    {
        ProductVariantPopulator populator = new ProductVariantPopulator();
        populator.setProductInnerOptionValueConverter(productInnerOptionValueConverter());
        return populator;
    }

    @Bean
    public Populator<ProductModel, ProductData> productBasicPopulators()
    {
        DefaultPopulatorList<ProductModel, ProductData> populatorList = new DefaultPopulatorList<>();
        populatorList.setPopulators(Arrays.asList(productBasicPopulator()));
        return populatorList;
    }

    @Bean
    public Populator<ProductModel, ProductData> productFullPopulators()
    {
        DefaultPopulatorList<ProductModel, ProductData> populatorList = new DefaultPopulatorList<>();
        populatorList.setPopulators(Arrays.asList(
                productBasicPopulator(),
                productDetailPopulator(),
                productCategoryPopulator(),
                productSoldPopulator(),
                productOptionPopulator(),
                productVariantPopulator(),
                productImagePopulator()
        ));
        return populatorList;
    }

    @Bean
    public ConfigurableConverter<ProductModel, ProductData, ProductOption> productConverter()
    {
        DefaultConfigurableConverter<ProductModel, ProductData, ProductOption> cxConverter = new DefaultConfigurableConverter<>();
        cxConverter.setTargetClass(ProductData.class);
        cxConverter.setDefaultOptions(Collections.singletonList(ProductOption.BASIC));

        Map<ProductOption, Populator<ProductModel, ProductData>> map = new ConcurrentHashMap<>();
        map.put(ProductOption.BASIC, productBasicPopulators());
        map.put(ProductOption.FULL, productFullPopulators());
        cxConverter.setPopulators(map);

        return cxConverter;
    }




    @Bean
    public MerchantDeliveryModePopulator merchantDeliveryModePopulator()
    {
        MerchantDeliveryModePopulator populator = new MerchantDeliveryModePopulator();
        populator.setMerchantDeliveryModeValueConverter(merchantDeliveryModeValueConverter());
        return populator;
    }

    @Bean
    public Converter<DeliveryModeModel, DeliveryModeData> merchantDeliveryModeConverter()
    {
        PopulatingConverter<DeliveryModeModel, DeliveryModeData> converter = new PopulatingConverter<>();
        converter.setTargetClass(DeliveryModeData.class);
        converter.setPopulators(Arrays.asList(merchantDeliveryModePopulator()));
        return converter;
    }

    @Bean
    public MerchantDeliveryModeValuePopulator merchantDeliveryModeValuePopulator()
    {
        return new MerchantDeliveryModeValuePopulator();
    }

    @Bean
    public Converter<DeliveryModeValueModel, DeliveryModeValueData> merchantDeliveryModeValueConverter()
    {
        PopulatingConverter<DeliveryModeValueModel, DeliveryModeValueData> converter = new PopulatingConverter<>();
        converter.setTargetClass(DeliveryModeValueData.class);
        converter.setPopulators(Arrays.asList(merchantDeliveryModeValuePopulator()));
        return converter;
    }

}
