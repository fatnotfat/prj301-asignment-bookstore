/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatnt.orders;

import java.io.Serializable;

/**
 *
 * @author HAU NUONG MO HANH
 */
public class OrdersDTO implements Serializable {
    
    private String orderId;
    private String name;
    private int quantity;
    private float price;
    private float sumOf;

    public OrdersDTO() {
    }

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the sumOf
     */
    public float getSumOf() {
        return sumOf;
    }

    /**
     * @param sumOf the sumOf to set
     */
    public void setSumOf(float sumOf) {
        this.sumOf = sumOf;
    }

    

}
