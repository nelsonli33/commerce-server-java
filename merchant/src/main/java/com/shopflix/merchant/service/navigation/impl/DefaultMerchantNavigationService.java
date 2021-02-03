package com.shopflix.merchant.service.navigation.impl;

import com.shopflix.core.exception.ModelNotFoundException;
import com.shopflix.core.model.category.CategoryModel;
import com.shopflix.core.model.navigation.CMSNavigationLinkModel;
import com.shopflix.core.model.navigation.CMSNavigationModel;
import com.shopflix.core.repository.CMSNavigationLinkRepository;
import com.shopflix.core.repository.CMSNavigationRepository;
import com.shopflix.merchant.service.navigation.MerchantNavigationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;
import static com.shopflix.core.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "merchantNavigationService")
public class DefaultMerchantNavigationService implements MerchantNavigationService {

    private CMSNavigationRepository cmsNavigationRepository;
    private CMSNavigationLinkRepository cmsNavigationLinkRepository;

    public List<CMSNavigationModel> getAllNavigationNodes() {
        return cmsNavigationRepository.findAll();
    }

    @Override
    public CMSNavigationModel getNavigationNodeForId(Long id)
    {
        validateParameterNotNullStandardMessage("id", id);

        Optional<CMSNavigationModel> cmsNavigationModel = this.cmsNavigationRepository.findById(id);
        if (cmsNavigationModel.isPresent()) {
            return cmsNavigationModel.get();
        }
        throw new ModelNotFoundException("Navigation with id "+id+" requested for the object does not exists in the system");
    }

    public CMSNavigationLinkModel getNavigationLinkForId(Long id)
    {
        validateParameterNotNullStandardMessage("id", id);

        Optional<CMSNavigationLinkModel> cmsNavigationLinkModel = cmsNavigationLinkRepository.findById(id);
        if (cmsNavigationLinkModel.isPresent()) {
            return cmsNavigationLinkModel.get();
        }
        throw new ModelNotFoundException("Navigation Link with id "+id+" requested for the object does not exists in the system");
    }


    public CMSNavigationModel createNavigationNode(String code, String name) {
        validateParameterNotNull(code, "code cannot be null");
        validateParameterNotNull(name, "name cannot be null");

        CMSNavigationModel navigationModel = new CMSNavigationModel();
        navigationModel.setCode(code);
        navigationModel.setName(name);

        return cmsNavigationRepository.save(navigationModel);
    }

    public void addParentForLink(CMSNavigationLinkModel linkModel, Long parentLinkId) {
        CMSNavigationLinkModel parentLinkModel = null;
        if (parentLinkId != null) {
            parentLinkModel = getNavigationLinkForId(parentLinkId);
            parentLinkModel.getChildren().add(linkModel);
        }
        linkModel.setParent(parentLinkModel);
        cmsNavigationLinkRepository.save(linkModel);
    }

    @Override
    public void saveNavigationLinkEntry(CMSNavigationModel cmsNavigationModel, CMSNavigationLinkModel cmsNavigationLinkModel)
    {
        cmsNavigationLinkModel.setNavigation(cmsNavigationModel);
        cmsNavigationModel.getLinks().add(cmsNavigationLinkModel);
        cmsNavigationRepository.save(cmsNavigationModel);
    }

    @Override
    public CMSNavigationLinkModel saveNavigationLinkEntry(CMSNavigationLinkModel linkModel)
    {
        validateParameterNotNullStandardMessage("CMSNavigationLinkModel", linkModel);
        return cmsNavigationLinkRepository.save(linkModel);
    }

    @Override
    public void deleteNavigationLinkEntry(CMSNavigationLinkModel linkModel)
    {
        validateParameterNotNullStandardMessage("CMSNavigationLinkModel", linkModel);
        cmsNavigationLinkRepository.delete(linkModel);
    }


    @Resource(name = "CMSNavigationRepository")
    public void setCmsNavigationRepository(CMSNavigationRepository cmsNavigationRepository) {
        this.cmsNavigationRepository = cmsNavigationRepository;
    }

    @Resource(name = "CMSNavigationLinkRepository")
    public void setCmsNavigationLinkRepository(CMSNavigationLinkRepository cmsNavigationLinkRepository)
    {
        this.cmsNavigationLinkRepository = cmsNavigationLinkRepository;
    }

    public CMSNavigationRepository getCmsNavigationRepository() {
        return cmsNavigationRepository;
    }

    public CMSNavigationLinkRepository getCmsNavigationLinkRepository()
    {
        return cmsNavigationLinkRepository;
    }


}
