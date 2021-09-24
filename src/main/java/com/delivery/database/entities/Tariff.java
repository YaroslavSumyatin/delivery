package com.delivery.database.entities;

public class Tariff implements Entity {

    public static final String DISTANCE_CITY = "city";
    public static final String DISTANCE_REGION = "region";
    public static final String DISTANCE_COUNTRY = "country";

    int size;
    float weight;
    String distance;

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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    float sizeCost;
    float weightCost;
    float distanceCost;
    float deliveryCost;

    public float getSizeCost() {
        return sizeCost;
    }

    public void setSizeCost(float sizeCost) {
        this.sizeCost = sizeCost;
    }

    public float getWeightCost() {
        return weightCost;
    }

    public void setWeightCost(float weightCost) {
        this.weightCost = weightCost;
    }

    public float getDistanceCost() {
        return distanceCost;
    }

    public void setDistanceCost(float distanceCost) {
        this.distanceCost = distanceCost;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(float deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public void calculateCost() {
        float cost = sizeCost + weightCost + distanceCost;
        deliveryCost = (float) (Math.round(cost * 100.0) / 100.0);
    }
}
