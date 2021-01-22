package com.shopflix.core.service.category.impl;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.form.CategoryForm;
import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.exception.ParentSelfRefException;
import com.shopflix.core.model.CategoryModel;
import com.shopflix.core.repository.CategoryRepository;
import com.shopflix.core.service.category.MerchantCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "merchantCategoryService")
public class DefaultMerchantCategoryService implements MerchantCategoryService {


    private CategoryRepository categoryRepository;
    private Populator<CategoryForm, CategoryModel> categoryReversePopulator;

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
        validateParameterNotNullStandardMessage("id", id);
        Optional<CategoryModel> categoryModel = this.categoryRepository.findById(id);

        if (categoryModel.isPresent()) {
            return categoryModel.get();
        } else {
            throw new ModelNotFoundException("Category with id "+id+" requested for the object does not exists in the system");
        }
    }

    @Override
    public CategoryModel createCategory(CategoryForm categoryForm) {
        CategoryModel categoryModel = new CategoryModel();
        categoryReversePopulator.populate(categoryForm, categoryModel);
        return categoryRepository.save(categoryModel);
    }

    @Override
    public CategoryModel updateCategory(Long id, CategoryForm categoryForm) {
        CategoryModel categoryModel = getCategoryForId(id);

        if (categoryModel.getId().equals(categoryForm.getParentId())) {
            throw new ParentSelfRefException("Same object cannot be parent to itself. (Category Id:" + id + ")");
        }

        categoryReversePopulator.populate(categoryForm, categoryModel);
        return categoryRepository.save(categoryModel);
    }

    @Override
    public List<CategoryModel> bulkUpdateCategories(List<CategoryForm> categoryForms) {

        List<CategoryModel> categoryModels = new ArrayList<>();

        for (int i = 0; i < categoryForms.size(); i++) {
            CategoryForm categoryForm = categoryForms.get(i);
            CategoryModel categoryModel = getCategoryForId(categoryForm.getId());

            if (categoryModel.getId().equals(categoryForm.getParentId())) {
                throw new ParentSelfRefException("Same object cannot be parent to itself. (Category Id:" + categoryForm.getId() + ")");
            }

            categoryReversePopulator.populate(categoryForm, categoryModel);
            categoryModels.add(categoryModel);
        }


        return categoryRepository.saveAll(categoryModels);
    }

    @Override
    public void deleteCategory(Long id) {
        CategoryModel categoryModel = getCategoryForId(id);
        categoryRepository.delete(categoryModel);
    }



    @Resource(name = "categoryRepository")
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Resource(name = "categoryReversePopulator")
    public void setCategoryReversePopulator(Populator<CategoryForm, CategoryModel> categoryReversePopulator) {
        this.categoryReversePopulator = categoryReversePopulator;
    }



    public Populator<CategoryForm, CategoryModel> getCategoryReversePopulator() {
        return categoryReversePopulator;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

}
