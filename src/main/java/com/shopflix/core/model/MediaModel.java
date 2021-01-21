package com.shopflix.core.model;

import javax.persistence.*;

@Entity
@Table(name = "medias")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MediaModel extends ItemModel {

    private String filename;
    private String originFilename;
    private String mime;


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getOriginFilename() {
        return originFilename;
    }

    public void setOriginFilename(String originFilename) {
        this.originFilename = originFilename;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }
}
