package com.shopflix.merchant.facades.category.converters.populator;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.MediaImageData;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.category.CategoryModel;
import com.shopflix.core.model.media.MediaImageModel;
import com.shopflix.merchant.data.CategoryData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component(value = "categoryPopulator")
public class CategoryPopulator implements Populator<CategoryModel, CategoryData>
{
    private Converter<MediaImageModel, MediaImageData> mediaImageConverter;

    @Autowired
    public CategoryPopulator(Converter<MediaImageModel, MediaImageData> mediaImageConverter)
    {
        this.mediaImageConverter = mediaImageConverter;
    }

    @Override
    public void populate(CategoryModel source, CategoryData target) throws ConversionException
    {
        Assert.notNull(source, "Parameter categoryModel cannot be null.");
        Assert.notNull(target, "Parameter CategoryData cannot be null.");

        target.setId(source.getId());
        target.setCode(StringUtils.defaultString(source.getCode()));
        target.setName(StringUtils.defaultString(source.getName()));
        target.setDescription(StringUtils.defaultString(source.getDescription()));
        target.setMetaTitle(StringUtils.defaultString(source.getMetaTitle()));
        target.setMetaDescription(StringUtils.defaultString(source.getMetaDescription()));
        target.setSortOrder(source.getSortOrder());
        target.setParentId(source.getParentId());

        if (source.getImage() != null) {
            MediaImageModel imageModel = source.getImage();
            target.setImage(mediaImageConverter.convert(imageModel));
        }

    }


}
