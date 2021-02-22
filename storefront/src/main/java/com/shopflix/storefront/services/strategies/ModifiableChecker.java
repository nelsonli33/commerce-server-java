package com.shopflix.storefront.services.strategies;

public interface ModifiableChecker<T>
{
    boolean canModify(T given);
}
