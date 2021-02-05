package com.shopflix.merchant.facades.product.converters.populator;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.ProductImageData;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.product.ProductImageModel;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.merchant.data.ProductData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component(value = "productImagePopulator")
public class ProductImagePopulator implements Populator<ProductModel, ProductData>
{
    private Converter<ProductImageModel, ProductImageData> productInnerImageConverter;

    @Override
    public void populate(ProductModel source, ProductData target) throws ConversionException
    {
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
