package com.shopflix.core.repository.product;

import com.shopflix.core.model.product.ProductOptionValueModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionValueRepository extends JpaRepository<ProductOptionValueModel, Long> {
}
