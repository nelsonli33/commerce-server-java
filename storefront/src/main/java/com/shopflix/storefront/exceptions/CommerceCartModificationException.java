package com.shopflix.storefront.exceptions;

/**
 * Exception thrown if the cart cannot be modified
 */
public class CommerceCartModificationException extends RuntimeException
{
    public CommerceCartModificationException(final String message) {
        super(message);
    }

    public CommerceCartModificationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
