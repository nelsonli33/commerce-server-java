package com.shopflix.merchant.facades.category.converters.populator;


import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.form.CategoryForm;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.category.CategoryModel;
import com.shopflix.core.model.media.MediaImageModel;
import com.shopflix.merchant.service.category.MerchantCategoryService;
import com.shopflix.merchant.service.media.MerchantMediaService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Component("categoryReversePopulator")
public class DefaultCategoryReversePopulator implements Populator<CategoryForm, CategoryModel> {

    private MerchantCategoryService merchantCategoryService;
    private MerchantMediaService merchantMediaService;

    @Override
    public void populate(CategoryForm source, CategoryModel target) throws ConversionException {
        Assert.notNull(source, "Parameter categoryData cannot be null.");
        Assert.notNull(target, "Parameter categoryModel cannot be null.");

        target.setCode(source.getCode());
        target.setDescription(source.getDescription());
        target.setName(source.getName());
        target.setStatus(source.getStatus());
        target.setSortOrder(source.getSortOrder());
        target.setMetaTitle(source.getMetaTitle());
        target.setMetaDescription(source.getMetaDescription());
        target.setParent(null);


        try {
            if (source.getImageId() == null) {
                target.setImage(null);
            } else {
                MediaImageModel mediaImageModel =
                        getMerchantMediaService().getMediaImageForId(source.getImageId());
                target.setImage(mediaImageModel);
            }
        } catch (ModelNotFoundException ex) {
            throw new ConversionException("No media image with the id " + source.getImageId() + " found.", ex);
        }

        try {
            if (source.getParentId() == null) {
                target.setParent(null);
            } else {
                CategoryModel parent = getMerchantCategoryService().getCategoryForId(source.getParentId());
                target.setParent(parent);
            }
        } catch (ModelNotFoundException ex) {
            throw new ConversionException("No category with the id " + source.getParentId() + " found.", ex);
        }

    }


    @Resource(name = "merchantCategoryService")
    public void setMerchantCategoryService(MerchantCategoryService merchantCategoryService) {
        this.merchantCategoryService = merchantCategoryService;
    }


    @Resource(name = "merchantMediaService")
    public void setMerchantMediaService(MerchantMediaService merchantMediaService) {
        this.merchantMediaService = merchantMediaService;
    }

    public MerchantMediaService getMerchantMediaService() {
        return merchantMediaService;
    }

    public MerchantCategoryService getMerchantCategoryService() {
        return merchantCategoryService;
    }

}
