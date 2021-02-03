package com.shopflix.merchant.service.navigation;

import com.shopflix.core.model.navigation.CMSNavigationLinkModel;
import com.shopflix.core.model.navigation.CMSNavigationModel;

import java.util.List;

public interface MerchantNavigationService {

    List<CMSNavigationModel> getAllNavigationNodes();

    CMSNavigationModel getNavigationNodeForId(Long id);

    CMSNavigationModel createNavigationNode(String code, String name);

    CMSNavigationLinkModel getNavigationLinkForId(Long id);

    void addParentForLink(CMSNavigationLinkModel linkModel, Long parentLinkId);

    void saveNavigationLinkEntry(CMSNavigationModel navigationModel, CMSNavigationLinkModel linkModel);

    CMSNavigationLinkModel saveNavigationLinkEntry(CMSNavigationLinkModel linkModel);

    void deleteNavigationLinkEntry(CMSNavigationLinkModel linkModel);
}
