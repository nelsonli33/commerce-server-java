package com.shopflix.core.model.order;

import jdk.jfr.Enabled;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cartlineitems")
public class CartLineItemModel extends AbstractOrderLineItemModel{


}
