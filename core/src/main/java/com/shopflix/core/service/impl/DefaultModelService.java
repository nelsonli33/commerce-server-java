package com.shopflix.core.service.impl;

import com.shopflix.core.model.ItemModel;
import com.shopflix.core.repository.ModelRepository;
import com.shopflix.core.service.ModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public void saveAll(List<ItemModel> models)
    {
        getModelRepository().saveAll(models);
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
