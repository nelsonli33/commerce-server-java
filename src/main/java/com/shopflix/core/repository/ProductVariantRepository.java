package com.shopflix.core.repository;

import com.shopflix.core.model.ProductOptionModel;
import com.shopflix.core.model.ProductVariantModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantRepository extends JpaRepository<ProductVariantModel, Long> {
}
