package com.shopflix.core.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
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
    @ColumnDefault(value = "1")
    private Integer status;
    private String metaTitle;
    private String metaDescription;

    @OneToOne
    @JoinColumn(name = "image_id")
    private MediaImageModel image;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private CategoryModel parent;


    @ManyToMany(mappedBy = "categories")
    private Set<ProductModel> products = new HashSet<>();


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public MediaImageModel getImage() {
        return image;
    }

    public void setImage(MediaImageModel image) {
        this.image = image;
    }

    public CategoryModel getParent() {
        return parent;
    }

    public void setParent(CategoryModel parent) {
        this.parent = parent;
    }
}
