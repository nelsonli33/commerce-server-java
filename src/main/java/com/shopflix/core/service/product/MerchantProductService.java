package com.shopflix.core.service.product;

import com.shopflix.core.data.form.ProductForm;
import com.shopflix.core.model.product.ProductModel;
import org.springframework.data.domain.Page;

public interface MerchantProductService {

    Page<ProductModel> getProducts(int page, int size);

    ProductModel getProduct(Long id);

    ProductModel createProduct(ProductForm productForm);

    ProductModel updateProduct(Long id, ProductForm productForm);

    void deleteProduct(Long id);
}
