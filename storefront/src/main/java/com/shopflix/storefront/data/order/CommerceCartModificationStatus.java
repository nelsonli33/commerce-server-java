package com.shopflix.storefront.data.order;

public interface CommerceCartModificationStatus
{
    /**
     * Indicates a successful modification of the cart
     */
    String SUCCESS = "success";

    /**
     * Indicates a failure to add the requested number of items to cart due to low stock.
     */
    String LOW_STOCK = "lowStock";

    /**
     * Indicates a failure to add the requested number of items to cart due to no stock.
     */
    String NO_STOCK = "noStock";

    /**
     * Indicates a failure to add the requested number of items to cart due to max order quantity exceeded.
     */
    String MAX_ORDER_QUANTITY_EXCEEDED = "maxOrderQuantityExceeded";

    /**
     * Indicates a failure to add the requested number of items to cart due to product unavailability (approval, online
     * dates etc).
     */
    String UNAVAILABLE = "unavailable";

    /**
     * Indicates a successful removing the requested line item from a cart.
     */
    String SUCCESSFULLY_REMOVED = "succesfullyRemoved";
}
