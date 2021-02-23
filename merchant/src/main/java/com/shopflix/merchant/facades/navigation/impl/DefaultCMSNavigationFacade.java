package com.shopflix.merchant.facades.navigation.impl;

import com.shopflix.core.converters.Converter;
import com.shopflix.core.converters.Populator;
import com.shopflix.core.model.navigation.CMSNavigationLinkModel;
import com.shopflix.core.model.navigation.CMSNavigationModel;
import com.shopflix.merchant.data.NavigationData;
import com.shopflix.merchant.data.NavigationLinkData;
import com.shopflix.merchant.facades.navigation.CMSNavigationFacade;
import com.shopflix.merchant.forms.NavigationLinkForm;
import com.shopflix.merchant.service.navigation.MerchantNavigationService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

public class DefaultCMSNavigationFacade implements CMSNavigationFacade
{
    private MerchantNavigationService merchantNavigationService;
    private Populator<NavigationLinkData, CMSNavigationLinkModel> navigationLinkReversePopulator;
    private Converter<CMSNavigationModel, NavigationData> navigationConverter;
    private Converter<CMSNavigationLinkModel, NavigationLinkData> navigationLinkConverter;

    @Override
    public List<NavigationData> getAllNavigationNodes()
    {
        List<CMSNavigationModel> allNavigationModels = merchantNavigationService.getAllNavigationNodes();
        return navigationConverter.convertAll(allNavigationModels);
    }

    @Override
    public NavigationData createNavigationNode(String code, String name)
    {
        CMSNavigationModel navigationModel = merchantNavigationService.createNavigationNode(code, name);
        return navigationConverter.convert(navigationModel);
    }

    @Override
    public NavigationData getNavigationForId(Long navigationId) {
        CMSNavigationModel model = merchantNavigationService.getNavigationNodeForId(navigationId);
        NavigationData data = navigationConverter.convert(model);
        data.setLinks(navigationLinkConverter.convertAll(model.getLinks()));
        return data;
    }

    @Override
    public NavigationLinkData getNavigationLink(Long linkId)
    {
        validateParameterNotNullStandardMessage("linkId", linkId);

        CMSNavigationLinkModel model = merchantNavigationService.getNavigationLinkForId(linkId);
        return navigationLinkConverter.convert(model);
    }

    @Override
    public void addNavigationLink(Long navigationId, NavigationLinkData data)
    {
        validateParameterNotNullStandardMessage("navigationId", navigationId);
        validateParameterNotNullStandardMessage("navigationLinkForm", data);

        CMSNavigationLinkModel linkModel = new CMSNavigationLinkModel();
        navigationLinkReversePopulator.populate(data, linkModel);
        merchantNavigationService.addParentForLink(linkModel, data.getParentId());

        CMSNavigationModel cmsNavigationModel = merchantNavigationService.getNavigationNodeForId(navigationId);
        merchantNavigationService.saveNavigationLinkEntry(cmsNavigationModel, linkModel);
    }

    @Override
    public void editNavigationLink(Long linkId, NavigationLinkData data)
    {
        validateParameterNotNullStandardMessage("linkId", linkId);
        validateParameterNotNullStandardMessage("navigationLinkData", data);

        CMSNavigationLinkModel linkModel = merchantNavigationService.getNavigationLinkForId(linkId);
        navigationLinkReversePopulator.populate(data, linkModel);
        merchantNavigationService.addParentForLink(linkModel, data.getParentId());
        merchantNavigationService.saveNavigationLinkEntry(linkModel);
    }

    @Override
    public void deleteNavigationLink(Long linkId)
    {
        validateParameterNotNullStandardMessage("link id", linkId);

        CMSNavigationLinkModel linkModel = merchantNavigationService.getNavigationLinkForId(linkId);
        merchantNavigationService.deleteNavigationLinkEntry(linkModel);
    }


    public MerchantNavigationService getMerchantNavigationService()
    {
        return merchantNavigationService;
    }

    public void setMerchantNavigationService(MerchantNavigationService merchantNavigationService)
    {
        this.merchantNavigationService = merchantNavigationService;
    }

    public Populator<NavigationLinkData, CMSNavigationLinkModel> getNavigationLinkReversePopulator()
    {
        return navigationLinkReversePopulator;
    }

    public void setNavigationLinkReversePopulator(Populator<NavigationLinkData, CMSNavigationLinkModel> navigationLinkReversePopulator)
    {
        this.navigationLinkReversePopulator = navigationLinkReversePopulator;
    }

    public Converter<CMSNavigationModel, NavigationData> getNavigationConverter()
    {
        return navigationConverter;
    }

    public void setNavigationConverter(Converter<CMSNavigationModel, NavigationData> navigationConverter)
    {
        this.navigationConverter = navigationConverter;
    }

    public Converter<CMSNavigationLinkModel, NavigationLinkData> getNavigationLinkConverter()
    {
        return navigationLinkConverter;
    }

    public void setNavigationLinkConverter(Converter<CMSNavigationLinkModel, NavigationLinkData> navigationLinkConverter)
    {
        this.navigationLinkConverter = navigationLinkConverter;
    }
}
