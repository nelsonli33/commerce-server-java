package com.shopflix.storefront.facades.order.impl;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.enums.PaymentModeType;
import com.shopflix.core.enums.PaymentStatus;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.model.order.delivery.DeliveryAddressModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import com.shopflix.core.service.ModelService;
import com.shopflix.storefront.facades.order.CartFacade;
import com.shopflix.storefront.facades.order.data.CartData;
import com.shopflix.storefront.facades.order.data.CommerceCheckoutParameter;
import com.shopflix.storefront.facades.order.data.DeliveryAddressData;
import com.shopflix.storefront.facades.order.data.DeliveryModeData;
import com.shopflix.storefront.facades.order.CheckoutFacade;
import com.shopflix.storefront.facades.user.data.CustomerAddressData;
import com.shopflix.storefront.services.customer.CustomerAccountService;
import com.shopflix.storefront.services.delivery.DeliveryService;
import com.shopflix.storefront.services.order.CartService;
import com.shopflix.storefront.services.order.CommerceCheckoutService;

import java.util.ArrayList;
import java.util.List;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

public class DefaultCheckoutFacade implements CheckoutFacade
{
    private CartFacade cartFacade;
    private CartService cartService;
    private CommerceCheckoutService commerceCheckoutService;
    private DeliveryService deliveryService;
    private CustomerAccountService customerAccountService;
    private ModelService modelService;
    private Populator<CustomerAddressData, DeliveryAddressModel> deliveryAddressReversePopulator;
    private Converter<DeliveryAddressModel, DeliveryAddressData> deliveryAddressConverter;
    private Converter<DeliveryModeModel, DeliveryModeData> deliveryModeConverter;


    @Override
    public CartData getCheckoutCart()
    {
        final CartData cartData = getCartFacade().getCart();
        if (cartData != null)
        {
            cartData.setDeliveryAddress(getDeliveryAddress());
            cartData.setDeliveryMode(getDeliveryMode());
//            cartData.setPaymentInfo(getPaymentDetails());
        }
        return cartData;
    }

    @Override
    public List<DeliveryModeData> getSupportedDeliveryModes()
    {
        final List<DeliveryModeData> result = new ArrayList<>();
        final CartModel cart = getCart();
        if (cart != null)
        {
            for (final DeliveryModeModel deliveryModeModel : getDeliveryService().getSupportedDeliveryModesForOrder(cart))
            {
                result.add(convert(deliveryModeModel));
            }
        }

        return result;
    }

    protected DeliveryModeData getDeliveryMode()
    {
        final CartModel cart = getCart();
        return cart == null || cart.getDeliveryMode() == null ? null : convert(cart.getDeliveryMode());
    }

    @Override
    public boolean setDeliveryMode(String deliveryModeCode)
    {
        validateParameterNotNullStandardMessage("deliveryModeCode", deliveryModeCode);

        final CartModel cart = getCart();
        if (cart != null && isSupportedDeliveryMode(deliveryModeCode, cart))
        {
            final DeliveryModeModel deliveryMode = getDeliveryService().getDeliveryModeForCode(deliveryModeCode);

            if (deliveryMode != null)
            {
                CommerceCheckoutParameter parameter = createCommerceCheckoutParameter(cart);
                parameter.setDeliveryMode(deliveryMode);
                return getCommerceCheckoutService().setDeliveryMode(parameter);
            }
        }
        return false;
    }

    protected DeliveryAddressData getDeliveryAddress()
    {
        final CartModel cart = getCart();
        if (cart != null)
        {
            final DeliveryAddressModel deliveryAddress = cart.getDeliveryAddress();
            if (deliveryAddress != null)
            {
                return getDeliveryAddressConverter().convert(deliveryAddress);
            }
        }
        return null;
    }

    @Override
    public boolean setDeliveryAddress(CustomerAddressData customerAddressData)
    {
        validateParameterNotNullStandardMessage("customerAddressData", customerAddressData);

        final CartModel cart = getCart();
        if (cart != null) {

            DeliveryAddressModel deliveryAddressModel = cart.getDeliveryAddress() != null ? editDeliveryAddressModel(customerAddressData, cart) :
                    createDeliveryAddressModel(customerAddressData, cart);


            CommerceCheckoutParameter parameter = createCommerceCheckoutParameter(cart);
            parameter.setDeliveryAddress(deliveryAddressModel);
            return getCommerceCheckoutService().setDeliveryAddress(parameter);
        }

        return false;
    }

