package com.ra.model.entity;

import java.util.Date;

public class Order {
    private int order_id;
    private User user;
    private Date order_date;
    private double total;
    private StatusName orderStatus;
    private String phone;
    private String address;
    private String note;

    public Order() {
    }

    public Order(int order_id, User user, Date order_date, double total, StatusName orderStatus, String phone, String address, String note) {
        this.order_id = order_id;
        this.user = user;
        this.order_date = order_date;
        this.total = total;
        this.orderStatus = orderStatus;
        this.phone = phone;
        this.address = address;
        this.note = note;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public StatusName getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(StatusName orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
