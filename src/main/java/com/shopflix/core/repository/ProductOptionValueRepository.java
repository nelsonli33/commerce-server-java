package com.shopflix.core.repository;

import com.shopflix.core.model.ProductOptionModel;
import com.shopflix.core.model.ProductOptionValueModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionValueRepository extends JpaRepository<ProductOptionValueModel, Long> {
}
