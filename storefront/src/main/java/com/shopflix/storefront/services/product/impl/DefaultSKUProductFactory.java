package com.shopflix.storefront.services.product.impl;

import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.model.product.ProductVariantModel;
import com.shopflix.storefront.data.order.SKUProduct;
import com.shopflix.storefront.services.product.SKUProductFactory;

public class DefaultSKUProductFactory implements SKUProductFactory
{
    @Override
    public SKUProduct createSKUProduct(ProductModel product, ProductVariantModel variant)
    {
        SKUProduct skuProduct = new SKUProduct();
        String name = product.getName();
        Double price = product.getPrice();
        Long quantity = product.getQuantity();
        String sku = product.getSku();

        skuProduct.setVariantType(Boolean.FALSE);
        skuProduct.setProductId(product.getId());

        if (variant != null) {
            name = name.concat(" - ").concat(variant.getName());
            price = variant.getPrice();
            quantity = variant.getQuantity();
            sku = variant.getSku();
            skuProduct.setVariant(variant);
            skuProduct.setVariantId(variant.getId());
            skuProduct.setVariantType(Boolean.TRUE);
        }


        skuProduct.setName(name);
        skuProduct.setPrice(price);
        skuProduct.setSku(sku);
        skuProduct.setQuantity(quantity);
        skuProduct.setProduct(product);
        // TODO: add giveaway to product model
        skuProduct.setGiveAway(Boolean.FALSE);

        return skuProduct;
    }


}
