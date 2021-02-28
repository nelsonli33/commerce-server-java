package com.shopflix.storefront.config;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.impl.PopulatingConverter;
import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.model.order.delivery.DeliveryAddressModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.core.model.user.CustomerAddressModel;
import com.shopflix.core.repository.delivery.DeliveryModeRepository;
import com.shopflix.core.repository.order.CartRepository;
import com.shopflix.core.repository.product.ProductRepository;
import com.shopflix.core.repository.user.CustomerRepository;
import com.shopflix.core.service.I18NService;
import com.shopflix.core.service.ModelService;
import com.shopflix.core.service.SessionService;
import com.shopflix.storefront.facades.order.converters.populator.*;
import com.shopflix.storefront.facades.order.data.*;
import com.shopflix.storefront.facades.order.impl.DefaultCartFacade;
import com.shopflix.storefront.facades.order.impl.DefaultCheckoutFacade;
import com.shopflix.storefront.facades.order.impl.DefaultPaymentFacade;
import com.shopflix.storefront.facades.user.converters.populator.AbstractAddressReversePopulator;
import com.shopflix.storefront.facades.user.converters.populator.CustomerAddressPopulator;
import com.shopflix.storefront.facades.user.converters.populator.CustomerAddressReversePopulator;
import com.shopflix.storefront.facades.user.data.CustomerAddressData;
import com.shopflix.storefront.facades.user.impl.DefaultCustomerFacade;
import com.shopflix.storefront.services.customer.impl.DefaultCustomerAccountService;
import com.shopflix.storefront.services.customer.impl.DefaultCustomerService;
import com.shopflix.storefront.services.delivery.impl.DefaultDeliveryService;
import com.shopflix.storefront.services.delivery.strategies.impl.DefaultDeliveryModeLookupStrategy;
import com.shopflix.storefront.services.order.impl.*;
import com.shopflix.storefront.services.order.strategies.calculation.impl.DefaultFindDeliveryCostStrategy;
import com.shopflix.storefront.services.order.strategies.impl.*;
import com.shopflix.storefront.services.product.impl.DefaultProductService;
import com.shopflix.storefront.services.product.impl.DefaultSKUProductFactory;
import com.shopflix.storefront.services.strategies.impl.OrderLineItemModifiableChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
@ComponentScan(value = {"com.shopflix.storefront", "com.shopflix.core"})
public class StorefrontAppConfig
{
    private PasswordEncoder passwordEncoder;
    private SessionService sessionService;
    private ModelService modelService;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private DeliveryModeRepository deliveryModeRepository;
    private CartRepository cartRepository;
    private I18NService i18NService;

    public PasswordEncoder getPasswordEncoder()
    {
        return passwordEncoder;
    }

