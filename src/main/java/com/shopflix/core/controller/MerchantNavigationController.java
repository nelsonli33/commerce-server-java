package com.shopflix.core.controller;

import com.shopflix.core.model.category.CategoryModel;
import com.shopflix.core.model.navigation.CMSNavigationModel;
import com.shopflix.core.response.ApiResult;
import com.shopflix.core.service.navigation.MerchantNavigationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/merchant/api/v1/navigations")
public class MerchantNavigationController {

    private static final Logger LOG = LoggerFactory.getLogger(MerchantNavigationController.class);

    @Resource(name = "merchantNavigationService")
    private MerchantNavigationService merchantNavigationService;

    @GetMapping
    public ResponseEntity navigations() {
        List<CMSNavigationModel> allNavigationNodes = merchantNavigationService.getAllNavigationNodes();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(allNavigationNodes));
    }


    @PostMapping
    public ResponseEntity<ApiResult<CMSNavigationModel>> createNavigationNode(@RequestBody Map<String,Object> body) {

        String code = body.get("code").toString();
        String name = body.get("name").toString();

        CMSNavigationModel navigationModel = merchantNavigationService.createNavigationNode(code, name);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success(navigationModel));
    }
}
