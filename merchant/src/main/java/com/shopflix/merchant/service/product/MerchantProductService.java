package com.shopflix.merchant.service.product;

import com.shopflix.core.data.form.ProductForm;
import com.shopflix.core.model.product.*;
import com.shopflix.merchant.data.ProductSearchCriteria;
import org.springframework.data.domain.Page;

public interface MerchantProductService {

    Page<ProductModel> getProducts(ProductSearchCriteria searchCriteria);

    ProductModel getProduct(Long id);

    ProductModel save(ProductModel productModel);

    void deleteProduct(ProductModel productModel);

    ProductOptionModel getProductOption(Long optionId);

    ProductOptionValueModel getProductOptionValue(Long optionValueId);

    ProductVariantModel getProductVariant(Long variantId);
}
