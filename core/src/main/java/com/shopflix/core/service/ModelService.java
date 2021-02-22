package com.shopflix.core.service;

import com.shopflix.core.model.ItemModel;

import java.util.List;

public interface ModelService
{
    void save(ItemModel model);

    void saveAll(List<ItemModel> models);
}
