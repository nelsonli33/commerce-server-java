package com.shopflix.core.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public class ItemModel implements Serializable
{
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


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ItemModel)) return false;
        ItemModel itemModel = (ItemModel) o;
        return getId() != null && getId().equals(itemModel.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId());
    }

    public String toString() {
        return this.getId() == null ? this.getClass().getSimpleName() + " (<unsaved>)" : this.getClass().getSimpleName() + " (" + this.getId().toString() + ")";
    }
}
