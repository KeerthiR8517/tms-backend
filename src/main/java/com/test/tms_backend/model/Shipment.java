package com.test.tms_backend.model;

import java.time.LocalDateTime;

public class Shipment {
    private String id;
    private String shipperName;
    private String carrierName;
    private String pickupLocation;
    private String deliveryLocation;
    private String trackingNumber;
    private String status;
    private double rate;
    private LocalDateTime createdAt;

    // Constructors
    public Shipment() {}

    public Shipment(String id, String shipperName, String carrierName, String pickupLocation,
                    String deliveryLocation, String trackingNumber, String status, double rate,
                    LocalDateTime createdAt) {
        this.id = id;
        this.shipperName = shipperName;
        this.carrierName = carrierName;
        this.pickupLocation = pickupLocation;
        this.deliveryLocation = deliveryLocation;
        this.trackingNumber = trackingNumber;
        this.status = status;
        this.rate = rate;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
