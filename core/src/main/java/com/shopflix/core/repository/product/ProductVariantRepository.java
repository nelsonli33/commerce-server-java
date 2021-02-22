package com.shopflix.core.repository.product;

import com.shopflix.core.model.product.ProductVariantModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantRepository extends JpaRepository<ProductVariantModel, Long> {
}
