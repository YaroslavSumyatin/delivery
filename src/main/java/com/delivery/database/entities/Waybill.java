package com.delivery.database.entities;

import java.sql.Timestamp;

public class Waybill implements Entity {

    public static final String STATE_WAITING_FOR_PAYMENT = "waiting_for_payment";
    public static final String STATE_PAID = "paid";

    private int id;
    private Timestamp createDate;
    private int userId;
    private int applicationId;
    private String state;
    private float cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Waybill{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", userId=" + userId +
                ", applicationId=" + applicationId +
                ", state='" + state + '\'' +
                ", cost=" + cost +
                '}';
    }
}
