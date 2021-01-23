package com.shopflix.core.service.product;

import com.shopflix.core.data.ProductImageData;
import com.shopflix.core.model.product.ProductImageModel;

public interface MerchantProductImageService {
    ProductImageModel createProductImage(ProductImageData productImageData);
}
