package com.shopflix.storefront.services.order;

import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.core.model.order.AbstractOrderModel;

/**
 * Service allows calculation or recalculation of the order. This includes calculation of all the entries, taxes and
 * discount values. Information about price, discounts are fetched using dedicated strategies :
 * {@link FindDiscountValuesStrategy}, {@link FindPriceStrategy}. Also delivery costs is resolved using strategy
 * {@link FindPaymentCostStrategy}.
 */
public interface CalculationService
{
    void calculate(AbstractOrderModel order);

    void calculateTotals(AbstractOrderModel order);

    void calculateTotals(AbstractOrderLineItemModel lineItem);
}
