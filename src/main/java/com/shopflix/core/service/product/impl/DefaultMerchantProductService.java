package com.shopflix.core.service.product.impl;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.form.ProductForm;
import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.ProductModel;
import com.shopflix.core.repository.CategoryRepository;
import com.shopflix.core.repository.ProductRepository;
import com.shopflix.core.service.product.MerchantProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;
import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "merchantProductService")
public class DefaultMerchantProductService implements MerchantProductService {

    private ProductRepository productRepository;
    private Populator<ProductForm, ProductModel> productReversePopulator;


    @Override
    public Page<ProductModel> getProducts(int page, int size) {
        validateParameterNotNull(page > 0, "page must be greater than 0");
        validateParameterNotNull(size > 0, "size must be greater than 0");
        return productRepository.findAll(PageRequest.of(page - 1, size, Sort.by("updatedAt").descending()));
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

    public ProductModel createProduct(ProductForm productForm) {
        validateParameterNotNullStandardMessage("product form", productForm);

        ProductModel productModel = new ProductModel();
        productReversePopulator.populate(productForm, productModel);
        return productRepository.save(productModel);
    }

    @Override
    public ProductModel updateProduct(Long id, ProductForm productForm) {
        validateParameterNotNullStandardMessage("id", id);
        validateParameterNotNullStandardMessage("product form", productForm);


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
