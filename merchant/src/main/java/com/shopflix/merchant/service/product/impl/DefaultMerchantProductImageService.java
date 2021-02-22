package com.shopflix.merchant.service.product.impl;

import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.product.ProductImageModel;
import com.shopflix.core.repository.product.ProductImageRepository;
import com.shopflix.merchant.service.media.StorageService;
import com.shopflix.merchant.service.product.MerchantProductImageService;
import com.shopflix.merchant.service.product.ProductImage;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.Resizers;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service(value = "merchantProductImageService")
public class DefaultMerchantProductImageService implements MerchantProductImageService
{
    private StorageService storageService;
    private ProductImageRepository productImageRepository;


    public List<ProductImage> uploadProductImage(MultipartFile uploadFile) throws IOException
    {
        // TODO: validate upload file mimetype only accept image/*
        String filename = String.join("", UUID.randomUUID().toString().split("-"));

        // aspect ratio 2:3
        List<ProductImage> productImages = new ArrayList<>(Arrays.asList(
                new ProductImage(filename.concat("_zoom"), 2000, 3000, new ByteArrayOutputStream()),
                new ProductImage(filename.concat("_detail"), 1280, 1920, new ByteArrayOutputStream()),
                new ProductImage(filename.concat("_normal"), 920, 1380, new ByteArrayOutputStream()),
                new ProductImage(filename.concat("_thumbnail"), 396, 594, new ByteArrayOutputStream()),
                new ProductImage(filename.concat("_tiny"), 198, 297, new ByteArrayOutputStream())
        ));

        for (int i = 0; i < productImages.size(); i++)
        {
            ProductImage productImage = productImages.get(i);

            Thumbnails.of(uploadFile.getInputStream())
                    .size(productImage.getWidth(), productImage.getHeight())
                    .resizer(Resizers.BICUBIC)
                    .keepAspectRatio(true)
                    .toOutputStream(productImage.getOutputStream());

            storageService.sendFileToStorage(productImage.getOutputStream().toByteArray(),
                    productImage.getFilename(), uploadFile.getContentType());
        }

        return productImages;
    }

    @Override
    public ProductImageModel getProductImageForId(Long id)
    {
        Optional<ProductImageModel> productImageModel = productImageRepository.findById(id);
        if (productImageModel.isPresent()) {
            return productImageModel.get();
        }
        throw new ModelNotFoundException("Product Image with id "+id+" requested for the object does not exists in the system");
    }


    public ProductImageModel save(ProductImageModel productImageModel)
    {
        return productImageRepository.save(productImageModel);
    }


    public ProductImageRepository getProductImageRepository()
    {
        return productImageRepository;
    }

    @Resource(name = "productImageRepository")
    public void setProductImageRepository(ProductImageRepository productImageRepository)
    {
        this.productImageRepository = productImageRepository;
    }

    public StorageService getStorageService()
    {
        return storageService;
    }

    @Resource(name = "storageService")
    public void setStorageService(StorageService storageService)
    {
        this.storageService = storageService;
    }

}
