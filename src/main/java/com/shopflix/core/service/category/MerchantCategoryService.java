package com.shopflix.core.service.category;

import com.shopflix.core.data.form.CategoryForm;
import com.shopflix.core.model.category.CategoryModel;

import java.util.List;

public interface MerchantCategoryService {

    List<CategoryModel> getAllCategories();

    List<CategoryModel> getAllCategoriesByIds(List<Long> ids);

    CategoryModel getCategoryForId(Long id);

    CategoryModel createCategory(CategoryForm categoryForm);

    CategoryModel updateCategory(Long id, CategoryForm categoryForm);

    List<CategoryModel> bulkUpdateCategories(List<CategoryForm> categoryForms);

    void deleteCategory(Long id);

}
