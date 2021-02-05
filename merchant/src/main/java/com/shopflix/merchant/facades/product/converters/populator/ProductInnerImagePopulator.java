package com.shopflix.merchant.facades.product.converters.populator;

import com.shopflix.core.constants.WebConstants;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.ProductImageData;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.product.ProductImageModel;
import org.springframework.stereotype.Component;

@Component(value = "productInnerImagePopulator")
public class ProductInnerImagePopulator implements Populator<ProductImageModel, ProductImageData>
{

    @Override
    public void populate(ProductImageModel source, ProductImageData target) throws ConversionException
    {
        target.setId(source.getId());
        target.setFilename(source.getFilename());
        target.setNormal(WebConstants.STORAGE_URL.concat(source.getNormal()));
        target.setTiny(WebConstants.STORAGE_URL.concat(source.getTiny()));
        target.setThumbnail(WebConstants.STORAGE_URL.concat(source.getThumbnail()));
        target.setDetail(WebConstants.STORAGE_URL.concat(source.getDetail()));
        target.setZoom(WebConstants.STORAGE_URL.concat(source.getZoom()));
        target.setAlt(source.getAlt());
        target.setPosition(source.getPosition());
    }
}
