package com.shopflix.merchant.facades.product.converters.populator;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.product.ProductOptionModel;
import com.shopflix.core.model.product.ProductOptionValueModel;
import com.shopflix.merchant.data.ProductOptionData;
import com.shopflix.merchant.data.ProductOptionValueData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component(value = "productInnerOptionPopulator")
public class ProductInnerOptionPopulator implements Populator<ProductOptionModel, ProductOptionData>
{
    Converter<ProductOptionValueModel, ProductOptionValueData> productInnerOptionValueConverter;

    @Override
    public void populate(ProductOptionModel source, ProductOptionData target) throws ConversionException
    {
        target.setId(source.getId());
        target.setDisplayName(source.getDisplayName());
        target.setPosition(source.getPosition());
        target.setOptionValues(productInnerOptionValueConverter.convertAll(source.getValues()));
    }

    public Converter<ProductOptionValueModel, ProductOptionValueData> getProductInnerOptionValueConverter()
    {
        return productInnerOptionValueConverter;
    }

    @Resource(name = "productInnerOptionValueConverter")
    public void setProductInnerOptionValueConverter(Converter<ProductOptionValueModel, ProductOptionValueData> productInnerOptionValueConverter)
    {
        this.productInnerOptionValueConverter = productInnerOptionValueConverter;
    }
}


