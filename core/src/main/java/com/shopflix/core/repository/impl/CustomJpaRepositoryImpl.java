package com.shopflix.core.repository.impl;

import com.shopflix.core.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

public class CustomJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
    implements CustomJpaRepository<T, ID>
{
    private final EntityManager em;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager)
    {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }


    @Override
    @Transactional
    public void refresh(T t)
    {
        if (em.contains(t)) {
            em.refresh(em.merge(t));
        }

    }
}
