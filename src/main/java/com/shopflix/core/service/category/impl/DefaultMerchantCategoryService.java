package com.shopflix.core.service.category.impl;

import com.shopflix.core.data.CategoryData;
import com.shopflix.core.model.CategoryModel;
import com.shopflix.core.repository.CategoryRepository;
import com.shopflix.core.service.category.MerchantCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "merchantCategoryService")
public class DefaultMerchantCategoryService implements MerchantCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public CategoryModel createCategory(CategoryData data) {
        CategoryModel model = new CategoryModel();
        model.setCode(data.getCode());
        model.setDescription(data.getDescription());
        model.setName(data.getName());
        model.setStatus(data.getStatus());
        model.setSortOrder(data.getSortOrder());
        model.setMetaTitle(data.getMetaTitle());
        model.setMetaDescription(data.getMetaDescription());
        if (data.getParentId() != null) {
            CategoryModel parent = categoryRepository.findById(data.getParentId()).orElse(null);
            model.setParent(parent);
        }
        return categoryRepository.save(model);
    }
}
