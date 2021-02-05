package com.shopflix.merchant.controller;

import com.google.cloud.storage.Storage;
import com.shopflix.core.data.PageData;
import com.shopflix.core.data.ProductImageData;
import com.shopflix.core.data.form.ProductForm;
import com.shopflix.core.model.product.ProductImageModel;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.response.ApiResult;
import com.shopflix.merchant.data.ProductData;
import com.shopflix.merchant.data.ProductOption;
import com.shopflix.merchant.data.ProductSearchCriteria;
import com.shopflix.merchant.facades.product.MerchantProductFacade;
import com.shopflix.merchant.service.media.StorageService;
import com.shopflix.merchant.service.product.MerchantProductImageService;
import com.shopflix.merchant.service.product.MerchantProductService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.Resizers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/merchant/api/v1/products")
public class MerchantProductController {

    @Resource(name = "merchantProductFacade")
    private MerchantProductFacade merchantProductFacade;

    @GetMapping
    public ResponseEntity<ApiResult<PageData<List<ProductData>>>> productList(
            @RequestParam(required = false, defaultValue = "1", value = "page") Integer page,
            @RequestParam(required = false, defaultValue = "50", value = "limit") Integer limit,
            @RequestParam(required = false, defaultValue = "DESC", value = "order") Sort.Direction direction,
            @RequestParam(required = false, defaultValue = "updatedAt", value = "sort") String sort,
            @RequestParam(required = false, value = "status") String status) {

        ProductSearchCriteria searchCriteria = new ProductSearchCriteria.Builder()
                .setPage(page)
                .setLimit(limit)
                .setDirection(direction)
                .setSort(sort)
                .setStatus(status)
                .build();

        PageData<List<ProductData>> products = merchantProductFacade.getProducts(searchCriteria, Collections.singleton(ProductOption.BASIC));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity productDetail(@PathVariable("id") Long id) {
        ProductData productData = merchantProductFacade.getProductForIdAndOptions(id, Collections.singleton(ProductOption.FULL));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(productData));
    }

    @PostMapping
    public ResponseEntity<ApiResult<ProductData>> postProduct(@RequestBody ProductForm productForm) {
        ProductData productData = merchantProductFacade.createProduct(productForm);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(productData));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResult<ProductData>> editProduct(@PathVariable Long id, @RequestBody ProductForm productForm) {
        ProductData productData = merchantProductFacade.editProduct(id, productForm);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(productData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        merchantProductFacade.deleteProduct(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }


    @PostMapping("/images")
    public ResponseEntity<ApiResult<ProductImageData>> uploadProductImage(@RequestParam("file") MultipartFile uploadFile) throws IOException {

        ProductImageData productImageData = merchantProductFacade.uploadProductImage(uploadFile);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(productImageData));
    }



}
