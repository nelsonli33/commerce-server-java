package com.shopflix.merchant.facades.category;

import com.shopflix.merchant.data.CategoryData;

import java.util.List;

public interface MerchantCategoryFacade
{
    List<CategoryData> getAllCategories();

    CategoryData getCategoryForId(Long id);

    CategoryData postCategory(CategoryData categoryData);

    CategoryData editCategory(Long id, CategoryData categoryData);

    void deleteCategory(Long id);

    void orderingCategories(List<CategoryData> categoryDataList);
}
