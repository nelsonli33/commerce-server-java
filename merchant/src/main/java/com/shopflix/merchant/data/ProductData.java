package com.shopflix.merchant.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopflix.core.data.ProductImageData;
import com.shopflix.core.data.product.ProductStatus;

import java.math.BigDecimal;
import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductData
{
    private Long id;
    private String code;
    private String name;
    private String summary;
    private String description;
    private String status;
    private Integer quantity;
    private BigDecimal price;
    private Double weight;
    private String weightUnit;
    private String sku;
    private Double packageLength;
    private Double packageWidth;
    private Double packageHeight;
    private Integer daysToShip;
    private Integer sold;
    private Integer maxOrderQuantity;
    private Integer minOrderQuantity;
    private String metaTitle;
    private String metaDescription;
    private Collection<CategoryData> categories;
    private Collection<ProductOptionData> options;
    private Collection<ProductVariantData> variants;
    private Collection<ProductImageData> images;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public Double getWeight()
    {
        return weight;
    }

    public void setWeight(Double weight)
    {
        this.weight = weight;
    }

    public String getWeightUnit()
    {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit)
    {
        this.weightUnit = weightUnit;
    }

    public String getSku()
    {
        return sku;
    }

    public void setSku(String sku)
    {
        this.sku = sku;
    }

    public Double getPackageLength()
    {
        return packageLength;
    }

    public void setPackageLength(Double packageLength)
    {
        this.packageLength = packageLength;
    }

    public Double getPackageWidth()
    {
        return packageWidth;
    }

    public void setPackageWidth(Double packageWidth)
    {
        this.packageWidth = packageWidth;
    }

    public Double getPackageHeight()
    {
        return packageHeight;
    }

    public void setPackageHeight(Double packageHeight)
    {
        this.packageHeight = packageHeight;
    }

    public Integer getDaysToShip()
    {
        return daysToShip;
    }

    public void setDaysToShip(Integer daysToShip)
    {
        this.daysToShip = daysToShip;
    }

    public Integer getSold()
    {
        return sold;
    }

    public void setSold(Integer sold)
    {
        this.sold = sold;
    }

    public Integer getMaxOrderQuantity()
    {
        return maxOrderQuantity;
    }

    public void setMaxOrderQuantity(Integer maxOrderQuantity)
    {
        this.maxOrderQuantity = maxOrderQuantity;
    }

    public Integer getMinOrderQuantity()
    {
        return minOrderQuantity;
    }

    public void setMinOrderQuantity(Integer minOrderQuantity)
    {
        this.minOrderQuantity = minOrderQuantity;
    }

    public String getMetaTitle()
    {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle)
    {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription()
    {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription)
    {
        this.metaDescription = metaDescription;
    }

    public Collection<CategoryData> getCategories()
    {
        return categories;
    }

    public void setCategories(Collection<CategoryData> categories)
    {
        this.categories = categories;
    }

    public Collection<ProductOptionData> getOptions()
    {
        return options;
    }

    public void setOptions(Collection<ProductOptionData> options)
    {
        this.options = options;
    }

    public Collection<ProductVariantData> getVariants()
    {
        return variants;
    }

    public void setVariants(Collection<ProductVariantData> variants)
    {
        this.variants = variants;
    }

    public Collection<ProductImageData> getImages()
    {
        return images;
    }

    public void setImages(Collection<ProductImageData> images)
    {
        this.images = images;
    }
}
