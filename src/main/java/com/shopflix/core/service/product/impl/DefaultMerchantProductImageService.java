package com.shopflix.core.service.product.impl;

import com.shopflix.core.data.ProductImageData;
import com.shopflix.core.model.ProductImageModel;
import com.shopflix.core.repository.ProductImageRepository;
import com.shopflix.core.service.product.MerchantProductImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "merchantProductImageService")
public class DefaultMerchantProductImageService implements MerchantProductImageService {

    private ProductImageRepository productImageRepository;


    public ProductImageModel createProductImage(ProductImageData productImageData) {
        ProductImageModel productImageModel = new ProductImageModel();
        productImageModel.setZoom(productImageData.getZoom());
        productImageModel.setDetail(productImageData.getDetail());
        productImageModel.setNormal(productImageData.getNormal());
        productImageModel.setThumbnail(productImageData.getThumbnail());
        productImageModel.setTiny(productImageData.getTiny());
        productImageModel.setOriginfilename(productImageData.getOriginfilename());
        productImageModel.setAlt(productImageData.getOriginfilename());
        return productImageRepository.save(productImageModel);
    }



    @Resource(name = "productImageRepository")
    public void setProductImageRepository(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    public ProductImageRepository getProductImageRepository() {
        return productImageRepository;
    }

}
