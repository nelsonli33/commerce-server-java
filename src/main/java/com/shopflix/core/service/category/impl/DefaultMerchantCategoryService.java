package com.shopflix.core.service.category.impl;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.CategoryData;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.exception.ParentSelfRefException;
import com.shopflix.core.model.CategoryModel;
import com.shopflix.core.repository.CategoryRepository;
import com.shopflix.core.service.category.MerchantCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "merchantCategoryService")
public class DefaultMerchantCategoryService implements MerchantCategoryService {


    private CategoryRepository categoryRepository;
    private Populator<CategoryData, CategoryModel> categoryReversePopulator;

    @Override
    public List<CategoryModel> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public CategoryModel getCategoryForId(Long id) {
        validateParameterNotNullStandardMessage("id", id);
        Optional<CategoryModel> categoryModel = this.categoryRepository.findById(id);

        if (categoryModel.isPresent()) {
            return categoryModel.get();
        } else {
            throw new ModelNotFoundException("ID requested for the object does not exists in the system");
        }
    }

    @Override
    public CategoryModel createCategory(CategoryData categoryData) {
        CategoryModel categoryModel = new CategoryModel();
        categoryReversePopulator.populate(categoryData, categoryModel);
        return categoryRepository.save(categoryModel);
    }

    @Override
    public CategoryModel updateCategory(Long id, CategoryData categoryData) {
        CategoryModel categoryModel = getCategoryForId(id);

        if (categoryModel.getId().equals(categoryData.getParentId())) {
            throw new ParentSelfRefException("Same object cannot be parent to itself. (Category Id:"+id+")");
        }

        categoryReversePopulator.populate(categoryData, categoryModel);
        return categoryRepository.save(categoryModel);
    }

    @Override
    public List<CategoryModel> bulkUpdateCategories(List<CategoryData> dataList) {

        List<CategoryModel> categoryModels = new ArrayList<>();

        for(int i = 0; i < dataList.size(); i++) {
            CategoryData categoryData = dataList.get(i);
            CategoryModel categoryModel = getCategoryForId(categoryData.getId());

            if (categoryModel.getId().equals(categoryData.getParentId())) {
                throw new ParentSelfRefException("Same object cannot be parent to itself. (Category Id:"+categoryData.getId()+")");
            }

            categoryReversePopulator.populate(categoryData, categoryModel);
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
    public void setCategoryReversePopulator(Populator<CategoryData, CategoryModel> categoryReversePopulator) {
        this.categoryReversePopulator = categoryReversePopulator;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public Populator<CategoryData, CategoryModel> getCategoryReversePopulator() {
        return categoryReversePopulator;
    }

}
