package com.shopflix.core.repository;

import com.shopflix.core.model.product.ProductOptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionRepository extends JpaRepository<ProductOptionModel, Long> {
}
