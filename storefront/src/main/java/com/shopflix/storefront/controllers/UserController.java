package com.shopflix.storefront.controllers;

import com.shopflix.core.controllers.AbstractController;
import com.shopflix.core.response.ApiResult;
import com.shopflix.storefront.facades.user.CustomerFacade;
import com.shopflix.storefront.facades.user.data.RegisterData;
import com.shopflix.storefront.forms.RegisterForm;
import com.shopflix.storefront.forms.validation.RegistrationValidator;
import com.shopflix.storefront.security.AutoLoginStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends AbstractController {

    @Resource(name = "registrationValidator")
    private RegistrationValidator registrationValidator;

    @Resource(name = "customerFacade")
    private CustomerFacade customerFacade;

    @Resource(name = "autoLoginStrategy")
    private AutoLoginStrategy autoLoginStrategy;

    @PostMapping("/register")
    public ResponseEntity<ApiResult> doRegister(
            @RequestHeader(value = "referer", required = false) final String referer,
            @RequestBody final RegisterForm form,
            final BindingResult bindingResult,
            final HttpServletRequest request,
            final HttpServletResponse response) {


        registrationValidator.validate(form, bindingResult);

        doBindingResult(bindingResult);


        RegisterData data = new RegisterData();
        data.setUid(form.getUid());
        data.setUsername(form.getUsername());
        data.setPassword(form.getPwd());
        data.setEmail(data.getEmail());

        customerFacade.register(data);
        autoLoginStrategy.login(form.getUid().toLowerCase(), form.getPwd(), request, response);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResult.success());
    }

}
