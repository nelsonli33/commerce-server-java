package com.shopflix.core.data;

import java.io.Serializable;

public class MediaImageData extends MediaData implements Serializable {

    private static final long serialVersionUID = -170224471653619759L;

    private String alt;
    private int width;
    private int height;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