    @Override
    public boolean setPaymentMode(String paymentModeCode)
    {
        validateParameterNotNullStandardMessage("paymentModeCode", paymentModeCode);

        final PaymentModeType paymentMode = PaymentModeType.from(paymentModeCode);

        if (paymentMode != null) {
            final CartModel cart = getCart();
            cart.setPaymentMode(paymentMode);
            cart.setPaymentStatus(PaymentStatus.UNPAID);
            getModelService().save(cart);
            return true;
        }

        return false;
    }


    protected boolean isSupportedDeliveryMode(final String deliveryModeCode, final CartModel cartModel)
    {
        for (final DeliveryModeModel supportedDeliveryMode : getDeliveryService().getSupportedDeliveryModesForOrder(cartModel))
        {
            if (deliveryModeCode.equalsIgnoreCase(supportedDeliveryMode.getCode()))
            {
                return true;
            }
        }
        return false;
    }


    protected CartModel getCart()
    {
        return getCartService().getCartForCurrentCustomer();
    }


    protected DeliveryModeData convert(final DeliveryModeModel deliveryModeModel)
    {

        final DeliveryModeData deliveryModeData = getDeliveryModeConverter().convert(deliveryModeModel);

        final CartModel cart = getCart();

        final Double deliveryCost = getDeliveryService().getDeliveryCostForDeliveryModeAndOrder(deliveryModeModel, cart);

        deliveryModeData.setDeliveryCost(deliveryCost);

        return deliveryModeData;
    }


    protected CommerceCheckoutParameter createCommerceCheckoutParameter(final CartModel cart)
    {
        final CommerceCheckoutParameter parameter = new CommerceCheckoutParameter();
        parameter.setCart(cart);
        return parameter;
    }



    protected DeliveryAddressModel createDeliveryAddressModel(final CustomerAddressData customerAddressData, final CartModel cartModel) {
        DeliveryAddressModel deliveryAddressModel = new DeliveryAddressModel();
        getDeliveryAddressReversePopulator().populate(customerAddressData, deliveryAddressModel);
        deliveryAddressModel.setOrder(cartModel);
        getModelService().save(deliveryAddressModel);
        return deliveryAddressModel;
    }

    protected DeliveryAddressModel editDeliveryAddressModel(final CustomerAddressData customerAddressData, final CartModel cartModel) {
        final DeliveryAddressModel deliveryAddressModel = cartModel.getDeliveryAddress();
        validateParameterNotNullStandardMessage("deliveryAddressModel",  deliveryAddressModel);

        getDeliveryAddressReversePopulator().populate(customerAddressData, deliveryAddressModel);
        getModelService().save(deliveryAddressModel);
        return deliveryAddressModel;
    }

    public CartFacade getCartFacade()
    {
        return cartFacade;
    }

    public void setCartFacade(CartFacade cartFacade)
    {
        this.cartFacade = cartFacade;
    }

    public CartService getCartService()
    {
        return cartService;
    }

    public void setCartService(CartService cartService)
    {
        this.cartService = cartService;
    }

    public CommerceCheckoutService getCommerceCheckoutService()
    {
        return commerceCheckoutService;
    }

    public void setCommerceCheckoutService(CommerceCheckoutService commerceCheckoutService)
    {
        this.commerceCheckoutService = commerceCheckoutService;
    }

    public DeliveryService getDeliveryService()
    {
        return deliveryService;
    }

    public void setDeliveryService(DeliveryService deliveryService)
    {
        this.deliveryService = deliveryService;
    }

    public Populator<CustomerAddressData, DeliveryAddressModel> getDeliveryAddressReversePopulator()
    {
        return deliveryAddressReversePopulator;
    }

    public void setDeliveryAddressReversePopulator(Populator<CustomerAddressData, DeliveryAddressModel> deliveryAddressReversePopulator)
    {
        this.deliveryAddressReversePopulator = deliveryAddressReversePopulator;
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    public CustomerAccountService getCustomerAccountService()
    {
        return customerAccountService;
    }

    public void setCustomerAccountService(CustomerAccountService customerAccountService)
    {
        this.customerAccountService = customerAccountService;
    }

    public Converter<DeliveryAddressModel, DeliveryAddressData> getDeliveryAddressConverter()
    {
        return deliveryAddressConverter;
    }

    public void setDeliveryAddressConverter(Converter<DeliveryAddressModel, DeliveryAddressData> deliveryAddressConverter)
    {
        this.deliveryAddressConverter = deliveryAddressConverter;
    }

    public Converter<DeliveryModeModel, DeliveryModeData> getDeliveryModeConverter()
    {
        return deliveryModeConverter;
    }

    public void setDeliveryModeConverter(Converter<DeliveryModeModel, DeliveryModeData> deliveryModeConverter)
    {
        this.deliveryModeConverter = deliveryModeConverter;
    }
}
