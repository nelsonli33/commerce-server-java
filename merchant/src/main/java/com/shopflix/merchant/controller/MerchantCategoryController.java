package com.shopflix.merchant.controller;

import com.shopflix.core.data.form.CategoryForm;
import com.shopflix.core.model.category.CategoryModel;
import com.shopflix.core.response.ApiResult;
import com.shopflix.merchant.service.category.MerchantCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/merchant/api/v1/categories")
public class MerchantCategoryController {

    @Resource(name = "merchantCategoryService")
    private MerchantCategoryService merchantCategoryService;


    @GetMapping
    public ResponseEntity<ApiResult<List<CategoryModel>>> categories() {
        List<CategoryModel> categoryModels = merchantCategoryService.getAllCategories();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(categoryModels));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<CategoryModel>> categoryDetail(@PathVariable("id") Long id) {
        CategoryModel categoryModels = merchantCategoryService.getCategoryForId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(categoryModels));
    }

    @PostMapping
    public ResponseEntity<ApiResult<CategoryModel>> newCategory(@RequestBody CategoryForm categoryForm) {
        CategoryModel categoryModel = merchantCategoryService.createCategory(categoryForm);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(categoryModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResult<CategoryModel>> editCategory(@PathVariable Long id, @RequestBody CategoryForm categoryForm) {
        CategoryModel updatedCategoryModel = merchantCategoryService.updateCategory(id, categoryForm);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(updatedCategoryModel));
    }

    @PutMapping("/bulk_update")
    public ResponseEntity<ApiResult<List<CategoryModel>>> bulkEditCategories(@RequestBody List<CategoryForm> categoryForms) {
        List<CategoryModel> updatedCategoryModels = merchantCategoryService.bulkUpdateCategories(categoryForms);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(updatedCategoryModels));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        merchantCategoryService.deleteCategory(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }


}
