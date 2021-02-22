package com.shopflix.storefront.services.order.impl;

import com.shopflix.core.model.order.CartLineItemModel;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.repository.order.CartRepository;
import com.shopflix.storefront.services.customer.CustomerService;
import com.shopflix.storefront.services.order.CartFactory;
import com.shopflix.storefront.services.order.CartService;

public class DefaultCartService extends DefaultAbstractOrderService<CartModel, CartLineItemModel> implements CartService
{
    private CustomerService customerService;
    private CartRepository cartRepository;
    private CartFactory cartFactory;

    @Override
    public CartModel getCartForCurrentCustomer()
    {
        final CartModel cart = cartRepository.findCartModelByCustomer(getCustomerService().getCurrentCustomer());

        if (cart != null) {
            return cart;
        } else {
            return getCartFactory().createCart();
        }

    }

    public CustomerService getCustomerService()
    {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public CartRepository getCartRepository()
    {
        return cartRepository;
    }

    public void setCartRepository(CartRepository cartRepository)
    {
        this.cartRepository = cartRepository;
    }

    public CartFactory getCartFactory()
    {
        return cartFactory;
    }

    public void setCartFactory(CartFactory cartFactory)
    {
        this.cartFactory = cartFactory;
    }
}
