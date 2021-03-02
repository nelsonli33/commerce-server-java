package com.shopflix.core.model.order;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shopflix.core.enums.PaymentModeType;
import com.shopflix.core.enums.PaymentStatus;
import com.shopflix.core.model.ItemModel;
import com.shopflix.core.model.order.delivery.DeliveryAddressModel;
import com.shopflix.core.model.order.delivery.DeliveryModeModel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractOrderModel extends ItemModel {

    private String code;
    private Double deliveryCost;
    private Double subtotal;
    private Double totalPrice;
    private Double totalDiscounts;

    @OneToOne(fetch = FetchType.LAZY)
    private DeliveryModeModel deliveryMode;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    private DeliveryAddressModel deliveryAddress;

    private PaymentModeType paymentMode;
    private PaymentStatus paymentStatus;
    private Date payTime;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<AbstractOrderLineItemModel> lineItems;



    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Double getDeliveryCost()
    {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost)
    {
        this.deliveryCost = deliveryCost;
    }

    public Double getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(Double subtotal)
    {
        this.subtotal = subtotal;
    }

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public Double getTotalDiscounts()
    {
        return totalDiscounts;
    }

    public void setTotalDiscounts(Double totalDiscounts)
    {
        this.totalDiscounts = totalDiscounts;
    }

    public DeliveryModeModel getDeliveryMode()
    {
        return deliveryMode;
    }

    public void setDeliveryMode(DeliveryModeModel deliveryMode)
    {
        this.deliveryMode = deliveryMode;
    }

    public DeliveryAddressModel getDeliveryAddress()
    {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddressModel deliveryAddress)
    {
        this.deliveryAddress = deliveryAddress;
    }

    public PaymentModeType getPaymentMode()
    {
        return paymentMode;
    }

    public void setPaymentMode(PaymentModeType paymentMode)
    {
        this.paymentMode = paymentMode;
    }

    public PaymentStatus getPaymentStatus()
    {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    public Date getPayTime()
    {
        return payTime;
    }

    public void setPayTime(Date payTime)
    {
        this.payTime = payTime;
    }

    public List<AbstractOrderLineItemModel> getLineItems()
    {
        return lineItems;
    }

    public void setLineItems(List<AbstractOrderLineItemModel> lineItems)
    {
        this.lineItems = lineItems;
    }
}
