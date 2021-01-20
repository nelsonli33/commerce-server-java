package com.shopflix.core.data;

import java.io.Serializable;

public class MediaData implements Serializable {


    private static final long serialVersionUID = 6990949911914899373L;
    private Long id;
    private String code;
    private String filename;
    private String mime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

}
