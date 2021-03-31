package com.example.alfred.model;

public class Order {

    private Integer status;
    private String observation;
    private Integer paymentMethod;

    public Order(Integer status, String observation, Integer paymentMethod) {
        this.status = status;
        this.observation = observation;
        this.paymentMethod = paymentMethod;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
