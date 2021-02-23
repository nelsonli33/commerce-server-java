package com.shopflix.merchant.facades.product.impl;

import com.google.common.collect.Sets;
import com.shopflix.core.converters.ConfigurableConverter;
import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.data.PageData;
import com.shopflix.core.data.ProductImageData;
import com.shopflix.core.data.form.*;
import com.shopflix.core.model.category.CategoryModel;
import com.shopflix.core.model.product.*;
import com.shopflix.merchant.data.ProductData;
import com.shopflix.merchant.data.ProductOption;
import com.shopflix.merchant.data.ProductSearchCriteria;
import com.shopflix.merchant.facades.product.MerchantProductFacade;
import com.shopflix.merchant.service.category.MerchantCategoryService;
import com.shopflix.merchant.service.product.MerchantProductImageService;
import com.shopflix.merchant.service.product.MerchantProductService;
import com.shopflix.merchant.service.product.ProductImage;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

public class DefaultMerchantProductFacade implements MerchantProductFacade
{
    private MerchantCategoryService merchantCategoryService;
    private MerchantProductService merchantProductService;
    private MerchantProductImageService merchantProductImageService;
    private Populator<ProductForm, ProductModel> productReversePopulator;
    private ConfigurableConverter<ProductModel, ProductData, ProductOption> productConverter;
    private Converter<ProductImageModel, ProductImageData> productInnerImageConverter;

    @Override
    public PageData<List<ProductData>> getProducts(ProductSearchCriteria searchCriteria, Collection<ProductOption> options)
    {
        Page<ProductModel> products = merchantProductService.getProducts(searchCriteria);
        List<ProductData> productDataList = productConverter.convertAll(products.getContent(), options);

        PageData<List<ProductData>> pageData = new PageData<>();
        pageData.setData(productDataList);
        pageData.setPage(1);
        pageData.setSize(50);
        pageData.setTotal(products.getNumberOfElements());
        pageData.setTotalPages(products.getTotalPages());

        return pageData;
    }

    @Override
    public ProductData getProductForIdAndOptions(Long id, Collection<ProductOption> options)
    {
        ProductModel productModel = merchantProductService.getProduct(id);
        return productConverter.convert(productModel, options);
    }

    @Override
    public ProductData createProduct(ProductForm form)
    {
        ProductModel productModel = new ProductModel();
        productReversePopulator.populate(form, productModel);
        setProductOptionsForProduct(form.getOptions(), productModel);
        setProductVariantsForProduct(form.getVariants(), productModel);
        setProductCategories(form.getCategoryIds(), productModel);
        setProductImageForProduct(form.getImages(), productModel);
        return productConverter.convert(merchantProductService.save(productModel), Collections.singletonList(ProductOption.FULL));
    }


    @Override
    public ProductData editProduct(Long id, ProductForm form)
    {
        ProductModel productModel = merchantProductService.getProduct(id);
        productReversePopulator.populate(form, productModel);
        setProductOptionsForProduct(form.getOptions(), productModel);
        setProductVariantsForProduct(form.getVariants(), productModel);
        setProductCategories(form.getCategoryIds(), productModel);
        setProductImageForProduct(form.getImages(), productModel);
        return productConverter.convert(merchantProductService.save(productModel), Collections.singletonList(ProductOption.FULL));
    }

    @Override
    public void deleteProduct(Long id)
    {
        ProductModel productModel = merchantProductService.getProduct(id);
        merchantProductService.deleteProduct(productModel);
    }

