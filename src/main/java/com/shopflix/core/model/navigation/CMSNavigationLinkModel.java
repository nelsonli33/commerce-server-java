package com.shopflix.core.model.navigation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shopflix.core.model.ItemModel;

import javax.persistence.*;


@Entity
@Table(name = "cmsnavigationlinks")
public class CMSNavigationLinkModel extends ItemModel {

    // This is link type for category, page,...etc.
    private String type;
    private Integer valueId;
    private String name;
    private String url;
    private Boolean target;
    private Integer sortOrder;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private CMSNavigationModel navigation;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getTarget() {
        return target;
    }

    public void setTarget(Boolean target) {
        this.target = target;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public CMSNavigationModel getNavigation() {
        return navigation;
    }

    public void setNavigation(CMSNavigationModel navigation) {
        this.navigation = navigation;
    }
}
