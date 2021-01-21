package com.shopflix.core.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "created_at", columnDefinition = "DATETIME", length = 6, updatable = false, nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME", length = 6)
    @UpdateTimestamp
    private Date updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
