package com.delivery.database.entities;

import java.sql.Date;
import java.sql.Timestamp;

public class Application implements Entity {

    public static final String BAGGAGE_DOCUMENTS = "documents";
    public static final String BAGGAGE_FRAGILE = "fragile";
    public static final String BAGGAGE_CLOTHES = "clothes";
    public static final String BAGGAGE_PACKAGE = "package";

    public static final String STATE_IN_PROCESSING = "in_processing";
    public static final String STATE_WAITING_FOR_PAYMENT = "waiting_for_payment";
    public static final String STATE_SENT = "sent";

    private int id;
    private int department1Id;
    private int department2Id;
    private Timestamp createDate;
    private int userId;
    private String state;
    private int size;
    private float weight;
    private Date receiveDate;
    private String baggageType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartment1Id() {
        return department1Id;
    }

    public void setDepartment1Id(int department1Id) {
        this.department1Id = department1Id;
    }

    public int getDepartment2Id() {
        return department2Id;
    }

    public void setDepartment2Id(int department2Id) {
        this.department2Id = department2Id;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getBaggageType() {
        return baggageType;
    }

    public void setBaggageType(String baggageType) {
        this.baggageType = baggageType;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", department1Id=" + department1Id +
                ", department2Id=" + department2Id +
                ", createDate=" + createDate +
                ", userId=" + userId +
                ", state='" + state + '\'' +
                ", size=" + size +
                ", weight=" + weight +
                ", receiveDate=" + receiveDate +
                ", baggageType='" + baggageType + '\'' +
                '}';
    }
}
