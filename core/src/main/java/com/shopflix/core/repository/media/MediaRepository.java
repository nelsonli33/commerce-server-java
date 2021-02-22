package com.shopflix.core.repository.media;

import com.shopflix.core.model.media.MediaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaModel, Long> {
}
