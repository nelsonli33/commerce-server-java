package com.shopflix.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CustomJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>
{
    void refresh(T t);
}