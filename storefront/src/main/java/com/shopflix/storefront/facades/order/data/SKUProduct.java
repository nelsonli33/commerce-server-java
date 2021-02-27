package com.shopflix.storefront.facades.order.data;

import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.model.product.ProductVariantModel;

import java.io.Serializable;

public class SKUProduct implements Serializable {

    private static final long serialVersionUID = -3467609006855388103L;

    private String name;

    private Long quantity;

    private Double price;

    private Long productId;

    private ProductModel product;

    private Long variantId;

    private ProductVariantModel variant;

    private String sku;

    private Boolean giveAway;

    private Boolean variantType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getVariantId()
    {
        return variantId;
    }

    public void setVariantId(Long variantId)
    {
        this.variantId = variantId;
    }

    public ProductVariantModel getVariant() {
        return variant;
    }

    public void setVariant(ProductVariantModel variant) {
        this.variant = variant;
    }

    public String getSku()
    {
        return sku;
    }

    public void setSku(String sku)
    {
        this.sku = sku;
    }

    public Boolean getGiveAway()
    {
        return giveAway;
    }

    public void setGiveAway(Boolean giveAway)
    {
        this.giveAway = giveAway;
    }

    public Boolean isVariantType()
    {
        return variantType;
    }

    public void setVariantType(Boolean variantType)
    {
        this.variantType = variantType;
    }
}
