package com.shopflix.merchant.service.navigation;

import com.shopflix.core.model.navigation.CMSNavigationModel;

import java.util.List;

public interface MerchantNavigationService {
    List<CMSNavigationModel> getAllNavigationNodes();
    CMSNavigationModel createNavigationNode(String code, String name);
}
