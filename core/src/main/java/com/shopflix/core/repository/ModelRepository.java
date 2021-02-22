package com.shopflix.core.repository;

import com.shopflix.core.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

public interface ModelRepository<T extends ItemModel> extends JpaRepository<T, Long>
{
}
