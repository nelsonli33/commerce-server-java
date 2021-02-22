package com.shopflix.merchant.service.category.impl;

import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.category.CategoryModel;
import com.shopflix.core.repository.category.CategoryRepository;
import com.shopflix.core.util.ServicesUtil;
import com.shopflix.merchant.service.category.MerchantCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service(value = "merchantCategoryService")
public class DefaultMerchantCategoryService implements MerchantCategoryService {


    private CategoryRepository categoryRepository;


    @Override
    public List<CategoryModel> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public List<CategoryModel> getAllCategoriesByIds(List<Long> ids) {
        List<CategoryModel> categoryModels = this.categoryRepository.findAllById(ids);
        if (CollectionUtils.isEmpty(categoryModels)) {
            throw new ModelNotFoundException("Category with ids: " + StringUtils.join(ids, ',') + " not found.");
        }
        return categoryModels;
    }

    @Override
    public CategoryModel getCategoryForId(Long id) {
        ServicesUtil.validateParameterNotNullStandardMessage("id", id);
        Optional<CategoryModel> categoryModel = this.categoryRepository.findById(id);
        if (categoryModel.isPresent()) {
            return categoryModel.get();
        }
        throw new ModelNotFoundException("Category with id "+id+" requested for the object does not exists in the system");
    }

    @Override
    public CategoryModel save(CategoryModel categoryModel)
    {
        return categoryRepository.saveAndFlush(categoryModel);
    }

    @Override
    public List<CategoryModel> saveAll(List<CategoryModel> categoryModels)
    {
        return categoryRepository.saveAll(categoryModels);
    }

    @Override
    public void addParentCategory(CategoryModel categoryModel, Long parentId)
    {
        CategoryModel parentCategory = null;
        if (parentId != null) {
            parentCategory = getCategoryForId(parentId);
            categoryModel.getChildren().add(parentCategory);
        }
        categoryModel.setParent(parentCategory);
        categoryRepository.save(categoryModel);
    }


    @Override
    public List<CategoryModel> orderingCategories(List<CategoryModel> categoryModels) {



        return categoryRepository.saveAll(categoryModels);
    }

    @Override
    public void deleteCategory(CategoryModel categoryModel) {
        categoryRepository.delete(categoryModel);
    }



    @Resource(name = "categoryRepository")
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

}
