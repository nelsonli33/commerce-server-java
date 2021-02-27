package com.shopflix.storefront.services.product;

import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.model.product.ProductVariantModel;
import com.shopflix.storefront.facades.order.data.SKUProduct;

public interface SKUProductFactory
{
    SKUProduct createSKUProduct(ProductModel product, ProductVariantModel productVariant);
}
