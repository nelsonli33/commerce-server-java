package com.shopflix.merchant.facades.navigation;

import com.shopflix.merchant.data.NavigationData;
import com.shopflix.merchant.data.NavigationLinkData;
import com.shopflix.merchant.forms.NavigationLinkForm;

import java.util.List;

public interface CMSNavigationFacade {

    List<NavigationData> getAllNavigationNodes();

    NavigationData getNavigationForId(Long navigationId);

    NavigationData createNavigationNode(String code, String name);

    NavigationLinkData getNavigationLink(Long linkId);

    void addNavigationLink(Long navigationId, NavigationLinkData data);

    void editNavigationLink(Long linkId,  NavigationLinkData data);

    void deleteNavigationLink(Long linkId);
}
