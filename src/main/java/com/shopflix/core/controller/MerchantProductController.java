package com.shopflix.core.controller;

import com.google.cloud.storage.Storage;
import com.shopflix.core.data.PageData;
import com.shopflix.core.data.ProductImageData;
import com.shopflix.core.data.form.ProductForm;
import com.shopflix.core.model.product.ProductImageModel;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.response.ApiResult;
import com.shopflix.core.service.media.StorageService;
import com.shopflix.core.service.product.MerchantProductImageService;
import com.shopflix.core.service.product.MerchantProductService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.Resizers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/merchant/api/v1/products")
public class MerchantProductController {

    @Value("${storage.bucketname}")
    private String BucketName;

    @Resource(name = "merchantProductService")
    private MerchantProductService merchantProductService;

    @Resource(name = "merchantProductImageService")
    private MerchantProductImageService merchantProductImageService;

    @Resource(name = "storageService")
    private StorageService storageService;

    @Autowired
    private Storage storage;

    @GetMapping
    public ResponseEntity productList(@RequestParam(name = "page", defaultValue = "1") int page,
                                      @RequestParam(name = "size", defaultValue = "50") int size) {
        Page<ProductModel> products = merchantProductService.getProducts(page, size);

        PageData pageData = new PageData();
        pageData.setData(products.getContent());
        pageData.setPage(page);
        pageData.setSize(size);
        pageData.setTotal(products.getNumberOfElements());
        pageData.setTotalPages(products.getTotalPages());


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(pageData));
    }

    @GetMapping("/{id}")
    public ResponseEntity productDetail(@PathVariable("id") Long id) {
        ProductModel productModel = merchantProductService.getProduct(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(productModel));
    }

    @PostMapping
    public ResponseEntity<ApiResult<ProductModel>> postProduct(@RequestBody ProductForm productForm) {
        ProductModel savedProductModel = merchantProductService.createProduct(productForm);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(savedProductModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResult<ProductModel>> editProduct(@PathVariable Long id, @RequestBody ProductForm productForm) {
        ProductModel productModel = merchantProductService.updateProduct(id, productForm);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(productModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        merchantProductService.deleteProduct(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }


    @PostMapping("/images")
    public ResponseEntity<ApiResult<ProductImageModel>> uploadProductImage(@RequestParam("file") MultipartFile uploadFile) throws IOException {

        // TODO: validate upload file mimetype only accept image/*
        String filename = String.join("", UUID.randomUUID().toString().split("-"));

        List<ProductImage> productImages = new ArrayList<>();
        // aspect ratio 2:3
        productImages.add(new ProductImage(filename.concat("_zoom"), 2000, 3000, new ByteArrayOutputStream()));
        productImages.add(new ProductImage(filename.concat("_detail"), 1280, 1920, new ByteArrayOutputStream()));
        productImages.add(new ProductImage(filename.concat("_normal"), 920, 1380, new ByteArrayOutputStream()));
        productImages.add(new ProductImage(filename.concat("_thumbnail"), 396, 594, new ByteArrayOutputStream()));
        productImages.add(new ProductImage(filename.concat("_tiny"), 198, 297, new ByteArrayOutputStream()));

        for (int i = 0; i < productImages.size(); i++) {
            ProductImage productImage = productImages.get(i);

            Thumbnails.of(uploadFile.getInputStream())
                    .size(productImage.getWidth(), productImage.getHeight())
                    .resizer(Resizers.BICUBIC)
                    .keepAspectRatio(true)
                    .toOutputStream(productImage.getOutputStream());

            storageService.sendFileToStorage(productImage.getOutputStream().toByteArray(),
                    productImage.getFilename(), uploadFile.getContentType());
        }

        ProductImageData productImageData = new ProductImageData();
        productImageData.setZoom(productImages.get(0).getFilename());
        productImageData.setDetail(productImages.get(1).getFilename());
        productImageData.setNormal(productImages.get(2).getFilename());
        productImageData.setThumbnail(productImages.get(3).getFilename());
        productImageData.setTiny(productImages.get(4).getFilename());
        productImageData.setOriginfilename(uploadFile.getOriginalFilename());
        productImageData.setAlt(uploadFile.getOriginalFilename());

        ProductImageModel productImageModel = merchantProductImageService.createProductImage(productImageData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(productImageModel));
    }


    private class ProductImage {
        private String filename;
        private int width;
        private int height;
        private ByteArrayOutputStream outputStream;

        public ProductImage(String filename, int width, int height, ByteArrayOutputStream outputStream) {
            this.filename = filename;
            this.width = width;
            this.height = height;
            this.outputStream = outputStream;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public ByteArrayOutputStream getOutputStream() {
            return outputStream;
        }

        public void setOutputStream(ByteArrayOutputStream outputStream) {
            this.outputStream = outputStream;
        }
    }
}
