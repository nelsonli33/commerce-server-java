package com.shopflix.storefront.services;

import com.shopflix.core.service.ModelService;

import javax.annotation.Resource;

public abstract class AbstractBusinessService
{
    private ModelService modelService;


    public ModelService getModelService()
    {
        return modelService;
    }

    @Resource(name = "modelService")
    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }
}
