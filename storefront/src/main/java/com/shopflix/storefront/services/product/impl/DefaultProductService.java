package com.shopflix.storefront.services.product.impl;

import com.shopflix.core.enums.ProductStatus;
import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.model.product.ProductModel_;
import com.shopflix.core.model.product.ProductVariantModel;
import com.shopflix.core.repository.product.ProductRepository;
import com.shopflix.storefront.services.product.ProductService;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultProductService implements ProductService
{
    private ProductRepository productRepository;

    @Override
    public ProductModel getProductForId(Long productId)
    {
        final Optional<ProductModel> productModel = productRepository.findOne(getProductSpecification(productId));
        if (productModel.isPresent()) {
            return productModel.get();
        }
        throw new ModelNotFoundException("Product with id "+productId+" requested for the object does not exists in the system");
    }

    public ProductVariantModel getVariantForProduct(ProductModel product, Long variantId) {

        for(ProductVariantModel variant : product.getVariants()) {
            if (variant.getId().equals(variantId)) {
                return variant;
            }
        }
        throw new ModelNotFoundException("Product Variant with id "+variantId+" does not exists in product with id "+product.getId());
    }


    private Specification<ProductModel> getProductSpecification(Long productId) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get(ProductModel_.ID), productId));
            predicates.add(cb.equal(root.get(ProductModel_.STATUS), ProductStatus.ACTIVE));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public ProductRepository getProductRepository()
    {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }
}
