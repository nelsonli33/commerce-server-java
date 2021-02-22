package com.shopflix.storefront.services.product;

import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.model.product.ProductVariantModel;

public interface ProductService
{
    ProductModel getProductForId(Long productId);

    ProductVariantModel getVariantForProduct(ProductModel product, Long variantId);
}
