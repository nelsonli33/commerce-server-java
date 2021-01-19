package com.shopflix.core.service.category;

import com.shopflix.core.data.CategoryData;
import com.shopflix.core.model.CategoryModel;

public interface MerchantCategoryService {
    CategoryModel createCategory(CategoryData data);
}
