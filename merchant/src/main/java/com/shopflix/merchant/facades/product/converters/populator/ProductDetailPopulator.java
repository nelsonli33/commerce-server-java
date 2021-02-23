package com.shopflix.merchant.facades.product.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.merchant.data.ProductData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

public class ProductDetailPopulator implements Populator<ProductModel, ProductData>
{
    @Override
    public void populate(ProductModel source, ProductData target) throws ConversionException
    {
        target.setSummary(StringUtils.defaultString(source.getSummary()));
        target.setDescription(StringUtils.defaultString(source.getDescription()));
        target.setWeight(source.getWeight());
        target.setWeightUnit(source.getWeightUnit());
        target.setSku(source.getSku());
        target.setPackageLength(source.getPackageLength());
        target.setPackageHeight(source.getPackageHeight());
        target.setPackageWidth(source.getPackageWidth());
        target.setDaysToShip(source.getDaysToShip());
        target.setMinOrderQuantity(source.getMinOrderQuantity());
        target.setMaxOrderQuantity(source.getMaxOrderQuantity());
        target.setMetaTitle(source.getMetaDescription());
        target.setMetaDescription(source.getMetaDescription());
    }
}
