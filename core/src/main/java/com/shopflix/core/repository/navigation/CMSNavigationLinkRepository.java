package com.shopflix.core.repository.navigation;

import com.shopflix.core.model.navigation.CMSNavigationLinkModel;
import com.shopflix.core.model.navigation.CMSNavigationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CMSNavigationLinkRepository extends JpaRepository<CMSNavigationLinkModel, Long> {
    CMSNavigationLinkModel findByIdAndNavigation(Long id, CMSNavigationModel navigation);
}
