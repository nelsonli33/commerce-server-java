package com.shopflix.core.model.product;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shopflix.core.enums.ProductStatus;
import com.shopflix.core.model.ItemModel;

import com.shopflix.core.model.category.CategoryModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@DynamicUpdate
public class ProductModel extends ItemModel
{
    private String code;
    private String name;
    private String summary;
    @Column(length = 65535, columnDefinition = "TEXT")
    private String description;
    private Integer quantity;
    private Double price;
    private Double weight;
    private String weightUnit;
    private String sku;
    private Double packageLength;
    private Double packageWidth;
    private Double packageHeight;
    private Integer daysToShip;
    private ProductStatus status;
    private Integer sold;
    private Integer maxOrderQuantity;
    private Integer minOrderQuantity;
    private String metaTitle;
    @Column(length = 1000, columnDefinition = "TEXT")
    private String metaDescription;

    @ManyToMany(
            cascade = { CascadeType.PERSIST, CascadeType.MERGE }
    )
    @JoinTable(
            name = "cat2productrel",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    Set<CategoryModel> categories = new HashSet<>();


    @ManyToMany(
            cascade = { CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "product2deliverymoderel",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "deliverymode_id")
    )
    Set<DeliveryModeModel> deliveryModes = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<ProductImageModel> images = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductOptionModel> options = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductVariantModel> variants = new ArrayList<>();



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

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
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

    public ProductStatus getStatus()
    {
        return status;
    }

    public void setStatus(ProductStatus status)
    {
        this.status = status;
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

    public Set<DeliveryModeModel> getDeliveryModes()
    {
        return deliveryModes;
    }

    public void setDeliveryModes(Set<DeliveryModeModel> deliveryModes)
    {
        this.deliveryModes = deliveryModes;
    }

    public Set<CategoryModel> getCategories()
    {
        return categories;
    }

    public void setCategories(Set<CategoryModel> categories)
    {
        this.categories = categories;
    }

    public List<ProductImageModel> getImages()
    {
        return images;
    }

    public void setImages(List<ProductImageModel> images)
    {
        this.images = images;
    }

    public List<ProductOptionModel> getOptions()
    {
        return options;
    }

    public void setOptions(List<ProductOptionModel> options)
    {
        this.options = options;
    }

    public List<ProductVariantModel> getVariants()
    {
        return variants;
    }

    public void setVariants(List<ProductVariantModel> variants)
    {
        this.variants = variants;
    }
}
