package com.shopflix.core.repository;

import com.shopflix.core.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<ProductModel, Long> {

}
