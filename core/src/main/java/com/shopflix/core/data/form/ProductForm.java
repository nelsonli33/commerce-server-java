package com.shopflix.core.data.form;

import java.io.Serializable;
import java.util.List;

public class ProductForm implements Serializable {

    private static final long serialVersionUID = -4231794994049179628L;

    public List<Long> categoryIds;
    public String code;
    public String name;
    public String summary;
    public String description;
    public Double price;
    public Integer quantity;
    public String sku;
    public Double packageLength;
    public Double packageWidth;
    public Double packageHeight;
    public Double weight;
    public String weightUnit;
    public Integer daysToShip;
    public String status;
    public Integer minOrderQuantity;
    public Integer maxOrderQuantity;
    public String metaTitle;
    public String metaDescription;
    public List<Long> imageIds;
    public List<ProductOptionForm> options;
    public List<ProductVariantForm> variants;
    public List<ProductImageForm> images;

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Double getPackageLength() {
        return packageLength;
    }

    public void setPackageLength(Double packageLength) {
        this.packageLength = packageLength;
    }

    public Double getPackageWidth() {
        return packageWidth;
    }

    public void setPackageWidth(Double packageWidth) {
        this.packageWidth = packageWidth;
    }

    public Double getPackageHeight() {
        return packageHeight;
    }

    public void setPackageHeight(Double packageHeight) {
        this.packageHeight = packageHeight;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public Integer getDaysToShip() {
        return daysToShip;
    }

    public void setDaysToShip(Integer daysToShip) {
        this.daysToShip = daysToShip;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Integer getMinOrderQuantity() {
        return minOrderQuantity;
    }

    public void setMinOrderQuantity(Integer minOrderQuantity) {
        this.minOrderQuantity = minOrderQuantity;
    }

    public Integer getMaxOrderQuantity() {
        return maxOrderQuantity;
    }

    public void setMaxOrderQuantity(Integer maxOrderQuantity) {
        this.maxOrderQuantity = maxOrderQuantity;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public List<Long> getImageIds() {
        return imageIds;
    }

    public void setImageIds(List<Long> imageIds) {
        this.imageIds = imageIds;
    }

    public List<ProductOptionForm> getOptions() {
        return options;
    }

    public void setOptions(List<ProductOptionForm> options) {
        this.options = options;
    }

    public List<ProductVariantForm> getVariants() {
        return variants;
    }

    public void setVariants(List<ProductVariantForm> variants) {
        this.variants = variants;
    }

    public List<ProductImageForm> getImages()
    {
        return images;
    }

    public void setImages(List<ProductImageForm> images)
    {
        this.images = images;
    }
}
