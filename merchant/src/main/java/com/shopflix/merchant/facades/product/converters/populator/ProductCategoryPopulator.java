package com.shopflix.merchant.facades.product.converters.populator;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.category.CategoryModel;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.merchant.data.CategoryData;
import com.shopflix.merchant.data.ProductData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

public class ProductCategoryPopulator implements Populator<ProductModel, ProductData>
{
    private Converter<CategoryModel, CategoryData> categoryConverter;

    @Override
    public void populate(ProductModel source, ProductData target) throws ConversionException
    {
        target.setCategories(categoryConverter.convertAll(source.getCategories()));
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
