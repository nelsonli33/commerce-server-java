package com.shopflix.core.repository;

import com.shopflix.core.model.navigation.CMSNavigationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CMSNavigationRepository extends JpaRepository<CMSNavigationModel, Long> {
}
