package com.shopflix.core.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.CategoryData;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.CategoryModel;
import com.shopflix.core.service.category.MerchantCategoryService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Component("categoryReversePopulator")
public class DefaultCategoryReversePopulator implements Populator<CategoryData, CategoryModel> {

    private MerchantCategoryService merchantCategoryService;

    @Override
    public void populate(CategoryData categoryData, CategoryModel categoryModel) throws ConversionException {
        Assert.notNull(categoryData, "Parameter categoryData cannot be null.");
        Assert.notNull(categoryModel, "Parameter categoryModel cannot be null.");

        categoryModel.setCode(categoryData.getCode());
        categoryModel.setDescription(categoryData.getDescription());
        categoryModel.setName(categoryData.getName());
        categoryModel.setStatus(categoryData.getStatus());
        categoryModel.setSortOrder(categoryData.getSortOrder());
        categoryModel.setMetaTitle(categoryData.getMetaTitle());
        categoryModel.setMetaDescription(categoryData.getMetaDescription());
        categoryModel.setParent(null);

        try {
            if (categoryData.getParentId() != null) {
                CategoryModel parent = getMerchantCategoryService().getCategoryForId(categoryData.getParentId());
                categoryModel.setParent(parent);
            }
        } catch (ModelNotFoundException ex) {
            throw new ConversionException("No category with the id " + categoryData.getParentId() + " found.", ex);
        }

    }

    @Resource(name = "merchantCategoryService")
    public void setMerchantCategoryService(MerchantCategoryService merchantCategoryService) {
        this.merchantCategoryService = merchantCategoryService;
    }

    public MerchantCategoryService getMerchantCategoryService() {
        return merchantCategoryService;
    }


}
