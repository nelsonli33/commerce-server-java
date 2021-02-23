package com.shopflix.storefront.config;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.converters.impl.PopulatingConverter;
import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.repository.order.CartRepository;
import com.shopflix.core.repository.product.ProductRepository;
import com.shopflix.core.repository.user.CustomerRepository;
import com.shopflix.core.service.ModelService;
import com.shopflix.core.service.SessionService;
import com.shopflix.storefront.data.order.*;
import com.shopflix.storefront.facades.order.converters.populator.*;
import com.shopflix.storefront.facades.order.impl.DefaultCartFacade;
import com.shopflix.storefront.services.customer.CustomerService;
import com.shopflix.storefront.services.customer.impl.DefaultCustomerService;
import com.shopflix.storefront.services.order.CalculationService;
import com.shopflix.storefront.services.order.CartFactory;
import com.shopflix.storefront.services.order.CartService;
import com.shopflix.storefront.services.order.CommerceCartService;
import com.shopflix.storefront.services.order.impl.DefaultCalculationService;
import com.shopflix.storefront.services.order.impl.DefaultCartFactory;
import com.shopflix.storefront.services.order.impl.DefaultCartService;
import com.shopflix.storefront.services.order.impl.DefaultCommerceCartService;
import com.shopflix.storefront.services.order.strategies.CommerceAddToCartStrategy;
import com.shopflix.storefront.services.order.strategies.CommerceCartCalculationStrategy;
import com.shopflix.storefront.services.order.strategies.CommerceRemoveLineItemsStrategy;
import com.shopflix.storefront.services.order.strategies.CommerceUpdateCartLineItemStrategy;
import com.shopflix.storefront.services.order.strategies.impl.DefaultCommerceAddToCartStrategy;
import com.shopflix.storefront.services.order.strategies.impl.DefaultCommerceCartCalculationStrategy;
import com.shopflix.storefront.services.order.strategies.impl.DefaultCommerceRemoveLineItemsStrategy;
import com.shopflix.storefront.services.order.strategies.impl.DefaultCommerceUpdateCartLineItemStrategy;
import com.shopflix.storefront.services.product.ProductService;
import com.shopflix.storefront.services.product.SKUProductFactory;
import com.shopflix.storefront.services.product.impl.DefaultProductService;
import com.shopflix.storefront.services.product.impl.DefaultSKUProductFactory;
import com.shopflix.storefront.services.strategies.impl.OrderLineItemModifiableChecker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class StorefrontAppConfig
{

    // <!---  service layer --->
    @Bean
    public DefaultCustomerService customerService(
            @Qualifier("sessionService") SessionService sessionService,
            @Qualifier("customerRepository") CustomerRepository customerRepository
    ) {
        DefaultCustomerService customerService = new DefaultCustomerService();
        customerService.setSessionService(sessionService);
        customerService.setCustomerRepository(customerRepository);
        return customerService;
    }

    @Bean
    public DefaultProductService productService(
            @Qualifier("productRepository") ProductRepository productRepository
    ) {
        DefaultProductService productService = new DefaultProductService();
        productService.setProductRepository(productRepository);
        return productService;
    }

    @Bean
    public DefaultCartService cartService(
            @Qualifier("customerService") CustomerService customerService,
            @Qualifier("cartRepository") CartRepository cartRepository,
            @Qualifier("cartFactory") CartFactory cartFactory
    ) {
        DefaultCartService cartService = new DefaultCartService();
        cartService.setCustomerService(customerService);
        cartService.setCartRepository(cartRepository);
        cartService.setCartFactory(cartFactory);
        return cartService;
    }

    @Bean
    public DefaultCartFactory cartFactory(
            @Qualifier("customerService") CustomerService customerService,
            @Qualifier("modelService") ModelService modelService
    ) {
        DefaultCartFactory cartFactory = new DefaultCartFactory();
        cartFactory.setCustomerService(customerService);
        cartFactory.setModelService(modelService);
        return cartFactory;
    }

    @Bean
    public DefaultCommerceCartService commerceCartService(
            @Qualifier("commerceCartCalculationStrategy")  CommerceCartCalculationStrategy commerceCartCalculationStrategy,
            @Qualifier("commerceAddToCartStrategy") CommerceAddToCartStrategy commerceAddToCartStrategy,
            @Qualifier("commerceUpdateCartLineItemStrategy") CommerceUpdateCartLineItemStrategy commerceUpdateCartLineItemStrategy,
            @Qualifier("commerceRemoveLineItemsStrategy") CommerceRemoveLineItemsStrategy commerceRemoveLineItemsStrategy
    ) {
        DefaultCommerceCartService commerceCartService = new DefaultCommerceCartService();
        commerceCartService.setCommerceCartCalculationStrategy(commerceCartCalculationStrategy);
        commerceCartService.setCommerceAddToCartStrategy(commerceAddToCartStrategy);
        commerceCartService.setCommerceUpdateCartLineItemStrategy(commerceUpdateCartLineItemStrategy);
        commerceCartService.setCommerceRemoveLineItemsStrategy(commerceRemoveLineItemsStrategy);
        return commerceCartService;
    }

    @Bean
    public DefaultCalculationService calculationService() {
        return new DefaultCalculationService();
    }

    @Bean
    public DefaultCommerceAddToCartStrategy commerceAddToCartStrategy() {
        return new DefaultCommerceAddToCartStrategy();
    }

    @Bean
    public DefaultCommerceUpdateCartLineItemStrategy commerceUpdateCartLineItemStrategy() {
        return new DefaultCommerceUpdateCartLineItemStrategy();
    }

    @Bean
    public DefaultCommerceRemoveLineItemsStrategy commerceRemoveLineItemsStrategy(
            @Qualifier("modelService") ModelService modelService
    ) {
        DefaultCommerceRemoveLineItemsStrategy strategy = new DefaultCommerceRemoveLineItemsStrategy();
        strategy.setModelService(modelService);
        return strategy;
    }

    @Bean
    public DefaultCommerceCartCalculationStrategy commerceCartCalculationStrategy(
            @Qualifier("calculationService") CalculationService calculationService
    ) {
        DefaultCommerceCartCalculationStrategy commerceCartCalculationStrategy = new DefaultCommerceCartCalculationStrategy();
        commerceCartCalculationStrategy.setCalculationService(calculationService);
        return commerceCartCalculationStrategy;
    }

    @Bean
    public DefaultSKUProductFactory skuProductFactory() {
        return new DefaultSKUProductFactory();
    }

    @Bean
    public OrderLineItemModifiableChecker orderLineItemModifiableChecker() {
        return new OrderLineItemModifiableChecker();
    }

    // <!--- facade layer --->
    @Bean
    public DefaultCartFacade cartFacade(
            @Qualifier("cartService") CartService cartService,
            @Qualifier("commerceCartService") CommerceCartService commerceCartService,
            @Qualifier("cartConverter") Converter<CartModel, CartData> cartConverter,
            @Qualifier("commerceCartParameterConverter") Converter<AddToCartParams, CommerceCartParameter> commerceCartParameterConverter,
            @Qualifier("commerceUpdateCartParameterConverter") Converter<UpdateCartParams, CommerceCartParameter> commerceUpdateCartParameterConverter,
            @Qualifier("cartModificationConverter") Converter<CommerceCartModification, CartModificationData> cartModificationConverter
    ) {
        DefaultCartFacade cartFacade = new DefaultCartFacade();
        cartFacade.setCartService(cartService);
        cartFacade.setCartConverter(cartConverter);
        cartFacade.setCommerceCartService(commerceCartService);
        cartFacade.setCommerceCartParameterConverter(commerceCartParameterConverter);
        cartFacade.setCommerceUpdateCartParameterConverter(commerceUpdateCartParameterConverter);
        cartFacade.setCartModificationConverter(cartModificationConverter);
        return cartFacade;
    }

    // <!--- converter and populator --->
    @Bean
    public OrderLineItemPopulator orderLineItemPopulator(
            @Qualifier("orderLineItemModifiableChecker") OrderLineItemModifiableChecker orderLineItemModifiableChecker
    ) {
        OrderLineItemPopulator populator = new OrderLineItemPopulator();
        populator.setOrderLineItemModelModifiableChecker(orderLineItemModifiableChecker);
        return populator;
    }

    @Bean
    public Converter<AbstractOrderLineItemModel, OrderLineItemData> orderLineItemConverter(
            @Qualifier("orderLineItemPopulator") Populator<AbstractOrderLineItemModel, OrderLineItemData> orderLineItemPopulator
    ) {
        PopulatingConverter<AbstractOrderLineItemModel, OrderLineItemData> converter = new PopulatingConverter<>();
        converter.setTargetClass(OrderLineItemData.class);
        converter.setPopulators(Arrays.asList(orderLineItemPopulator));
        return converter;
    }

    @Bean
    public CommerceCartParameterPopulator commerceCartParameterPopulator(
            @Qualifier("cartService") CartService cartService,
            @Qualifier("productService") ProductService productService,
            @Qualifier("skuProductFactory") SKUProductFactory skuProductFactory
    ) {
        CommerceCartParameterPopulator populator = new CommerceCartParameterPopulator();
        populator.setCartService(cartService);
        populator.setProductService(productService);
        populator.setSkuProductFactory(skuProductFactory);
        return populator;
    }

    @Bean
    public Converter<AddToCartParams, CommerceCartParameter> commerceCartParameterConverter(
            @Qualifier("commerceCartParameterPopulator") Populator<AddToCartParams, CommerceCartParameter> commerceCartParameterPopulator
    ) {
        PopulatingConverter<AddToCartParams, CommerceCartParameter> converter = new PopulatingConverter<>();
        converter.setTargetClass(CommerceCartParameter.class);
        converter.setPopulators(Arrays.asList(commerceCartParameterPopulator));
        return converter;
    }

    @Bean
    public CommerceUpdateCartParameterPopulator commerceUpdateCartParameterPopulator(
            @Qualifier("cartService") CartService cartService,
            @Qualifier("skuProductFactory") SKUProductFactory skuProductFactory
    ) {
        CommerceUpdateCartParameterPopulator populator = new CommerceUpdateCartParameterPopulator();
        populator.setCartService(cartService);
        populator.setSkuProductFactory(skuProductFactory);
        return populator;
    }

    @Bean
    public Converter<UpdateCartParams, CommerceCartParameter> commerceUpdateCartParameterConverter(
            @Qualifier("commerceUpdateCartParameterPopulator") Populator<UpdateCartParams, CommerceCartParameter> commerceUpdateCartParameterPopulator
    ) {
        PopulatingConverter<UpdateCartParams, CommerceCartParameter> converter = new PopulatingConverter<>();
        converter.setTargetClass(CommerceCartParameter.class);
        converter.setPopulators(Arrays.asList(commerceUpdateCartParameterPopulator));
        return converter;
    }


    @Bean
    public CartModificationPopulator cartModificationPopulator(
            @Qualifier("orderLineItemConverter") Converter<AbstractOrderLineItemModel, OrderLineItemData> orderLineItemConverter
    ) {
        CartModificationPopulator populator = new CartModificationPopulator();
        populator.setOrderLineItemConverter(orderLineItemConverter);
        return populator;
    }

    @Bean
    public Converter<CommerceCartModification, CartModificationData> cartModificationConverter(
            @Qualifier("cartModificationPopulator") Populator<CommerceCartModification, CartModificationData> cartModificationPopulator
    ) {
        PopulatingConverter<CommerceCartModification, CartModificationData> converter = new PopulatingConverter<>();
        converter.setTargetClass(CartModificationData.class);
        converter.setPopulators(Arrays.asList(cartModificationPopulator));
        return converter;
    }

    @Bean
    public CartPopulator cartPopulator(
            @Qualifier("orderLineItemConverter") Converter<AbstractOrderLineItemModel, OrderLineItemData> orderLineItemConverter
    ) {
        CartPopulator populator = new CartPopulator();
        populator.setOrderLineItemConverter(orderLineItemConverter);
        return populator;
    }

    @Bean
    public Converter<CartModel, CartData> cartConverter(
            @Qualifier("cartPopulator") Populator<CartModel, CartData> cartPopulator
    ) {
        PopulatingConverter<CartModel, CartData> converter = new PopulatingConverter<>();
        converter.setTargetClass(CartData.class);
        converter.setPopulators(Arrays.asList(cartPopulator));
        return converter;
    }


}
