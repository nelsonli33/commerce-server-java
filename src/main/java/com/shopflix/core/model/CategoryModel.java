package com.shopflix.core.model;


import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryModel extends ItemModel {
    private String code;
    private String name;
    private String description;
    private Integer sortOrder;
    private Integer status;
    private String metaTitle;
    private String metaDescription;

    @OneToOne
    @JoinColumn(name = "image_id")
    private MediaImageModel image;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryModel parent;



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
