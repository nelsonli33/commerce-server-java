package com.shopflix.storefront.facades.user;

import com.shopflix.storefront.facades.user.data.RegisterData;

/**
 * Defines an API to perform various customer related operations
 */
public interface CustomerFacade {

    /**
     * Register a user with given parameters
     *
     * @param registerData
     *           the user data the user will be registered with
     */
    void register(RegisterData registerData);

}
