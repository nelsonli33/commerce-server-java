package com.shopflix.storefront.services.order;

import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.storefront.facades.order.data.CommerceCartModification;
import com.shopflix.storefront.facades.order.data.CommerceCartParameter;
import com.shopflix.storefront.facades.order.data.SKUProduct;
import com.shopflix.storefront.exceptions.CommerceCartModificationException;

public interface CommerceCartService {

    /**
     * Adds to the (existing) {@link CartModel} the (existing) {@link ProductModel} in the given <code>quantity</code>.
     * If in the cart already an line item with the given product
     * the given <code>quantity</code> is added to the the quantity of this cart line item/
     *
     * @param parameter
     *           - A parameter object containing all attributes needed for add to cart
     *           <P>
     *           {@link CommerceCartParameter#cart} - The user's cart
     *           {@link CommerceCartParameter#skuProduct} - The {@link SKUProduct} to add {@link CommerceCartParameter#quantity} - The quantity to add
     *           </P>
     * @return the cart modification data that includes a statusCode and the actual quantity added to the cart
     * @throws CommerceCartModificationException
     *            if the <code>product</code> is a base product OR the quantity is less than 1 or any other reason the cart could
     *            not be modified.
     */
    CommerceCartModification addToCart(final CommerceCartParameter parameter) throws CommerceCartModificationException;


    /**
     * Update quantity for the cart line item with given <code>lineItemId</code> with the given <code>newQuantity</code>.
     * Then cart is calculated.
     *
     * @param parameter
     *           - A parameter object containing all attributes needed for add to cart
     *           <P>
     *           {@link CommerceCartParameter#cart} - The user's cart
     *           {@link CommerceCartParameter#skuProduct} - The {@link SKUProduct} to add {@link CommerceCartParameter#quantity} - The quantity to add
     *           </P>
     * @return the cart modification data that includes a statusCode and the actual quantity that the line item was updated
     *         to
     * @throws CommerceCartModificationException
     *            if the <code>product</code> is a base product OR the quantity is less than 1 or no usable unit was
     *            found (only when given <code>unit</code> is also <code>null</code>) or any other reason the cart could
     *            not be modified.
     */
    CommerceCartModification updateQuantityForCartLineItem(final CommerceCartParameter parameter)
            throws CommerceCartModificationException;



    void removeAllLineItems(final CommerceCartParameter parameter);
}
