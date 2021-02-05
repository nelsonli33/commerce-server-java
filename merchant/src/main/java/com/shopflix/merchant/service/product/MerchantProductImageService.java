package com.shopflix.merchant.service.product;

import com.shopflix.core.data.ProductImageData;
import com.shopflix.core.model.product.ProductImageModel;
import com.shopflix.merchant.service.product.impl.DefaultMerchantProductImageService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MerchantProductImageService {

    List<ProductImage> uploadProductImage(MultipartFile uploadFile) throws IOException;

    ProductImageModel getProductImageForId(Long id);

    ProductImageModel save(ProductImageModel productImageModel);

}
