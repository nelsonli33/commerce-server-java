package com.shopflix.merchant.facades.product.converters.populator;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.model.product.ProductOptionModel;
import com.shopflix.merchant.data.ProductData;
import com.shopflix.merchant.data.ProductOptionData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component(value = "productOptionPopulator")
public class ProductOptionPopulator implements Populator<ProductModel, ProductData>
{
    Converter<ProductOptionModel, ProductOptionData> productInnerOptionConverter;

    @Override
    public void populate(ProductModel source, ProductData target) throws ConversionException
    {
        target.setOptions(productInnerOptionConverter.convertAll(source.getOptions()));
    }


    public Converter<ProductOptionModel, ProductOptionData> getProductInnerOptionConverter()
    {
        return productInnerOptionConverter;
    }

    @Resource(name = "productInnerOptionConverter")
    public void setProductInnerOptionConverter(Converter<ProductOptionModel, ProductOptionData> productInnerOptionConverter)
    {
        this.productInnerOptionConverter = productInnerOptionConverter;
    }
}
