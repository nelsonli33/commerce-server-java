package com.shopflix.core.service.impl;

import com.shopflix.core.model.ItemModel;
import com.shopflix.core.repository.ModelRepository;
import com.shopflix.core.service.ModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service(value = "modelService")
public class DefaultModelService implements ModelService
{
    private ModelRepository<ItemModel> modelRepository;

    @Override
    public void save(ItemModel model)
    {
        getModelRepository().save(model);
    }

    @Override
    public void saveAll(Collection<? extends ItemModel> models)
    {
        getModelRepository().saveAll(models);
    }


    @Override
    public void remove(ItemModel model)
    {
        getModelRepository().delete(model);
    }

    @Override
    public void removeAll(Collection<? extends ItemModel> models)
    {
        getModelRepository().deleteAll(models);
    }


    @Override
    public void refresh(ItemModel model)
    {
        getModelRepository().refresh(model);
    }


    public ModelRepository<ItemModel> getModelRepository()
    {
        return modelRepository;
    }

    @Resource(name = "modelRepository")
    public void setModelRepository(ModelRepository<ItemModel> modelRepository)
    {
        this.modelRepository = modelRepository;
    }


}
