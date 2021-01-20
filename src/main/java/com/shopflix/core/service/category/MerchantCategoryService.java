package com.shopflix.core.service.category;

import com.shopflix.core.data.CategoryData;
import com.shopflix.core.model.CategoryModel;

import java.util.List;

public interface MerchantCategoryService {

    List<CategoryModel> getAllCategories();

    CategoryModel getCategoryForId(Long id);

    CategoryModel createCategory(CategoryData categoryData);

    CategoryModel updateCategory(Long id, CategoryData categoryData);

    List<CategoryModel> bulkUpdateCategories(List<CategoryData> dataList);

    void deleteCategory(Long id);

}
