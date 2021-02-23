package com.shopflix.merchant.facades.category.impl;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.MediaImageData;
import com.shopflix.core.data.form.CategoryForm;
import com.shopflix.core.model.category.CategoryModel;
import com.shopflix.core.model.media.MediaImageModel;
import com.shopflix.core.service.ModelService;
import com.shopflix.merchant.data.CategoryData;
import com.shopflix.merchant.facades.category.MerchantCategoryFacade;
import com.shopflix.merchant.service.category.MerchantCategoryService;
import com.shopflix.merchant.service.media.MerchantMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class DefaultMerchantCategoryFacade implements MerchantCategoryFacade
{

    private MerchantCategoryService merchantCategoryService;
    private MerchantMediaService merchantMediaService;
    private ModelService modelService;
    private Populator<CategoryData, CategoryModel> categoryReversePopulator;
    private Converter<CategoryModel, CategoryData> categoryConverter;


    @Override
    public List<CategoryData> getAllCategories()
    {
        List<CategoryModel> allCategories = merchantCategoryService.getAllCategories();
        return categoryConverter.convertAll(allCategories);
    }

    @Override
    public CategoryData getCategoryForId(Long id)
    {
        return categoryConverter.convert(merchantCategoryService.getCategoryForId(id));
    }

    @Override
    public CategoryData postCategory(CategoryData categoryData)
    {
        CategoryModel categoryModel = new CategoryModel();
        categoryReversePopulator.populate(categoryData, categoryModel);

        merchantCategoryService.addParentCategory(categoryModel, categoryData.getParentId());

        if (categoryData.getImageId() != null) {
            MediaImageModel mediaImageModel = merchantMediaService.getMediaImageForId(categoryData.getImageId());
            categoryModel.setImage(mediaImageModel);
        }

        getModelService().save(categoryModel);

        return categoryConverter.convert(categoryModel);
    }

    @Override
    public CategoryData editCategory(Long id, CategoryData categoryData)
    {
        CategoryModel categoryModel = merchantCategoryService.getCategoryForId(id);
        categoryReversePopulator.populate(categoryData, categoryModel);

        merchantCategoryService.addParentCategory(categoryModel, categoryData.getParentId());

        if (categoryData.getImageId() != null) {
            MediaImageModel mediaImageModel = merchantMediaService.getMediaImageForId(categoryData.getImageId());
            categoryModel.setImage(mediaImageModel);
        }

        getModelService().save(categoryModel);

        return categoryConverter.convert(categoryModel);
    }

    @Override
    public void deleteCategory(Long id)
    {
        CategoryModel categoryModel = merchantCategoryService.getCategoryForId(id);
        getModelService().remove(categoryModel);
    }


    public void orderingCategories(List<CategoryData> categoryDataList) {
        List<CategoryModel> models = new ArrayList<>();

        for(CategoryData categoryData : categoryDataList) {
            CategoryModel model = merchantCategoryService.getCategoryForId(categoryData.getId());
            model.setSortOrder(categoryData.getSortOrder());
            models.add(model);
        }
        getModelService().saveAll(models);
    }

    public MerchantCategoryService getMerchantCategoryService()
    {
        return merchantCategoryService;
    }

    public void setMerchantCategoryService(MerchantCategoryService merchantCategoryService)
    {
        this.merchantCategoryService = merchantCategoryService;
    }

    public MerchantMediaService getMerchantMediaService()
    {
        return merchantMediaService;
    }

    public void setMerchantMediaService(MerchantMediaService merchantMediaService)
    {
        this.merchantMediaService = merchantMediaService;
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    public Populator<CategoryData, CategoryModel> getCategoryReversePopulator()
    {
        return categoryReversePopulator;
    }

    public void setCategoryReversePopulator(Populator<CategoryData, CategoryModel> categoryReversePopulator)
    {
        this.categoryReversePopulator = categoryReversePopulator;
    }

    public Converter<CategoryModel, CategoryData> getCategoryConverter()
    {
        return categoryConverter;
    }

    public void setCategoryConverter(Converter<CategoryModel, CategoryData> categoryConverter)
    {
        this.categoryConverter = categoryConverter;
    }
}
