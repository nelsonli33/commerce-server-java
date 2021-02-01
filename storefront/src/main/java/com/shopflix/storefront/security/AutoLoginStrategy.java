package com.shopflix.storefront.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Strategy for automatic login of a user after registration
 */
public interface AutoLoginStrategy {
    /**
     * Login a user
     *
     * @param username
     * @param password
     * @param request
     * @param response
     */
    void login(String username, String password, HttpServletRequest request, HttpServletResponse response);
}
