package com.shopflix.merchant.controller;

import com.shopflix.core.data.form.CategoryForm;
import com.shopflix.core.response.ApiResult;
import com.shopflix.merchant.data.CategoryData;
import com.shopflix.merchant.facades.category.MerchantCategoryFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/merchant/api/v1/categories")
public class MerchantCategoryController {


    @Resource(name = "merchantCategoryFacade")
    private MerchantCategoryFacade merchantCategoryFacade;


    @GetMapping
    public ResponseEntity<ApiResult<List<CategoryData>>> categories() {
        List<CategoryData> data = merchantCategoryFacade.getAllCategories();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<CategoryData>> categoryDetail(@PathVariable("id") Long categoryId) {
        CategoryData categoryData = merchantCategoryFacade.getCategoryForId(categoryId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(categoryData));
    }

    @PostMapping
    public ResponseEntity<ApiResult<CategoryData>> postCategory(@RequestBody CategoryForm form) {

        CategoryData data = fillCategoryFormToData(form);
        CategoryData categoryData = merchantCategoryFacade.postCategory(data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(categoryData));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResult<CategoryData>> editCategory(@PathVariable("id") Long categoryId, @RequestBody CategoryForm form) {

        CategoryData data = fillCategoryFormToData(form);
        CategoryData categoryData = merchantCategoryFacade.editCategory(categoryId, data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(categoryData));
    }



    @PutMapping("/ordering")
    public ResponseEntity<ApiResult<List<Object>>> bulkEditCategories(@RequestBody List<CategoryForm> forms) {


        List<CategoryData> categoryDataList = forms.stream().map(form -> {
            CategoryData categoryData = new CategoryData();
            categoryData.setId(form.getId());
            categoryData.setSortOrder(form.getSortOrder());
            return categoryData;
        }).collect(Collectors.toList());


        merchantCategoryFacade.orderingCategories(categoryDataList);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long categoryId) {
        merchantCategoryFacade.deleteCategory(categoryId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }


    private CategoryData fillCategoryFormToData(@RequestBody CategoryForm form)
    {
        CategoryData data = new CategoryData();
        data.setCode(form.getCode());
        data.setName(form.getName());
        data.setDescription(form.getDescription());
        data.setSortOrder(form.getSortOrder());
        data.setMetaTitle(form.getMetaTitle());
        data.setMetaDescription(form.getMetaDescription());
        data.setParentId(form.getParentId());
        data.setImageId(form.getImageId());
        return data;
    }


}
