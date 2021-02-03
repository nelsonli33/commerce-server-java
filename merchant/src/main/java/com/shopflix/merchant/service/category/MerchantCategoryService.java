package com.shopflix.merchant.service.category;

import com.shopflix.core.data.form.CategoryForm;
import com.shopflix.core.model.category.CategoryModel;

import java.util.List;

public interface MerchantCategoryService {

    List<CategoryModel> getAllCategories();

    List<CategoryModel> getAllCategoriesByIds(List<Long> ids);

    CategoryModel getCategoryForId(Long id);

    CategoryModel save(CategoryModel categoryModel);

    List<CategoryModel> saveAll(List<CategoryModel> categoryModels);

    void addParentCategory(CategoryModel categoryModel, Long parentId);


    List<CategoryModel> orderingCategories(List<CategoryModel> categoryModels);

    void deleteCategory(CategoryModel categoryModel);

}
