package com.shopflix.merchant.facades.product.converters.populator;

import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.form.ProductForm;
import com.shopflix.core.data.form.ProductOptionForm;
import com.shopflix.core.data.form.ProductOptionValueForm;
import com.shopflix.core.data.form.ProductVariantForm;
import com.shopflix.core.exception.ConversionException;
import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.category.CategoryModel;
import com.shopflix.core.model.product.ProductModel;
import com.shopflix.core.model.product.ProductOptionModel;
import com.shopflix.core.model.product.ProductOptionValueModel;
import com.shopflix.core.model.product.ProductVariantModel;
import com.shopflix.core.repository.ProductOptionRepository;
import com.shopflix.core.repository.ProductOptionValueRepository;
import com.shopflix.core.repository.ProductVariantRepository;
import com.shopflix.merchant.service.category.MerchantCategoryService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component("productReversePopulator")
public class DefaultProductReversePopulator implements Populator<ProductForm, ProductModel> {

    private MerchantCategoryService merchantCategoryService;
    private ProductOptionRepository productOptionRepository;
    private ProductOptionValueRepository productOptionValueRepository;
    private ProductVariantRepository productVariantRepository;

    @Override
    public void populate(ProductForm source, ProductModel target) throws ConversionException {
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
        target.setStatus(source.getStatus());
        target.setMetaTitle(source.getMetaTitle());
        target.setMetaDescription(source.getMetaDescription());
        target.setMaxOrderQuantity(source.getMaxOrderQuantity());
        target.setMinOrderQuantity(source.getMinOrderQuantity());

        populateCategories(source, target);
        populateProductOptionAndValues(source, target);
        populateProductVariants(source, target);
    }


    protected void populateCategories(ProductForm source, ProductModel target) {
        List<CategoryModel> categoryModels = merchantCategoryService.getAllCategoriesByIds(source.getCategoryIds());
        Set<CategoryModel> categoryModelSet = new HashSet<>(categoryModels);
        target.setCategories(categoryModelSet);
    }


    protected void populateProductOptionAndValues(ProductForm source, ProductModel target) {
        List<ProductOptionModel> productOptionModels = new ArrayList<>();

        List<ProductOptionForm> options = source.getOptions();

        for (int i = 0; i < options.size(); i++) {
            ProductOptionForm optionForm = options.get(i);

            ProductOptionModel productOptionModel = new ProductOptionModel();
            if (optionForm.getId() != null) {
                productOptionModel = productOptionRepository.findById(optionForm.getId()).orElseThrow(() ->
                        new ModelNotFoundException("product option with id "+optionForm.getId()+" not found."));
            }
            productOptionModel.setDisplayName(optionForm.getDisplayName());
            productOptionModel.setPosition(optionForm.getPosition());
            productOptionModel.setProduct(target);


            productOptionModel.getValues().clear();

            List<ProductOptionValueForm> optionValues = optionForm.getOptionValues();
            for (int j = 0; j < optionValues.size(); j++) {
                ProductOptionValueForm optionValueForm = optionValues.get(j);
                ProductOptionValueModel productOptionValueModel = new ProductOptionValueModel();

                if (optionValueForm.getId() != null) {
                    productOptionValueModel = productOptionValueRepository.findById(optionValueForm.getId()).orElseThrow(() ->
                            new ModelNotFoundException("product option value with id "+optionValueForm.getId()+" not found."));
                }

                productOptionValueModel.setLabel(optionValueForm.getLabel());
                productOptionValueModel.setPosition(optionValueForm.getPosition());
                productOptionValueModel.setOption(productOptionModel);
                productOptionModel.getValues().add(productOptionValueModel);
            }
            productOptionModels.add(productOptionModel);
        }

        target.getOptions().clear();
        target.getOptions().addAll(productOptionModels);
    }

    protected void populateProductVariants(ProductForm source, ProductModel target) {
        Map<String, ProductOptionValueModel> optionValueMap = new HashMap<>();

        for (int i = 0; i < target.getOptions().size(); i++) {
            ProductOptionModel productOptionModel = target.getOptions().get(i);
            productOptionModel.getValues().forEach(productOptionValueModel -> {
                optionValueMap.put(productOptionValueModel.getLabel(), productOptionValueModel);
            });
        }


        List<ProductVariantModel> productVariantModels = new ArrayList<>();
        for (int i = 0; i < source.getVariants().size(); i++) {
            ProductVariantForm variantForm = source.getVariants().get(i);

            ProductVariantModel productVariantModel = new ProductVariantModel();
            if (variantForm.getId() != null) {
                productVariantModel = productVariantRepository.findById(variantForm.getId()).orElseThrow(() ->
                        new ModelNotFoundException("product variant with id "+variantForm.getId()+" not found."));
            }
            productVariantModel.setName(variantForm.getName());
            productVariantModel.setPrice(variantForm.getPrice());
            productVariantModel.setQuantity(variantForm.getQuantity());
            productVariantModel.setSku(variantForm.getSku());
            productVariantModel.setProduct(target);
            productVariantModel.setOptionValue1(optionValueMap.get(variantForm.getOption1()));
            productVariantModel.setOptionValue2(optionValueMap.get(variantForm.getOption2()));
            productVariantModels.add(productVariantModel);
        }
        target.getVariants().clear();
        target.getVariants().addAll(productVariantModels);
    }


    @Resource(name = "merchantCategoryService")
    public void setMerchantCategoryService(MerchantCategoryService merchantCategoryService) {
        this.merchantCategoryService = merchantCategoryService;
    }

    @Resource(name = "productOptionRepository")
    public void setProductOptionRepository(ProductOptionRepository productOptionRepository) {
        this.productOptionRepository = productOptionRepository;
    }

    @Resource(name = "productOptionValueRepository")
    public void setProductOptionValueRepository(ProductOptionValueRepository productOptionValueRepository) {
        this.productOptionValueRepository = productOptionValueRepository;
    }

    @Resource(name = "productVariantRepository")
    public void setProductVariantRepository(ProductVariantRepository productVariantRepository) {
        this.productVariantRepository = productVariantRepository;
    }


    public MerchantCategoryService getMerchantCategoryService() {
        return merchantCategoryService;
    }


    public ProductOptionRepository getProductOptionRepository() {
        return productOptionRepository;
    }

    public ProductOptionValueRepository getProductOptionValueRepository() {
        return productOptionValueRepository;
    }


    public ProductVariantRepository getProductVariantRepository() {
        return productVariantRepository;
    }

}
