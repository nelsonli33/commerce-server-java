package com.shopflix.merchant.service.product.impl;

import com.shopflix.core.data.product.ProductStatus;
import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.product.*;
import com.shopflix.core.repository.product.ProductOptionRepository;
import com.shopflix.core.repository.product.ProductOptionValueRepository;
import com.shopflix.core.repository.product.ProductRepository;
import com.shopflix.core.repository.product.ProductVariantRepository;
import com.shopflix.core.util.ServicesUtil;
import com.shopflix.merchant.data.ProductSearchCriteria;
import com.shopflix.merchant.service.product.MerchantProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "merchantProductService")
public class DefaultMerchantProductService implements MerchantProductService {

    private ProductRepository productRepository;
    private ProductOptionRepository productOptionRepository;
    private ProductOptionValueRepository productOptionValueRepository;
    private ProductVariantRepository productVariantRepository;


    private PageRequest genPageRequest(int page, int limit, Sort.Direction direction, String sort) {
        return PageRequest.of(page - 1, limit, Sort.by(direction,sort));
    }

    @Override
    public Page<ProductModel> getProducts(ProductSearchCriteria searchCriteria) {
        ServicesUtil.validateParameterNotNull(searchCriteria.getPage() > 0, "page must be greater than 0");
        ServicesUtil.validateParameterNotNull(searchCriteria.getLimit() > 0, "limit must be greater than 0");

        PageRequest pageRequest = genPageRequest(searchCriteria.getPage(), searchCriteria.getLimit(),
                searchCriteria.getDirection(), searchCriteria.getSort());

        return productRepository.findAll(getProductSpecification(searchCriteria), pageRequest);
    }

    private Specification<ProductModel> getProductSpecification(ProductSearchCriteria filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(filter.getStatus())) {
                predicates.add(cb.equal(root.get(ProductModel_.STATUS), ProductStatus.from(filter.getStatus())));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    @Override
    public ProductModel getProduct(Long id) {
        validateParameterNotNullStandardMessage("id", id);

        Optional<ProductModel> productModel = productRepository.findById(id);
        if (productModel.isPresent()) {
            return productModel.get();
        }

        throw new ModelNotFoundException("Product with id "+id+" requested for the object does not exists in the system");
    }

    public ProductModel save(ProductModel productModel) {
        validateParameterNotNullStandardMessage("product model", productModel);
        return productRepository.save(productModel);
    }



    @Override
    public void deleteProduct(ProductModel productModel) {
        productRepository.delete(productModel);
    }

    @Override
    public ProductOptionModel getProductOption(Long optionId)
    {
        validateParameterNotNullStandardMessage("optionId", optionId);
        Optional<ProductOptionModel> productOptionModel = productOptionRepository.findById(optionId);
        if (productOptionModel.isPresent()) {
            return productOptionModel.get();
        }
        throw new ModelNotFoundException("Product Option with id "+optionId+" requested for the object does not exists in the system");
    }

    @Override
    public ProductOptionValueModel getProductOptionValue(Long optionValueId)
    {
        validateParameterNotNullStandardMessage("optionValueId", optionValueId);
        Optional<ProductOptionValueModel> productOptionValueModel = productOptionValueRepository.findById(optionValueId);
        if (productOptionValueModel.isPresent()) {
            return productOptionValueModel.get();
        }
        throw new ModelNotFoundException("Product Option Value with id "+optionValueId+" requested for the object does not exists in the system");
    }

    @Override
    public ProductVariantModel getProductVariant(Long variantId)
    {
        validateParameterNotNullStandardMessage("variantId", variantId);
        Optional<ProductVariantModel> productVariantModel = productVariantRepository.findById(variantId);
        if (productVariantModel.isPresent()) {
            return productVariantModel.get();
        }
        throw new ModelNotFoundException("Product Variant with id "+variantId+" requested for the object does not exists in the system");
    }


    public ProductRepository getProductRepository() {
        return productRepository;
    }

    @Resource(name = "productRepository")
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductOptionRepository getProductOptionRepository()
    {
        return productOptionRepository;
    }

    @Resource(name = "productOptionRepository")
    public void setProductOptionRepository(ProductOptionRepository productOptionRepository)
    {
        this.productOptionRepository = productOptionRepository;
    }

    public ProductOptionValueRepository getProductOptionValueRepository()
    {
        return productOptionValueRepository;
    }

    @Resource(name = "productOptionValueRepository")
    public void setProductOptionValueRepository(ProductOptionValueRepository productOptionValueRepository)
    {
        this.productOptionValueRepository = productOptionValueRepository;
    }

    public ProductVariantRepository getProductVariantRepository()
    {
        return productVariantRepository;
    }

    @Resource(name = "productVariantRepository")
    public void setProductVariantRepository(ProductVariantRepository productVariantRepository)
    {
        this.productVariantRepository = productVariantRepository;
    }
}
