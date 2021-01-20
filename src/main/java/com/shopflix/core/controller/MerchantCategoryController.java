package com.shopflix.core.controller;

import com.shopflix.core.data.CategoryData;
import com.shopflix.core.model.CategoryModel;
import com.shopflix.core.response.ApiResult;
import com.shopflix.core.service.category.MerchantCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/merchant/api/v1")
public class MerchantCategoryController {

    @Resource(name = "merchantCategoryService")
    private MerchantCategoryService merchantCategoryService;


    @GetMapping("/categories")
    public ResponseEntity categories() {
        List<CategoryModel> categoryModels = merchantCategoryService.getAllCategories();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(categoryModels));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity categoryDetail(@PathVariable("id") Long id) {
        CategoryModel categoryModels = merchantCategoryService.getCategoryForId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(categoryModels));
    }

    @PostMapping("/categories")
    public ResponseEntity newCategory(@RequestBody CategoryData categoryData) {
        CategoryModel categoryModel = merchantCategoryService.createCategory(categoryData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(categoryModel));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity editCategory(@PathVariable Long id, @RequestBody CategoryData data) {
        CategoryModel updatedCategoryModel = merchantCategoryService.updateCategory(id, data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(updatedCategoryModel));
    }

    @PutMapping("/categories/bulk_update")
    public ResponseEntity bulkEditCategories(@RequestBody List<CategoryData> data) {

        List<CategoryModel> updatedCategoryModels = merchantCategoryService.bulkUpdateCategories(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(updatedCategoryModels));
    }


    @DeleteMapping("/categories/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Long id) {
        merchantCategoryService.deleteCategory(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(ApiResult.success());
    }
}
