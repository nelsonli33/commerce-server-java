package com.shopflix.core.repository.media;

import com.shopflix.core.model.media.MediaImageModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MediaImageRepository extends JpaRepository<MediaImageModel, Long> {
}
