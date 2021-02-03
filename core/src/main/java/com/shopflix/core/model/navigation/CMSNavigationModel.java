package com.shopflix.core.model.navigation;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shopflix.core.model.ItemModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cmsnavigations")
public class CMSNavigationModel extends ItemModel {

    private String code;
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "navigation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CMSNavigationLinkModel> links = new ArrayList<>();

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

    public List<CMSNavigationLinkModel> getLinks()
    {
        return links;
    }

    public void setLinks(List<CMSNavigationLinkModel> links)
    {
        this.links = links;
    }
}
