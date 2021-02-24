package com.shopflix.storefront.config;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.converters.impl.PopulatingConverter;
import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.core.repository.delivery.DeliveryModeRepository;
import com.shopflix.core.repository.order.CartRepository;
import com.shopflix.core.repository.product.ProductRepository;
import com.shopflix.core.repository.user.CustomerRepository;
import com.shopflix.core.service.ModelService;
import com.shopflix.core.service.SessionService;
import com.shopflix.storefront.data.order.*;
import com.shopflix.storefront.facades.order.converters.populator.*;
import com.shopflix.storefront.facades.order.impl.DefaultCartFacade;
import com.shopflix.storefront.facades.order.impl.DefaultCheckoutFacade;
import com.shopflix.storefront.services.customer.CustomerService;
import com.shopflix.storefront.services.customer.impl.DefaultCustomerService;
import com.shopflix.storefront.services.delivery.impl.DefaultDeliveryService;
import com.shopflix.storefront.services.delivery.strategies.impl.DefaultDeliveryModeLookupStrategy;
import com.shopflix.storefront.services.order.CalculationService;
import com.shopflix.storefront.services.order.CartFactory;
import com.shopflix.storefront.services.order.CartService;
import com.shopflix.storefront.services.order.CommerceCartService;
import com.shopflix.storefront.services.order.impl.*;
import com.shopflix.storefront.services.order.strategies.CommerceAddToCartStrategy;
import com.shopflix.storefront.services.order.strategies.CommerceCartCalculationStrategy;
import com.shopflix.storefront.services.order.strategies.CommerceRemoveLineItemsStrategy;
import com.shopflix.storefront.services.order.strategies.CommerceUpdateCartLineItemStrategy;
import com.shopflix.storefront.services.order.strategies.calculation.impl.DefaultFindDeliveryCostStrategy;
import com.shopflix.storefront.services.order.strategies.impl.*;
import com.shopflix.storefront.services.product.ProductService;
import com.shopflix.storefront.services.product.SKUProductFactory;
import com.shopflix.storefront.services.product.impl.DefaultProductService;
import com.shopflix.storefront.services.product.impl.DefaultSKUProductFactory;
import com.shopflix.storefront.services.strategies.impl.OrderLineItemModifiableChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class StorefrontAppConfig
{
    @Autowired
    @Qualifier("sessionService")
    private SessionService sessionService;

    @Autowired
    @Qualifier("modelService")
    private ModelService modelService;

    @Autowired
    @Qualifier("customerRepository")
    private CustomerRepository customerRepository;

    @Autowired
    @Qualifier("productRepository")
    private ProductRepository productRepository;

    @Autowired
    @Qualifier("deliveryModeRepository")
    private DeliveryModeRepository deliveryModeRepository;

    @Autowired
    @Qualifier("cartRepository")
    private CartRepository cartRepository;

    // <!---  service layer --->
    @Bean
    public DefaultCustomerService customerService() {
        DefaultCustomerService customerService = new DefaultCustomerService();
        customerService.setSessionService(sessionService);
        customerService.setCustomerRepository(customerRepository);
        return customerService;
    }

    @Bean
    public DefaultProductService productService() {
        DefaultProductService productService = new DefaultProductService();
        productService.setProductRepository(productRepository);
        return productService;
    }

    @Bean
    public DefaultCartService cartService() {
        DefaultCartService cartService = new DefaultCartService();
        cartService.setCustomerService(customerService());
        cartService.setCartRepository(cartRepository);
        cartService.setCartFactory(cartFactory());
        return cartService;
    }

    @Bean
    public DefaultCartFactory cartFactory() {
        DefaultCartFactory cartFactory = new DefaultCartFactory();
        cartFactory.setCustomerService(customerService());
        cartFactory.setModelService(modelService);
        return cartFactory;
    }

    @Bean
    public DefaultCommerceCartService commerceCartService() {
        DefaultCommerceCartService commerceCartService = new DefaultCommerceCartService();
        commerceCartService.setCommerceCartCalculationStrategy(commerceCartCalculationStrategy());
        commerceCartService.setCommerceAddToCartStrategy(commerceAddToCartStrategy());
        commerceCartService.setCommerceUpdateCartLineItemStrategy(commerceUpdateCartLineItemStrategy());
        commerceCartService.setCommerceRemoveLineItemsStrategy(commerceRemoveLineItemsStrategy());
        return commerceCartService;
    }

    @Bean
    public DefaultCalculationService calculationService() {
        DefaultCalculationService calculationService = new DefaultCalculationService();
        calculationService.setFindDeliveryCostStrategy(findDeliveryCostStrategy());
        return calculationService;
    }

    @Bean
    public DefaultFindDeliveryCostStrategy findDeliveryCostStrategy() {
        return new DefaultFindDeliveryCostStrategy();
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
    public DefaultCommerceRemoveLineItemsStrategy commerceRemoveLineItemsStrategy() {
        DefaultCommerceRemoveLineItemsStrategy strategy = new DefaultCommerceRemoveLineItemsStrategy();
        strategy.setModelService(modelService);
        return strategy;
    }

    @Bean
    public DefaultCommerceCartCalculationStrategy commerceCartCalculationStrategy() {
        DefaultCommerceCartCalculationStrategy strategy = new DefaultCommerceCartCalculationStrategy();
        strategy.setCalculationService(calculationService());
        return strategy;
    }

    @Bean
    public DefaultSKUProductFactory skuProductFactory() {
        return new DefaultSKUProductFactory();
    }

    @Bean
    public OrderLineItemModifiableChecker orderLineItemModifiableChecker() {
        return new OrderLineItemModifiableChecker();
    }

    @Bean
    public DefaultDeliveryService deliveryService() {
        DefaultDeliveryService deliveryService = new DefaultDeliveryService();
        deliveryService.setDeliveryModeLookupStrategy(deliveryModeLookupStrategy());
        deliveryService.setDeliveryModeRepository(deliveryModeRepository);
        deliveryService.setFindDeliveryCostStrategy(findDeliveryCostStrategy());
        return deliveryService;
    }

    @Bean
    public DefaultDeliveryModeLookupStrategy deliveryModeLookupStrategy() {
        DefaultDeliveryModeLookupStrategy strategy = new DefaultDeliveryModeLookupStrategy();
        strategy.setDeliveryModeRepository(deliveryModeRepository);
        return strategy;
    }

    @Bean
    public DefaultCommerceCheckoutService commerceCheckoutService() {
        DefaultCommerceCheckoutService commerceCheckoutService = new DefaultCommerceCheckoutService();
        commerceCheckoutService.setCommerceDeliveryModeStrategy(commerceDeliveryModeStrategy());
        return commerceCheckoutService;
    }

    @Bean
    public DefaultCommerceDeliveryModeStrategy commerceDeliveryModeStrategy() {
        DefaultCommerceDeliveryModeStrategy strategy = new DefaultCommerceDeliveryModeStrategy();
        strategy.setModelService(modelService);
        strategy.setCommerceCartCalculationStrategy(commerceCartCalculationStrategy());
        return strategy;
    }




    // <!--- facade layer --->
    @Bean
    public DefaultCartFacade cartFacade() {
        DefaultCartFacade cartFacade = new DefaultCartFacade();
        cartFacade.setCartService(cartService());
        cartFacade.setCartConverter(cartConverter());
        cartFacade.setCommerceCartService(commerceCartService());
        cartFacade.setCommerceCartParameterConverter(commerceCartParameterConverter());
        cartFacade.setCommerceUpdateCartParameterConverter(commerceUpdateCartParameterConverter());
        cartFacade.setCartModificationConverter(cartModificationConverter());
        return cartFacade;
    }

    @Bean
    public DefaultCheckoutFacade checkoutFacade() {
        DefaultCheckoutFacade checkoutFacade = new DefaultCheckoutFacade();
        checkoutFacade.setCartService(cartService());
        checkoutFacade.setCommerceCheckoutService(commerceCheckoutService());
        checkoutFacade.setDeliveryService(deliveryService());
        checkoutFacade.setDeliveryModeConverter(deliveryModeConverter());
        return checkoutFacade;
    }









    // <!--- converter and populator --->
    @Bean
    public OrderLineItemPopulator orderLineItemPopulator() {
        OrderLineItemPopulator populator = new OrderLineItemPopulator();
        populator.setOrderLineItemModelModifiableChecker(orderLineItemModifiableChecker());
        return populator;
    }

    @Bean
    public Converter<AbstractOrderLineItemModel, OrderLineItemData> orderLineItemConverter() {
        PopulatingConverter<AbstractOrderLineItemModel, OrderLineItemData> converter = new PopulatingConverter<>();
        converter.setTargetClass(OrderLineItemData.class);
        converter.setPopulators(Arrays.asList(orderLineItemPopulator()));
        return converter;
    }

    @Bean
    public CommerceCartParameterPopulator commerceCartParameterPopulator() {
        CommerceCartParameterPopulator populator = new CommerceCartParameterPopulator();
        populator.setCartService(cartService());
        populator.setProductService(productService());
        populator.setSkuProductFactory(skuProductFactory());
        return populator;
    }

    @Bean
    public Converter<AddToCartParams, CommerceCartParameter> commerceCartParameterConverter() {
        PopulatingConverter<AddToCartParams, CommerceCartParameter> converter = new PopulatingConverter<>();
        converter.setTargetClass(CommerceCartParameter.class);
        converter.setPopulators(Arrays.asList(commerceCartParameterPopulator()));
        return converter;
    }

    @Bean
    public CommerceUpdateCartParameterPopulator commerceUpdateCartParameterPopulator() {
        CommerceUpdateCartParameterPopulator populator = new CommerceUpdateCartParameterPopulator();
        populator.setCartService(cartService());
        populator.setSkuProductFactory(skuProductFactory());
        return populator;
    }

    @Bean
    public Converter<UpdateCartParams, CommerceCartParameter> commerceUpdateCartParameterConverter() {
        PopulatingConverter<UpdateCartParams, CommerceCartParameter> converter = new PopulatingConverter<>();
        converter.setTargetClass(CommerceCartParameter.class);
        converter.setPopulators(Arrays.asList(commerceUpdateCartParameterPopulator()));
        return converter;
    }


    @Bean
    public CartModificationPopulator cartModificationPopulator() {
        CartModificationPopulator populator = new CartModificationPopulator();
        populator.setOrderLineItemConverter(orderLineItemConverter());
        return populator;
    }

    @Bean
    public Converter<CommerceCartModification, CartModificationData> cartModificationConverter() {
        PopulatingConverter<CommerceCartModification, CartModificationData> converter = new PopulatingConverter<>();
        converter.setTargetClass(CartModificationData.class);
        converter.setPopulators(Arrays.asList(cartModificationPopulator()));
        return converter;
    }

    @Bean
    public CartPopulator cartPopulator() {
        CartPopulator populator = new CartPopulator();
        populator.setOrderLineItemConverter(orderLineItemConverter());
        return populator;
    }

    @Bean
    public Converter<CartModel, CartData> cartConverter() {
        PopulatingConverter<CartModel, CartData> converter = new PopulatingConverter<>();
        converter.setTargetClass(CartData.class);
        converter.setPopulators(Arrays.asList(cartPopulator()));
        return converter;
    }

    @Bean
    public DeliveryModePopulator deliveryModePopulator() {
        return new DeliveryModePopulator();
    }

    @Bean
    public Converter<DeliveryModeModel, DeliveryModeData> deliveryModeConverter() {
        PopulatingConverter<DeliveryModeModel, DeliveryModeData> converter = new PopulatingConverter<>();
        converter.setTargetClass(DeliveryModeData.class);
        converter.setPopulators(Arrays.asList(deliveryModePopulator()));
        return converter;
    }
}
