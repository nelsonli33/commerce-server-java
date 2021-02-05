package com.shopflix.merchant.facades.product;

import com.shopflix.core.data.PageData;
import com.shopflix.core.data.ProductImageData;
import com.shopflix.core.data.form.ProductForm;
import com.shopflix.merchant.data.ProductData;
import com.shopflix.merchant.data.ProductOption;
import com.shopflix.merchant.data.ProductSearchCriteria;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

public interface MerchantProductFacade
{
    PageData<List<ProductData>> getProducts(ProductSearchCriteria searchCriteria, Collection<ProductOption> options);

    ProductData getProductForIdAndOptions(Long id, Collection<ProductOption> options);

    ProductData createProduct(ProductForm form);

    ProductData editProduct(Long id, ProductForm form);

    void deleteProduct(Long id);

    ProductImageData uploadProductImage(MultipartFile uploadFile);
}
