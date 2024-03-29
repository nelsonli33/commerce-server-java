package com.shopflix.core.model.category;


import com.shopflix.core.model.ItemModel;
import com.shopflix.core.model.media.MediaImageModel;
import com.shopflix.core.model.product.ProductModel;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@DynamicUpdate
public class CategoryModel extends ItemModel {
    private String code;
    private String name;
    private String description;
    private Integer sortOrder;
    private String metaTitle;
    private String metaDescription;

    @Column(name = "parent_id", insertable = false, updatable = false)
    private Long parentId;

    @Column(name = "image_id", insertable = false, updatable = false)
    private Long imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CategoryModel parent;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="parent")
    private Set<CategoryModel> children = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "image_id")
    private MediaImageModel image;

    @ManyToMany(mappedBy = "categories")
    private Set<ProductModel> products = new HashSet<>();


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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
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

    public MediaImageModel getImage()
    {
        return image;
    }

    public void setImage(MediaImageModel image)
    {
        this.image = image;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getImageId()
    {
        return imageId;
    }

    public void setImageId(Long imageId)
    {
        this.imageId = imageId;
    }

    public CategoryModel getParent()
    {
        return parent;
    }

    public void setParent(CategoryModel parent)
    {
        this.parent = parent;
    }

    public Set<CategoryModel> getChildren()
    {
        return children;
    }

    public void setChildren(Set<CategoryModel> children)
    {
        this.children = children;
    }

    public Set<ProductModel> getProducts()
    {
        return products;
    }

    public void setProducts(Set<ProductModel> products)
    {
        this.products = products;
    }
}
