package com.shopflix.core.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.CategoryData;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.CategoryModel;
import com.shopflix.core.model.MediaImageModel;
import com.shopflix.core.service.category.MerchantCategoryService;
import com.shopflix.core.service.media.MerchantMediaService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Component("categoryReversePopulator")
public class DefaultCategoryReversePopulator implements Populator<CategoryData, CategoryModel> {

    private MerchantCategoryService merchantCategoryService;
    private MerchantMediaService merchantMediaService;

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
            if (categoryData.getImageId() == null) {
                categoryModel.setImage(null);
            } else {
                MediaImageModel mediaImageModel =
                        getMerchantMediaService().getMediaImageForId(categoryData.getImageId());
                categoryModel.setImage(mediaImageModel);
            }
        } catch (ModelNotFoundException ex) {
            throw new ConversionException("No media image with the id " + categoryData.getImageId() + " found.", ex);
        }

        try {
            if (categoryData.getParentId() == null) {
                categoryModel.setParent(null);
            } else {
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
