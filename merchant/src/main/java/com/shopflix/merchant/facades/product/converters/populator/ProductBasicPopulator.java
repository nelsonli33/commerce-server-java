package com.shopflix.merchant.facades.product.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.merchant.data.ProductData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component(value = "productBasicPopulator")
public class ProductBasicPopulator implements Populator<ProductModel, ProductData>
{
    @Override
    public void populate(ProductModel source, ProductData target) throws ConversionException
    {
        target.setId(source.getId());
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setQuantity(source.getQuantity());
        target.setPrice(BigDecimal.valueOf(source.getPrice()));
        target.setSku(source.getSku());
        target.setStatus(source.getStatus().toString().toLowerCase());
    }
}
