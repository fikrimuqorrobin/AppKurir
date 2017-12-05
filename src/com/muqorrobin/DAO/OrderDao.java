/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.DAO;

import com.muqorrobin.model.Order;
import com.muqorrobin.model.Paket;
import com.muqorrobin.model.Person;

/**
 *
 * @author M Fikri Muqorrobin
 */
public class OrderDao {
    private Order[] orders;
    private Order order;
    
    public Order insertOrder(Person[] persons, Paket paket){
        Order order = new Order(persons, paket);
        return order;
    }

    /**
     * @return the orders
     */
    public Order[] getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    /**
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }
    
    
}
