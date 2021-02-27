package com.shopflix.core.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shopflix.core.listener.CustomerModelListener;
import com.shopflix.core.model.ItemModel;
import com.shopflix.core.model.order.CartModel;
import com.shopflix.core.model.order.OrderModel;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@EntityListeners({CustomerModelListener.class})
@Entity
@Table(name = "customers")
@DynamicUpdate
public class CustomerModel extends ItemModel
{
    private static final long serialVersionUID = 1687540371837402837L;

    @Column(nullable = false, unique = true)
    private String uid;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String tags;
    private Date birthday;
    private boolean acceptsMarketing;

    @OneToOne(fetch = FetchType.EAGER)
    private CustomerAddressModel defaultShipmentAddress;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<CustomerAddressModel> addresses = new ArrayList<>();


    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderModel> orders = new LinkedHashSet<>();


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isAcceptsMarketing() {
        return acceptsMarketing;
    }

    public void setAcceptsMarketing(boolean acceptsMarketing) {
        this.acceptsMarketing = acceptsMarketing;
    }

    public CustomerAddressModel getDefaultShipmentAddress()
    {
        return defaultShipmentAddress;
    }

    public void setDefaultShipmentAddress(CustomerAddressModel defaultShipmentAddress)
    {
        this.defaultShipmentAddress = defaultShipmentAddress;
    }

    public List<CustomerAddressModel> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(List<CustomerAddressModel> addresses)
    {
        this.addresses = addresses;
    }

    public Set<OrderModel> getOrders()
    {
        return orders;
    }

    public void setOrders(Set<OrderModel> orders)
    {
        this.orders = orders;
    }
}