    @Autowired
    @Qualifier("passwordEncoder")
    public void setPasswordEncoder(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    public SessionService getSessionService()
    {
        return sessionService;
    }

    @Autowired
    @Qualifier("sessionService")
    public void setSessionService(SessionService sessionService)
    {
        this.sessionService = sessionService;
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    @Autowired
    @Qualifier("modelService")
    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    public CustomerRepository getCustomerRepository()
    {
        return customerRepository;
    }

    @Autowired
    @Qualifier("customerRepository")
    public void setCustomerRepository(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    public ProductRepository getProductRepository()
    {
        return productRepository;
    }

    @Autowired
    @Qualifier("productRepository")
    public void setProductRepository(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public DeliveryModeRepository getDeliveryModeRepository()
    {
        return deliveryModeRepository;
    }

    @Autowired
    @Qualifier("deliveryModeRepository")
    public void setDeliveryModeRepository(DeliveryModeRepository deliveryModeRepository)
    {
        this.deliveryModeRepository = deliveryModeRepository;
    }

    public CartRepository getCartRepository()
    {
        return cartRepository;
    }

    @Autowired
    @Qualifier("cartRepository")
    public void setCartRepository(CartRepository cartRepository)
    {
        this.cartRepository = cartRepository;
    }

    public I18NService getI18NService()
    {
        return i18NService;
    }

    @Autowired
    @Qualifier("i18NService")
    public void setI18NService(I18NService i18NService)
    {
        this.i18NService = i18NService;
    }

    // <!---  service layer --->
    @Bean
    public DefaultCustomerService customerService()
    {
        DefaultCustomerService customerService = new DefaultCustomerService();
        customerService.setSessionService(getSessionService());
        customerService.setCustomerRepository(getCustomerRepository());
        return customerService;
    }

    @Bean
    public DefaultCustomerAccountService customerAccountService()
    {
        DefaultCustomerAccountService customerAccountService = new DefaultCustomerAccountService();
        customerAccountService.setModelService(getModelService());
        customerAccountService.setCustomerRepository(getCustomerRepository());
        customerAccountService.setPasswordEncoder(getPasswordEncoder());
        return customerAccountService;
    }

    @Bean
    public DefaultProductService productService()
    {
        DefaultProductService productService = new DefaultProductService();
        productService.setProductRepository(getProductRepository());
        return productService;
    }

    @Bean
    public DefaultCartService cartService()
    {
        DefaultCartService cartService = new DefaultCartService();
        cartService.setCustomerService(customerService());
        cartService.setCartRepository(getCartRepository());
        cartService.setCartFactory(cartFactory());
        return cartService;
    }

    @Bean
    public DefaultCartFactory cartFactory()
    {
        DefaultCartFactory cartFactory = new DefaultCartFactory();
        cartFactory.setCustomerService(customerService());
        cartFactory.setModelService(getModelService());
        return cartFactory;
    }

    @Bean
    public DefaultCommerceCartService commerceCartService()
    {
        DefaultCommerceCartService commerceCartService = new DefaultCommerceCartService();
        commerceCartService.setCommerceCartCalculationStrategy(commerceCartCalculationStrategy());
        commerceCartService.setCommerceAddToCartStrategy(commerceAddToCartStrategy());
        commerceCartService.setCommerceUpdateCartLineItemStrategy(commerceUpdateCartLineItemStrategy());
        commerceCartService.setCommerceRemoveLineItemsStrategy(commerceRemoveLineItemsStrategy());
        return commerceCartService;
    }

    @Bean
    public DefaultCalculationService calculationService()
    {
        DefaultCalculationService calculationService = new DefaultCalculationService();
        calculationService.setFindDeliveryCostStrategy(findDeliveryCostStrategy());
        return calculationService;
    }

    @Bean
    public DefaultFindDeliveryCostStrategy findDeliveryCostStrategy()
    {
        return new DefaultFindDeliveryCostStrategy();
    }

    @Bean
    public DefaultCommerceAddToCartStrategy commerceAddToCartStrategy()
    {
        return new DefaultCommerceAddToCartStrategy();
    }

    @Bean
    public DefaultCommerceUpdateCartLineItemStrategy commerceUpdateCartLineItemStrategy()
    {
        return new DefaultCommerceUpdateCartLineItemStrategy();
    }

    @Bean
    public DefaultCommerceRemoveLineItemsStrategy commerceRemoveLineItemsStrategy()
    {
        DefaultCommerceRemoveLineItemsStrategy strategy = new DefaultCommerceRemoveLineItemsStrategy();
        strategy.setModelService(getModelService());
        return strategy;
    }

    @Bean
    public DefaultCommerceCartCalculationStrategy commerceCartCalculationStrategy()
    {
        DefaultCommerceCartCalculationStrategy strategy = new DefaultCommerceCartCalculationStrategy();
        strategy.setCalculationService(calculationService());
        return strategy;
    }

    @Bean
    public DefaultSKUProductFactory skuProductFactory()
    {
        return new DefaultSKUProductFactory();
    }

    @Bean
    public OrderLineItemModifiableChecker orderLineItemModifiableChecker()
    {
        return new OrderLineItemModifiableChecker();
    }

    @Bean
    public DefaultDeliveryService deliveryService()
    {
        DefaultDeliveryService deliveryService = new DefaultDeliveryService();
        deliveryService.setDeliveryModeLookupStrategy(deliveryModeLookupStrategy());
        deliveryService.setDeliveryModeRepository(getDeliveryModeRepository());
        deliveryService.setFindDeliveryCostStrategy(findDeliveryCostStrategy());
        return deliveryService;
    }

    @Bean
    public DefaultDeliveryModeLookupStrategy deliveryModeLookupStrategy()
    {
        DefaultDeliveryModeLookupStrategy strategy = new DefaultDeliveryModeLookupStrategy();
        strategy.setDeliveryModeRepository(getDeliveryModeRepository());
        return strategy;
    }

    @Bean
    public DefaultCommerceCheckoutService commerceCheckoutService()
    {
        DefaultCommerceCheckoutService commerceCheckoutService = new DefaultCommerceCheckoutService();
        commerceCheckoutService.setCommerceDeliveryModeStrategy(commerceDeliveryModeStrategy());
        commerceCheckoutService.setCommerceDeliveryAddressStrategy(commerceDeliveryAddressStrategy());
        return commerceCheckoutService;
    }

    @Bean
    public DefaultCommerceDeliveryModeStrategy commerceDeliveryModeStrategy()
    {
        DefaultCommerceDeliveryModeStrategy strategy = new DefaultCommerceDeliveryModeStrategy();
        strategy.setModelService(getModelService());
        strategy.setCommerceCartCalculationStrategy(commerceCartCalculationStrategy());
        return strategy;
    }

    @Bean
    public DefaultCommerceDeliveryAddressStrategy commerceDeliveryAddressStrategy()
    {
        DefaultCommerceDeliveryAddressStrategy strategy = new DefaultCommerceDeliveryAddressStrategy();
        strategy.setModelService(getModelService());
        return strategy;
    }


    // <!--- facade layer --->
    @Bean
    public DefaultCustomerFacade customerFacade()
    {
        DefaultCustomerFacade customerFacade = new DefaultCustomerFacade();
        customerFacade.setCustomerService(customerService());
        customerFacade.setCustomerAccountService(customerAccountService());
        customerFacade.setCustomerAddressReversePopulator(customerAddressReversePopulator());
        customerFacade.setCustomerAddressConverter(customerAddressConverter());
        return customerFacade;
    }

    @Bean
    public DefaultCartFacade cartFacade()
    {
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
    public DefaultCheckoutFacade checkoutFacade()
    {
        DefaultCheckoutFacade checkoutFacade = new DefaultCheckoutFacade();
        checkoutFacade.setCartFacade(cartFacade());
        checkoutFacade.setCartService(cartService());
        checkoutFacade.setCommerceCheckoutService(commerceCheckoutService());
        checkoutFacade.setDeliveryService(deliveryService());
        checkoutFacade.setCustomerAccountService(customerAccountService());
        checkoutFacade.setModelService(getModelService());
        checkoutFacade.setDeliveryAddressReversePopulator(deliveryAddressReversePopulator());
        checkoutFacade.setDeliveryModeConverter(deliveryModeConverter());
        checkoutFacade.setDeliveryAddressConverter(deliveryAddressConverter());
        return checkoutFacade;
    }

    @Bean
    public DefaultPaymentFacade paymentFacade() {
        DefaultPaymentFacade paymentFacade = new DefaultPaymentFacade();
        paymentFacade.setI18nService(i18NService);
        paymentFacade.setCartService(cartService());
        return paymentFacade;
    }

    // <!--- converter and populator --->
    @Bean
    public OrderLineItemPopulator orderLineItemPopulator()
    {
        OrderLineItemPopulator populator = new OrderLineItemPopulator();
        populator.setOrderLineItemModelModifiableChecker(orderLineItemModifiableChecker());
        return populator;
    }

    @Bean
    public Converter<AbstractOrderLineItemModel, OrderLineItemData> orderLineItemConverter()
    {
        PopulatingConverter<AbstractOrderLineItemModel, OrderLineItemData> converter = new PopulatingConverter<>();
        converter.setTargetClass(OrderLineItemData.class);
        converter.setPopulators(Arrays.asList(orderLineItemPopulator()));
        return converter;
    }

    @Bean
    public CommerceCartParameterPopulator commerceCartParameterPopulator()
    {
        CommerceCartParameterPopulator populator = new CommerceCartParameterPopulator();
        populator.setCartService(cartService());
        populator.setProductService(productService());
        populator.setSkuProductFactory(skuProductFactory());
        return populator;
    }

    @Bean
    public Converter<AddToCartParams, CommerceCartParameter> commerceCartParameterConverter()
    {
        PopulatingConverter<AddToCartParams, CommerceCartParameter> converter = new PopulatingConverter<>();
        converter.setTargetClass(CommerceCartParameter.class);
        converter.setPopulators(Arrays.asList(commerceCartParameterPopulator()));
        return converter;
    }

    @Bean
    public CommerceUpdateCartParameterPopulator commerceUpdateCartParameterPopulator()
    {
        CommerceUpdateCartParameterPopulator populator = new CommerceUpdateCartParameterPopulator();
        populator.setCartService(cartService());
        populator.setSkuProductFactory(skuProductFactory());
        return populator;
    }

    @Bean
    public Converter<UpdateCartParams, CommerceCartParameter> commerceUpdateCartParameterConverter()
    {
        PopulatingConverter<UpdateCartParams, CommerceCartParameter> converter = new PopulatingConverter<>();
        converter.setTargetClass(CommerceCartParameter.class);
        converter.setPopulators(Arrays.asList(commerceUpdateCartParameterPopulator()));
        return converter;
    }


    @Bean
    public CartModificationPopulator cartModificationPopulator()
    {
        CartModificationPopulator populator = new CartModificationPopulator();
        populator.setOrderLineItemConverter(orderLineItemConverter());
        return populator;
    }

    @Bean
    public Converter<CommerceCartModification, CartModificationData> cartModificationConverter()
    {
        PopulatingConverter<CommerceCartModification, CartModificationData> converter = new PopulatingConverter<>();
        converter.setTargetClass(CartModificationData.class);
        converter.setPopulators(Arrays.asList(cartModificationPopulator()));
        return converter;
    }

    @Bean
    public CartPopulator cartPopulator()
    {
        CartPopulator populator = new CartPopulator();
        populator.setOrderLineItemConverter(orderLineItemConverter());
        populator.setDeliveryModeConverter(deliveryModeConverter());
        populator.setDeliveryAddressConverter(deliveryAddressConverter());
        return populator;
    }

    @Bean
    public Converter<CartModel, CartData> cartConverter()
    {
        PopulatingConverter<CartModel, CartData> converter = new PopulatingConverter<>();
        converter.setTargetClass(CartData.class);
        converter.setPopulators(Arrays.asList(cartPopulator()));
        return converter;
    }

    @Bean
    public DeliveryModePopulator deliveryModePopulator()
    {
        return new DeliveryModePopulator();
    }

    @Bean
    public Converter<DeliveryModeModel, DeliveryModeData> deliveryModeConverter()
    {
        PopulatingConverter<DeliveryModeModel, DeliveryModeData> converter = new PopulatingConverter<>();
        converter.setTargetClass(DeliveryModeData.class);
        converter.setPopulators(Arrays.asList(deliveryModePopulator()));
        return converter;
    }

    @Bean
    public CustomerAddressReversePopulator customerAddressReversePopulator()
    {
        return new CustomerAddressReversePopulator();
    }

    @Bean
    public CustomerAddressPopulator customerAddressPopulator()
    {
        return new CustomerAddressPopulator();
    }

    @Bean
    public Converter<CustomerAddressModel, CustomerAddressData> customerAddressConverter()
    {
        PopulatingConverter<CustomerAddressModel, CustomerAddressData> converter = new PopulatingConverter<>();
        converter.setTargetClass(CustomerAddressData.class);
        converter.setPopulators(Arrays.asList(customerAddressPopulator()));
        return converter;
    }

    @Bean
    public DeliveryAddressReversePopulator deliveryAddressReversePopulator()
    {
        return new DeliveryAddressReversePopulator();
    }

    @Bean
    public DeliveryAddressPopulator deliveryAddressPopulator()
    {
        return new DeliveryAddressPopulator();
    }

    @Bean
    public Converter<DeliveryAddressModel, DeliveryAddressData> deliveryAddressConverter()
    {
        PopulatingConverter<DeliveryAddressModel, DeliveryAddressData> converter = new PopulatingConverter<>();
        converter.setTargetClass(DeliveryAddressData.class);
        converter.setPopulators(Arrays.asList(deliveryAddressPopulator()));
        return converter;
    }
}