    @Override
    public ProductImageData uploadProductImage(MultipartFile uploadFile)
    {
        try
        {
            List<ProductImage> productImages = merchantProductImageService.uploadProductImage(uploadFile);

            ProductImageModel productImageModel = new ProductImageModel();
            productImageModel.setFilename(productImages.get(0).getFilename().split("_")[0]);
            productImageModel.setZoom(productImages.get(0).getFilename());
            productImageModel.setDetail(productImages.get(1).getFilename());
            productImageModel.setNormal(productImages.get(2).getFilename());
            productImageModel.setThumbnail(productImages.get(3).getFilename());
            productImageModel.setTiny(productImages.get(4).getFilename());
            productImageModel.setOriginfilename(uploadFile.getOriginalFilename());
            productImageModel.setAlt(uploadFile.getOriginalFilename());

            return productInnerImageConverter.convert(merchantProductImageService.save(productImageModel));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }


    protected void setProductCategories(List<Long> categoryIds, ProductModel productModel)
    {
        List<CategoryModel> categoryModelList = merchantCategoryService.getAllCategoriesByIds(categoryIds);
        Set<CategoryModel> categoryModelSet = Sets.newHashSet(categoryModelList);
        productModel.getCategories().addAll(categoryModelSet);
    }


    protected void setProductOptionsForProduct(List<ProductOptionForm> options, ProductModel productModel)
    {
        List<ProductOptionModel> productOptionModels = new ArrayList<>();
        for (int i = 0; i < options.size(); i++)
        {
            ProductOptionForm optionForm = options.get(i);

            ProductOptionModel productOptionModel;

            if (optionForm.getId() != null)
            {
                productOptionModel = merchantProductService.getProductOption(optionForm.getId());
            }
            else
            {
                productOptionModel = new ProductOptionModel();
            }

            productOptionModel.setDisplayName(optionForm.getDisplayName());
            productOptionModel.setPosition(optionForm.getPosition());
            productOptionModel.setProduct(productModel);

            setOptionValueForOption(optionForm.getOptionValues(), productOptionModel);

            productOptionModels.add(productOptionModel);
        }
        productModel.getOptions().clear();
        productModel.getOptions().addAll(productOptionModels);
    }

    protected void setOptionValueForOption(List<ProductOptionValueForm> optionValues, ProductOptionModel productOptionModel)
    {
        List<ProductOptionValueModel> productOptionValueModels = new ArrayList<>();

        for (int i = 0; i < optionValues.size(); i++)
        {
            ProductOptionValueForm optionValueForm = optionValues.get(i);

            ProductOptionValueModel productOptionValueModel;
            if (optionValueForm.getId() != null)
            {
                productOptionValueModel = merchantProductService.getProductOptionValue(optionValueForm.getId());
            }
            else
            {
                productOptionValueModel = new ProductOptionValueModel();
            }
            productOptionValueModel.setLabel(optionValueForm.getLabel());
            productOptionValueModel.setPosition(optionValueForm.getPosition());
            productOptionValueModel.setOption(productOptionModel);

            setProductImageForOptionValue(optionValueForm.getImages(), productOptionValueModel);

            productOptionValueModels.add(productOptionValueModel);
        }
        productOptionModel.getValues().clear();
        productOptionModel.getValues().addAll(productOptionValueModels);
    }

    protected void setProductVariantsForProduct(List<ProductVariantForm> variantForms, ProductModel productModel)
    {


        Map<String, ProductOptionValueModel> optionValueMap = new HashMap<>();
        for (int i = 0; i < productModel.getOptions().size(); i++)
        {
            ProductOptionModel productOptionModel = productModel.getOptions().get(i);
            productOptionModel.getValues().forEach(productOptionValueModel -> {
                optionValueMap.put(productOptionValueModel.getLabel(), productOptionValueModel);
            });
        }

        List<ProductVariantModel> productVariantModels = new ArrayList<>();
        for (int i = 0; i < variantForms.size(); i++)
        {
            ProductVariantForm variantForm = variantForms.get(i);

            ProductVariantModel productVariantModel;
            if (variantForm.getId() != null)
            {
                productVariantModel = merchantProductService.getProductVariant(variantForm.getId());
            }
            else
            {
                productVariantModel = new ProductVariantModel();
            }
            productVariantModel.setName(variantForm.getName());
            productVariantModel.setPrice(variantForm.getPrice().doubleValue());
            productVariantModel.setQuantity(variantForm.getQuantity());
            productVariantModel.setSku(variantForm.getSku());
            productVariantModel.setOptionValue1(optionValueMap.get(variantForm.getValue1()));
            productVariantModel.setOptionValue2(optionValueMap.get(variantForm.getValue2()));
            productVariantModel.setProduct(productModel);

            productVariantModels.add(productVariantModel);
        }
        productModel.getVariants().clear();
        productModel.getVariants().addAll(productVariantModels);
    }

    private void setProductImageForProduct(List<ProductImageForm> imageForms, ProductModel productModel)
    {
        for (ProductImageForm imageForm : imageForms)
        {
            ProductImageModel imageModel = merchantProductImageService.getProductImageForId(imageForm.getId());
            imageModel.setPosition(imageForm.getPosition());
            imageModel.setProduct(productModel);
            productModel.getImages().add(imageModel);
        }
    }

    private void setProductImageForOptionValue(List<ProductImageForm> imageForms, ProductOptionValueModel optionValueModel)
    {
        for (ProductImageForm imageForm : imageForms)
        {
            ProductImageModel imageModel = merchantProductImageService.getProductImageForId(imageForm.getId());
            imageModel.setPosition(imageForm.getPosition());
            imageModel.setOptionValue(optionValueModel);
            optionValueModel.getImages().add(imageModel);
        }
    }

    public MerchantCategoryService getMerchantCategoryService()
    {
        return merchantCategoryService;
    }

    public void setMerchantCategoryService(MerchantCategoryService merchantCategoryService)
    {
        this.merchantCategoryService = merchantCategoryService;
    }

    public MerchantProductService getMerchantProductService()
    {
        return merchantProductService;
    }

    public void setMerchantProductService(MerchantProductService merchantProductService)
    {
        this.merchantProductService = merchantProductService;
    }

    public MerchantProductImageService getMerchantProductImageService()
    {
        return merchantProductImageService;
    }

    public void setMerchantProductImageService(MerchantProductImageService merchantProductImageService)
    {
        this.merchantProductImageService = merchantProductImageService;
    }

    public Populator<ProductForm, ProductModel> getProductReversePopulator()
    {
        return productReversePopulator;
    }

    public void setProductReversePopulator(Populator<ProductForm, ProductModel> productReversePopulator)
    {
        this.productReversePopulator = productReversePopulator;
    }

    public ConfigurableConverter<ProductModel, ProductData, ProductOption> getProductConverter()
    {
        return productConverter;
    }

    public void setProductConverter(ConfigurableConverter<ProductModel, ProductData, ProductOption> productConverter)
    {
        this.productConverter = productConverter;
    }

    public Converter<ProductImageModel, ProductImageData> getProductInnerImageConverter()
    {
        return productInnerImageConverter;
    }

    public void setProductInnerImageConverter(Converter<ProductImageModel, ProductImageData> productInnerImageConverter)
    {
        this.productInnerImageConverter = productInnerImageConverter;
    }
}
