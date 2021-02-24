package com.shopflix.storefront.services.order.impl;

import com.shopflix.core.model.order.AbstractOrderLineItemModel;
import com.shopflix.core.model.order.AbstractOrderModel;
import com.shopflix.storefront.services.AbstractBusinessService;
import com.shopflix.storefront.services.order.CalculationService;
import com.shopflix.storefront.services.order.strategies.calculation.FindDeliveryCostStrategy;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

public class DefaultCalculationService extends AbstractBusinessService
        implements CalculationService
{

    private FindDeliveryCostStrategy findDeliveryCostStrategy;

    @Override
    public void calculate(AbstractOrderModel order)
    {
        // -----------------------------
        // first calc all entries
        calculateLineItems(order);
        // -----------------------------
        // reset own values
        resetAdditionalCosts(order);
        // -----------------------------
        // now calculate all totals
        calculateTotals(order);
    }

    public void calculateLineItems(final AbstractOrderModel order)
    {
        double subtotal = 0.0;
        for (final AbstractOrderLineItemModel lineItem : order.getLineItems())
        {
            calculateTotals(lineItem);
            subtotal += lineItem.getTotalPrice();
        }
        order.setSubtotal(subtotal);
    }

    @Override
    public void calculateTotals(AbstractOrderModel order)
    {

        // subtotal
        final double subtotal = order.getSubtotal();
        // discounts

//        final double totalDiscounts = calculateDiscountValues(order, recalculate);
        final double roundedTotalDiscounts = 0;
        order.setTotalDiscounts(roundedTotalDiscounts);

        // set total
        final double totalPrice = subtotal + order.getDeliveryCost()
                - roundedTotalDiscounts;
        order.setTotalPrice(totalPrice);


        getModelService().save(order);
    }


    @Override
    public void calculateTotals(AbstractOrderLineItemModel lineItem)
    {

        final double totalPriceWithoutDiscount =
                BigDecimal.valueOf(lineItem.getPrice() * lineItem.getQuantity()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        lineItem.setSubtotal(totalPriceWithoutDiscount);

        /*
         * apply discounts (will be rounded each) convert absolute discount values in case their currency doesn't match
         * the order currency
         */
        //YTODO : use CalculatinService methods to apply discounts
//        final List appliedDiscounts = DiscountValue.apply(quantity, totalPriceWithoutDiscount, digits,
//                convertDiscountValues(order, entry.getDiscountValues()), curr.getIsocode());
//        entry.setDiscountValues(appliedDiscounts);
//
        double totalPrice = totalPriceWithoutDiscount;
//        for (final Iterator it = appliedDiscounts.iterator(); it.hasNext();)
//        {
//            totalPrice -= ((DiscountValue) it.next()).getAppliedValue();
//        }

        // set total discounts
        lineItem.setTotalDiscounts(0.0);

        // set total price
        lineItem.setTotalPrice(totalPrice);


        getModelService().save(lineItem);
    }



    protected void resetAdditionalCosts(final AbstractOrderModel order)
    {
        final Double deliveryCostValue = findDeliveryCostStrategy.getDeliveryCost(order);
        order.setDeliveryCost(deliveryCostValue);
    }


    public FindDeliveryCostStrategy getFindDeliveryCostStrategy()
    {
        return findDeliveryCostStrategy;
    }

    public void setFindDeliveryCostStrategy(FindDeliveryCostStrategy findDeliveryCostStrategy)
    {
        this.findDeliveryCostStrategy = findDeliveryCostStrategy;
    }
}
