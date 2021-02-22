package com.shopflix.storefront.facades.order.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.model.product.ProductVariantModel;
import com.shopflix.storefront.data.order.AddToCartParams;
import com.shopflix.storefront.data.order.CommerceCartParameter;
import com.shopflix.storefront.data.order.SKUProduct;
import com.shopflix.storefront.services.order.CartService;
import com.shopflix.storefront.services.product.ProductService;
import com.shopflix.storefront.services.product.SKUProductFactory;

/**
 * Populates common data of add-to-cart creation to pass it from facade to service layer
 */
public class CommerceCartParameterPopulator implements Populator<AddToCartParams, CommerceCartParameter>
{
    private CartService cartService;
    private ProductService productService;
    private SKUProductFactory skuProductFactory;

    @Override
    public void populate(AddToCartParams addToCartParams, CommerceCartParameter parameter) throws ConversionException
    {
        final ProductModel productModel = getProductService().getProductForId(addToCartParams.getProductId());
        ProductVariantModel variantModel = null;
        if (addToCartParams.getVariantId() != null) {
            variantModel = getProductService().getVariantForProduct(productModel, addToCartParams.getVariantId());
        }
        final SKUProduct skuProduct = getSkuProductFactory().createSKUProduct(productModel, variantModel);

        final CartModel cart = getCartService().getCartForCurrentCustomer();

        parameter.setCart(cart);
        parameter.setSkuProduct(skuProduct);
        parameter.setQuantity(addToCartParams.getQty());
    }

    public CartService getCartService()
    {
        return cartService;
    }

    public void setCartService(CartService cartService)
    {
        this.cartService = cartService;
    }

    public ProductService getProductService()
    {
        return productService;
    }

    public void setProductService(ProductService productService)
    {
        this.productService = productService;
    }

    public SKUProductFactory getSkuProductFactory()
    {
        return skuProductFactory;
    }

    public void setSkuProductFactory(SKUProductFactory skuProductFactory)
    {
        this.skuProductFactory = skuProductFactory;
    }
}
