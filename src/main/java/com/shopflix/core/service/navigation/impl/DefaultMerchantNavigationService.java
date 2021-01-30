package com.shopflix.core.service.navigation.impl;

import com.shopflix.core.model.navigation.CMSNavigationModel;
import com.shopflix.core.repository.CMSNavigationRepository;
import com.shopflix.core.service.navigation.MerchantNavigationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.shopflix.core.util.ServicesUtil.validateParameterNotNull;

@Service(value = "merchantNavigationService")
public class DefaultMerchantNavigationService implements MerchantNavigationService {

    private CMSNavigationRepository cmsNavigationRepository;

    public List<CMSNavigationModel> getAllNavigationNodes() {
        return cmsNavigationRepository.findAll();
    }

    public CMSNavigationModel createNavigationNode(String code, String name) {
        validateParameterNotNull(code, "code cannot be null");
        validateParameterNotNull(name, "name cannot be null");

        CMSNavigationModel navigationModel = new CMSNavigationModel();
        navigationModel.setCode(code);
        navigationModel.setName(name);

        return cmsNavigationRepository.save(navigationModel);
    }






    @Resource(name = "CMSNavigationRepository")
    public void setCmsNavigationRepository(CMSNavigationRepository cmsNavigationRepository) {
        this.cmsNavigationRepository = cmsNavigationRepository;
    }

    public CMSNavigationRepository getCmsNavigationRepository() {
        return cmsNavigationRepository;
    }

}
