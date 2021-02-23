package com.shopflix.merchant.facades.product.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.merchant.data.ProductData;
import org.springframework.stereotype.Component;

public class ProductSoldPopulator implements Populator<ProductModel, ProductData>
{
    @Override
    public void populate(ProductModel source, ProductData target) throws ConversionException
    {
        target.setSold(source.getSold());
    }
}
