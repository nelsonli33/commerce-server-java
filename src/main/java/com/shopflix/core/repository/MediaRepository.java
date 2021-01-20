package com.shopflix.core.repository;

import com.shopflix.core.model.MediaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaModel, Long> {
}
