package com.shopflix.core.repository;

import com.shopflix.core.model.CategoryModel;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryModel, Long> {
}
