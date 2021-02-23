package com.shopflix.merchant.facades.product.converters.populator;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.model.product.ProductOptionValueModel;
import com.shopflix.core.model.product.ProductVariantModel;
import com.shopflix.merchant.data.ProductData;
import com.shopflix.merchant.data.ProductOptionValueData;
import com.shopflix.merchant.data.ProductVariantData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductVariantPopulator implements Populator<ProductModel, ProductData>
{
    Converter<ProductOptionValueModel, ProductOptionValueData> productInnerOptionValueConverter;

    @Override
    public void populate(ProductModel source, ProductData target) throws ConversionException
    {
        List<ProductVariantData> variantDataList = new ArrayList<>();
        List<ProductVariantModel> variants = source.getVariants();
        for(ProductVariantModel variantModel : variants) {
            ProductVariantData variantData = new ProductVariantData();
            variantData.setId(variantModel.getId());
            variantData.setName(variantModel.getName());
            variantData.setSku(variantModel.getSku());
            variantData.setQuantity(variantModel.getQuantity());
            variantData.setPrice(BigDecimal.valueOf(variantModel.getPrice()));
            populateVariantValues(variantModel, variantData);
            variantDataList.add(variantData);
        }

        target.setVariants(variantDataList);
    }

    protected void populateVariantValues(ProductVariantModel variantModel, ProductVariantData variantData) {
        ProductOptionValueModel optionValue1 = variantModel.getOptionValue1();
        ProductOptionValueModel optionValue2 = variantModel.getOptionValue2();

        variantData.setValue1(optionValue1.getLabel());
        variantData.setValue2(optionValue2 != null ? optionValue2.getLabel() : null);
        variantData.setValueIds(Arrays.asList(optionValue1.getId(), optionValue2 != null ? optionValue2.getId() : null));
    }

    public Converter<ProductOptionValueModel, ProductOptionValueData> getProductInnerOptionValueConverter()
    {
        return productInnerOptionValueConverter;
    }

    public void setProductInnerOptionValueConverter(Converter<ProductOptionValueModel, ProductOptionValueData> productInnerOptionValueConverter)
    {
        this.productInnerOptionValueConverter = productInnerOptionValueConverter;
    }
}
