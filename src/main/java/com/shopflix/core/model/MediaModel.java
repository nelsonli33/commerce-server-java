package com.shopflix.core.model;

import javax.persistence.*;

@Entity
@Table(name = "medias")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MediaModel extends ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
