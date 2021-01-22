package com.shopflix.core.controller;

import com.shopflix.core.data.PageData;
import com.shopflix.core.data.form.ProductForm;
import com.shopflix.core.model.ProductModel;
import com.shopflix.core.response.ApiResult;
import com.shopflix.core.service.product.MerchantProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/merchant/api/v1/products")
public class MerchantProductController {

    @Resource(name = "merchantProductService")
    private MerchantProductService merchantProductService;

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
}
