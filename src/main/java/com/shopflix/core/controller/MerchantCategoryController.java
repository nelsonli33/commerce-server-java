package com.shopflix.core.controller;

import com.shopflix.core.data.CategoryData;
import com.shopflix.core.model.CategoryModel;
import com.shopflix.core.service.category.MerchantCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/merchant/api/v1")
public class MerchantCategoryController {

    @Resource(name = "merchantCategoryService")
    private MerchantCategoryService merchantCategoryService;

    @PostMapping("/categories")
    public ResponseEntity<CategoryModel> createCategory(@RequestBody CategoryData categoryData) {

        return new ResponseEntity<>(this.merchantCategoryService.createCategory(categoryData), HttpStatus.OK);
    }

}
