package com.shopflix.storefront.exceptions;

public class AbstractOrderDeliveryAddressException extends RuntimeException
{
    public AbstractOrderDeliveryAddressException(String message)
    {
        super(message);
    }

    public AbstractOrderDeliveryAddressException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public AbstractOrderDeliveryAddressException(Throwable cause)
    {
        super(cause);
    }
}
