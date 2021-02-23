package com.shopflix.core.service;

import com.shopflix.core.model.ItemModel;

import java.util.Collection;

public interface ModelService
{
    void save(ItemModel model);

    void saveAll(Collection<? extends ItemModel> models);

    void remove(ItemModel model);

    void removeAll(Collection<? extends ItemModel> models);

    void refresh(ItemModel model);
}
