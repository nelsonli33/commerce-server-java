package com.shopflix.storefront.services.order;

import com.shopflix.core.model.order.CartLineItemModel;
import com.shopflix.core.model.order.CartModel;

public interface CartService extends AbstractOrderService<CartModel, CartLineItemModel>
{
    CartModel getCartForCurrentCustomer();
}
