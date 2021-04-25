package com.shopflix.core.model.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shopflix.core.model.ItemModel;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "productvariants")
@DynamicUpdate
public class ProductVariantModel extends ItemModel {
    private String name;
    private Double price;
    private Integer quantity;
    private String sku;
    private Integer sold;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductOptionValueModel optionValue1;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductOptionValueModel optionValue2;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductModel product;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public ProductOptionValueModel getOptionValue1() {
        return optionValue1;
    }

    public void setOptionValue1(ProductOptionValueModel optionValue1) {
        this.optionValue1 = optionValue1;
    }

    public ProductOptionValueModel getOptionValue2() {
        return optionValue2;
    }

    public void setOptionValue2(ProductOptionValueModel optionValue2) {
        this.optionValue2 = optionValue2;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }
}
