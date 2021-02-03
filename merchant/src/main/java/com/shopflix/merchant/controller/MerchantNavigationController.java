package com.shopflix.merchant.controller;

import com.shopflix.core.response.ApiResult;
import com.shopflix.merchant.data.NavigationData;
import com.shopflix.merchant.data.NavigationLinkData;
import com.shopflix.merchant.facades.navigation.CMSNavigationFacade;
import com.shopflix.merchant.forms.NavigationLinkForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/merchant/api/v1/navigations")
public class MerchantNavigationController
{

    private static final Logger LOG = LoggerFactory.getLogger(MerchantNavigationController.class);

    @Resource(name = "cmsNavigationFacade")
    private CMSNavigationFacade cmsNavigationFacade;

    @GetMapping
    public ResponseEntity<ApiResult<List<NavigationData>>> navigations()
    {
        List<NavigationData> allNavigationData = cmsNavigationFacade.getAllNavigationNodes();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(allNavigationData));
    }

    @GetMapping("/{navigationId}")
    public ResponseEntity<ApiResult<NavigationData>> navigationDetail(@PathVariable(name = "navigationId") Long id)
    {
        NavigationData navigationData = cmsNavigationFacade.getNavigationForId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(navigationData));
    }


    @PostMapping
    public ResponseEntity<ApiResult<NavigationData>> createNavigationNode(@RequestBody Map<String, Object> requestBody)
    {
        String code = requestBody.get("code").toString();
        String name = requestBody.get("name").toString();

        NavigationData navigationData = cmsNavigationFacade.createNavigationNode(code, name);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(navigationData));
    }

    @GetMapping("/{navigationId}/links/{linkId}")
    public ResponseEntity<ApiResult<NavigationLinkData>> navigationLinkDetail(@PathVariable(name = "linkId") Long linkId)
    {
        NavigationLinkData navigationLinkData = cmsNavigationFacade.getNavigationLink(linkId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(navigationLinkData));
    }


    @PostMapping("/{navigationId}/links")
    public ResponseEntity<ApiResult<Object>> addNavigationLink(@PathVariable(name = "navigationId") Long id, @RequestBody NavigationLinkForm form)
    {
        NavigationLinkData data = fillLinkFormToData(form);

        cmsNavigationFacade.addNavigationLink(id, data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success());
    }

    @PutMapping("/{navigationId}/links/{linkId}")
    public ResponseEntity<ApiResult<Object>> editNavigationLink(@PathVariable(name = "linkId") Long linkId, @RequestBody NavigationLinkForm form)
    {
        NavigationLinkData data = fillLinkFormToData(form);

        cmsNavigationFacade.editNavigationLink(linkId, data);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success());
    }



    @DeleteMapping("/{navigationId}/links/{linkId}")
    public ResponseEntity<ApiResult<Object>> deleteNavigationLink(@PathVariable(name = "linkId") Long linkId)
    {

        cmsNavigationFacade.deleteNavigationLink(linkId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(ApiResult.success());
    }

    private NavigationLinkData fillLinkFormToData(@RequestBody NavigationLinkForm form)
    {
        NavigationLinkData data = new NavigationLinkData();
        data.setName(form.getName());
        data.setType(form.getType());
        data.setUrl(form.getUrl());
        data.setTarget(form.getTarget());
        data.setSortOrder(form.getSortOrder());
        data.setParentId(form.getParentId());
        return data;
    }
}
