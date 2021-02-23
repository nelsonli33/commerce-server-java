package com.shopflix.merchant.facades.product.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.form.ProductForm;
import com.shopflix.core.enums.ProductStatus;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.model.product.ProductModel;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

public class ProductReversePopulator implements Populator<ProductForm, ProductModel> {

    @Override
    public void populate(ProductForm source, ProductModel target) throws ConversionException {

        Assert.notNull(source, "Parameter productForm cannot be null.");
        Assert.notNull(target, "Parameter productModel cannot be null.");

        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setSummary(source.getSummary());
        target.setDescription(source.getDescription());
        target.setQuantity(source.getQuantity());
        target.setPrice(source.getPrice());
        target.setWeight(source.getWeight());
        target.setWeightUnit(source.getWeightUnit());
        target.setSku(source.getSku());
        target.setPackageLength(source.getPackageLength());
        target.setPackageWidth(source.getPackageWidth());
        target.setPackageHeight(source.getPackageHeight());
        target.setDaysToShip(source.getDaysToShip());
        target.setStatus(ProductStatus.from(source.getStatus()));
        target.setMetaTitle(source.getMetaTitle());
        target.setMetaDescription(source.getMetaDescription());
        target.setMaxOrderQuantity(source.getMaxOrderQuantity());
        target.setMinOrderQuantity(source.getMinOrderQuantity());

    }


//
//    protected void populateProductOptionAndValues(ProductForm source, ProductModel target) {
//        List<ProductOptionModel> productOptionModels = new ArrayList<>();
//
//        List<ProductOptionForm> options = source.getOptions();
//
//        for (int i = 0; i < options.size(); i++) {
//            ProductOptionForm optionForm = options.get(i);
//
//            ProductOptionModel productOptionModel = new ProductOptionModel();
//            if (optionForm.getId() != null) {
//                productOptionModel = productOptionRepository.findById(optionForm.getId()).orElseThrow(() ->
//                        new ModelNotFoundException("product option with id "+optionForm.getId()+" not found."));
//            }
//            productOptionModel.setDisplayName(optionForm.getDisplayName());
//            productOptionModel.setPosition(optionForm.getPosition());
//            productOptionModel.setProduct(target);
//
//
//            productOptionModel.getValues().clear();
//
//            List<ProductOptionValueForm> optionValues = optionForm.getOptionValues();
//            for (int j = 0; j < optionValues.size(); j++) {
//                ProductOptionValueForm optionValueForm = optionValues.get(j);
//                ProductOptionValueModel productOptionValueModel = new ProductOptionValueModel();
//
//                if (optionValueForm.getId() != null) {
//                    productOptionValueModel = productOptionValueRepository.findById(optionValueForm.getId()).orElseThrow(() ->
//                            new ModelNotFoundException("product option value with id "+optionValueForm.getId()+" not found."));
//                }
//
//                productOptionValueModel.setLabel(optionValueForm.getLabel());
//                productOptionValueModel.setPosition(optionValueForm.getPosition());
//                productOptionValueModel.setOption(productOptionModel);
//                productOptionModel.getValues().add(productOptionValueModel);
//            }
//            productOptionModels.add(productOptionModel);
//        }
//
//        target.getOptions().clear();
//        target.getOptions().addAll(productOptionModels);
//    }
//
//    protected void populateProductVariants(ProductForm source, ProductModel target) {
//        Map<String, ProductOptionValueModel> optionValueMap = new HashMap<>();
//
//        for (int i = 0; i < target.getOptions().size(); i++) {
//            ProductOptionModel productOptionModel = target.getOptions().get(i);
//            productOptionModel.getValues().forEach(productOptionValueModel -> {
//                optionValueMap.put(productOptionValueModel.getLabel(), productOptionValueModel);
//            });
//        }
//
//
//        List<ProductVariantModel> productVariantModels = new ArrayList<>();
//        for (int i = 0; i < source.getVariants().size(); i++) {
//            ProductVariantForm variantForm = source.getVariants().get(i);
//
//            ProductVariantModel productVariantModel = new ProductVariantModel();
//            if (variantForm.getId() != null) {
//                productVariantModel = productVariantRepository.findById(variantForm.getId()).orElseThrow(() ->
//                        new ModelNotFoundException("product variant with id "+variantForm.getId()+" not found."));
//            }
//
//            productVariantModel.setProduct(target);
//            productVariantModel.setOptionValue1(optionValueMap.get(variantForm.getOption1()));
//            productVariantModel.setOptionValue2(optionValueMap.get(variantForm.getOption2()));
//            productVariantModels.add(productVariantModel);
//        }
//        target.setVariants(productVariantModels);
//    }




}
