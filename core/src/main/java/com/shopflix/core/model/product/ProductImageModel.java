package com.shopflix.core.model.product;

import com.shopflix.core.model.ItemModel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productimages")
public class ProductImageModel extends ItemModel {

    private String alt;
    private Integer position;
    private String filename;
    private String originfilename;
    private String tiny;
    private String thumbnail;
    private String normal;
    private String detail;
    private String zoom;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductOptionValueModel optionValue;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductModel product;

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

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

    public ProductOptionValueModel getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(ProductOptionValueModel optionValue) {
        this.optionValue = optionValue;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }
}
