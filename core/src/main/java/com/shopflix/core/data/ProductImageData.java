package com.shopflix.core.data;

import java.io.Serializable;

public class ProductImageData implements Serializable {

    private static final long serialVersionUID = -4723196236176145251L;

    private String alt;
    private Integer position;
    private String originfilename;
    private String tiny;
    private String thumbnail;
    private String normal;
    private String detail;
    private String zoom;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getOriginfilename() {
        return originfilename;
    }

    public void setOriginfilename(String originfilename) {
        this.originfilename = originfilename;
    }

    public String getTiny() {
        return tiny;
    }

    public void setTiny(String tiny) {
        this.tiny = tiny;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }
}
