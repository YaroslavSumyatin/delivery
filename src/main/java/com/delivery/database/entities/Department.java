package com.delivery.database.entities;

public class Department implements Entity {

    private int id;
    private String index;
    private String address;
    private int number;
    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", index='" + index + '\'' +
                ", address='" + address + '\'' +
                ", number=" + number +
                ", cityId=" + cityId +
                '}';
    }
}
