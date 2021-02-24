package com.shopflix.storefront.services.order;

import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.core.model.order.AbstractOrderModel;
import com.shopflix.storefront.services.order.strategies.calculation.FindDeliveryCostStrategy;

/**
 * Service allows calculation or recalculation of the order. This includes calculation of all the entries, taxes and
 * discount values. Information about price, discounts are fetched using dedicated strategies :
 * {@link FindDiscountValuesStrategy}, {@link FindPriceStrategy}. Also delivery costs is resolved using strategy
 * {@link FindDeliveryCostStrategy}.
 */
public interface CalculationService
{
    void calculate(AbstractOrderModel order);

    void calculateTotals(AbstractOrderModel order);

    void calculateTotals(AbstractOrderLineItemModel lineItem);
}
