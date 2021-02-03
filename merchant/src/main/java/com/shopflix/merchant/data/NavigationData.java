package com.shopflix.merchant.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class NavigationData {

    private Long id;
    private String code;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<NavigationLinkData> links;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public List<NavigationLinkData> getLinks()
    {
        return links;
    }

    public void setLinks(List<NavigationLinkData> links)
    {
        this.links = links;
    }
}
