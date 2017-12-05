/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.model;

import java.util.Random;

/**
 *
 * @author M Fikri Muqorrobin
 */
public class Order {

    private String noResi;
    private Person[] person;
    private Paket paket;

    public Order() {
    }

    public Order( Person[] person, Paket paket) {
        this.person = person;
        this.paket = paket;
    }

    /**
     * @return the noResi
     */
    public String getNoResi() {
        return getPaket().getNomorPaket()+"-DEC-17-"+ generateResi();
    }

    /**
     * @return the paket
     */
    public Paket getPaket() {
        return paket;
    }

    /**
     * @param paket the paket to set
     */
    public void setPaket(Paket paket) {
        this.paket = paket;
    }

    private int generateResi() {
        Random rd = new Random();
        int x = rd.nextInt(999);
        int resi = getPaket().getNomorPaket() + x;
        return resi;
    }

    /**
     * @return the person
     */
    public Person[] getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person[] person) {
        this.person = person;
    }

}
