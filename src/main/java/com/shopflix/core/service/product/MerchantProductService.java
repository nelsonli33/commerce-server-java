package com.shopflix.core.service.product;

import com.shopflix.core.data.form.ProductForm;
import com.shopflix.core.data.form.ProductOptionForm;
import com.shopflix.core.data.form.ProductVariantForm;
import com.shopflix.core.model.ProductModel;
import com.shopflix.core.model.ProductOptionModel;
import com.shopflix.core.model.ProductVariantModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MerchantProductService {

    Page<ProductModel> getProducts(int page, int size);

    ProductModel getProduct(Long id);

    ProductModel createProduct(ProductForm productForm);

    ProductModel updateProduct(Long id, ProductForm productForm);

    void deleteProduct(Long id);
}
