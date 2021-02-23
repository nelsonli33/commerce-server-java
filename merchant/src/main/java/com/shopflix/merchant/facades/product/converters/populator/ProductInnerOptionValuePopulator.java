package com.shopflix.merchant.facades.product.converters.populator;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.ProductImageData;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.product.ProductImageModel;
import com.shopflix.core.model.product.ProductOptionValueModel;
import com.shopflix.merchant.data.ProductOptionValueData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

public class ProductInnerOptionValuePopulator implements Populator<ProductOptionValueModel, ProductOptionValueData>
{
    private Converter<ProductImageModel, ProductImageData> productInnerImageConverter;

    @Override
    public void populate(ProductOptionValueModel source, ProductOptionValueData target) throws ConversionException
    {
        target.setId(source.getId());
        target.setLabel(source.getLabel());
        target.setPosition(source.getPosition());
        target.setImages(productInnerImageConverter.convertAll(source.getImages()));
    }

    public Converter<ProductImageModel, ProductImageData> getProductInnerImageConverter()
    {
        return productInnerImageConverter;
    }

    @Resource(name = "productInnerImageConverter")
    public void setProductInnerImageConverter(Converter<ProductImageModel, ProductImageData> productInnerImageConverter)
    {
        this.productInnerImageConverter = productInnerImageConverter;
    }
}