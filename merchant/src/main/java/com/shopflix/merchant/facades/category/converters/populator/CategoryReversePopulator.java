package com.shopflix.merchant.facades.category.converters.populator;


import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.form.CategoryForm;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.category.CategoryModel;
import com.shopflix.core.model.media.MediaImageModel;
import com.shopflix.merchant.data.CategoryData;
import com.shopflix.merchant.service.category.MerchantCategoryService;
import com.shopflix.merchant.service.media.MerchantMediaService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;

public class CategoryReversePopulator implements Populator<CategoryData, CategoryModel> {

    @Override
    public void populate(CategoryData source, CategoryModel target) throws ConversionException {
        Assert.notNull(source, "Parameter categoryData cannot be null.");
        Assert.notNull(target, "Parameter categoryModel cannot be null.");

        target.setCode(source.getCode());
        target.setDescription(source.getDescription());
        target.setName(source.getName());
        target.setSortOrder(source.getSortOrder());
        target.setMetaTitle(source.getMetaTitle());
        target.setMetaDescription(source.getMetaDescription());
    }
}
