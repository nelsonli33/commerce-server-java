package com.shopflix.core.repository;

import com.shopflix.core.model.ProductImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImageModel, Long> {
}
