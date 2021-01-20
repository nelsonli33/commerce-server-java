package com.shopflix.core.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class ItemModel {

    @Column(name = "created_at", columnDefinition = "DATETIME", length = 6, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME", length = 6)
    @UpdateTimestamp
    private Date updatedAt;


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
