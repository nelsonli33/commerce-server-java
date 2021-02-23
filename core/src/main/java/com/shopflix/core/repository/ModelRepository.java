package com.shopflix.core.repository;

import com.shopflix.core.model.ItemModel;

public interface ModelRepository<T extends ItemModel> extends CustomJpaRepository<T, Long>
{

}
