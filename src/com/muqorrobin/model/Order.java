/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.model;

import java.util.List;
import java.util.Random;

/**
 *
 * @author M Fikri Muqorrobin
 */
public class Order {
    private String noResi;
    private List<Person> person;
    private Paket paket;
    private Tarif tarif;
    
    public Order() {
    }

    public Order(String noResi, Paket paket, Tarif tarif, List<Person> person) {
        this.noResi = noResi;
        this.paket = paket;
        this.tarif = tarif;
        this.person = person;
    }

    /**
     * @return the noResi
     */
    public String getNoResi() {
        return noResi+""+generateResi();
    }

    /**
     * @param noResi the noResi to set
     */
    public void setNoResi(String noResi) {
        this.noResi = noResi;
    }

    /**
     * @return the person
     */
    public List<Person> getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(List<Person> person) {
        this.person = person;
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
    
    private int generateResi(){
        Random rd = new Random();
        int x = rd.nextInt(999);
        int resi = getPaket().getNomorPaket() + x ;
        return resi;
    }

    /**
     * @return the tarif
     */
    public Tarif getTarif() {
        return tarif;
    }

    /**
     * @param tarif the tarif to set
     */
    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }
    
    
}
