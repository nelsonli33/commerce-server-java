package com.shopflix.core.model.media;

import com.shopflix.core.model.ItemModel;

import javax.persistence.*;

@Entity
@Table(name = "medias")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MediaModel extends ItemModel {

    private String filename;
    private String originfilename;
    private String mime;


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getOriginfilename() {
        return originfilename;
    }

    public void setOriginfilename(String originfilename) {
        this.originfilename = originfilename;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }
}
