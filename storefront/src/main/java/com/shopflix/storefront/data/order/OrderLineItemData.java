package com.shopflix.storefront.data.order;

import java.io.Serializable;

public class OrderLineItemData implements Serializable
{
    private static final long serialVersionUID = 646064757726830593L;

    private Long id;

    private String name;

    private Long productId;

    private String productName;

    private Long quantity;

    private Double price;

    private Long variantId;

    private String variantName;

    private Double subtotal;

    private Double totalDiscounts;

    private Double totalPrice;

    private boolean updateable;


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public Long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Long quantity)
    {
        this.quantity = quantity;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Long getVariantId()
    {
        return variantId;
    }

    public void setVariantId(Long variantId)
    {
        this.variantId = variantId;
    }

    public String getVariantName()
    {
        return variantName;
    }

    public void setVariantName(String variantName)
    {
        this.variantName = variantName;
    }

    public Double getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(Double subtotal)
    {
        this.subtotal = subtotal;
    }

    public Double getTotalDiscounts()
    {
        return totalDiscounts;
    }

    public void setTotalDiscounts(Double totalDiscounts)
    {
        this.totalDiscounts = totalDiscounts;
    }

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public boolean isUpdateable()
    {
        return updateable;
    }

    public void setUpdateable(boolean updateable)
    {
        this.updateable = updateable;
    }
}
