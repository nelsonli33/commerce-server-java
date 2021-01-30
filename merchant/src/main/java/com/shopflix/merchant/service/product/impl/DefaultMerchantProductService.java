package com.shopflix.merchant.service.product.impl;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.form.ProductForm;
import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.repository.ProductRepository;
import com.shopflix.core.util.ServicesUtil;
import com.shopflix.merchant.service.product.MerchantProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service(value = "merchantProductService")
public class DefaultMerchantProductService implements MerchantProductService {

    private ProductRepository productRepository;
    private Populator<ProductForm, ProductModel> productReversePopulator;


    @Override
    public Page<ProductModel> getProducts(int page, int size) {
        ServicesUtil.validateParameterNotNull(page > 0, "page must be greater than 0");
        ServicesUtil.validateParameterNotNull(size > 0, "size must be greater than 0");
        return productRepository.findAll(PageRequest.of(page - 1, size, Sort.by("updatedAt").descending()));
    }

    @Override
    public ProductModel getProduct(Long id) {
        ServicesUtil.validateParameterNotNullStandardMessage("id", id);

        Optional<ProductModel> productModel = productRepository.findById(id);
        if (productModel.isPresent()) {
            return productModel.get();
        }

        throw new ModelNotFoundException("Product with id "+id+" requested for the object does not exists in the system");
    }

    public ProductModel createProduct(ProductForm productForm) {
        ServicesUtil.validateParameterNotNullStandardMessage("product form", productForm);

        ProductModel productModel = new ProductModel();
        productReversePopulator.populate(productForm, productModel);
        return productRepository.save(productModel);
    }

    @Override
    public ProductModel updateProduct(Long id, ProductForm productForm) {
        ServicesUtil.validateParameterNotNullStandardMessage("id", id);
        ServicesUtil.validateParameterNotNullStandardMessage("product form", productForm);


        ProductModel productModel = getProduct(id);
        productReversePopulator.populate(productForm, productModel);
        return productRepository.save(productModel);
    }

    @Override
    public void deleteProduct(Long id) {
        ProductModel productModel = getProduct(id);
        productRepository.delete(productModel);
    }




    @Resource(name = "productRepository")
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Resource(name = "productReversePopulator")
    public void setProductReversePopulator(Populator<ProductForm, ProductModel> productReversePopulator) {
        this.productReversePopulator = productReversePopulator;
    }

    public Populator<ProductForm, ProductModel> getProductReversePopulator() {
        return productReversePopulator;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

}
