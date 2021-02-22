package com.shopflix.core.model.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shopflix.core.model.ItemModel;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.model.product.ProductVariantModel;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractOrderLineItemModel extends ItemModel {

    private String name;
    private Long quantity;
    private String sku;
    private Double price;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productId;

    @Column(name = "variant_id", insertable = false, updatable = false)
    private Long variantId;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductModel product;
    private String productName;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductVariantModel variant;
    private String variantName;

    private Boolean giveAway;
    private Double subtotal;
    private Double totalDiscounts;
    private Double totalPrice;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private AbstractOrderModel order;

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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductVariantModel getVariant() {
        return variant;
    }

    public void setVariant(ProductVariantModel variant) {
        this.variant = variant;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public Boolean getGiveAway()
    {
        return giveAway;
    }

    public void setGiveAway(Boolean giveAway)
    {
        this.giveAway = giveAway;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotalDiscounts() {
        return totalDiscounts;
    }

    public void setTotalDiscounts(Double totalDiscounts) {
        this.totalDiscounts = totalDiscounts;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public AbstractOrderModel getOrder()
    {
        return order;
    }

    public void setOrder(AbstractOrderModel order)
    {
        this.order = order;
    }
}
