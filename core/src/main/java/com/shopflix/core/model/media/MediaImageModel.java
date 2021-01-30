package com.shopflix.core.model.media;

import javax.persistence.*;

@Entity
@Table(name = "mediaimages")
public class MediaImageModel extends MediaModel {

    @Column(columnDefinition="int default null")
    private Integer width;
    @Column(columnDefinition="int default null")
    private Integer height;
    private String alt;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
