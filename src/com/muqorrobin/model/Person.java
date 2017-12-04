/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.model;

/**
 *
 * @author M Fikri Muqorrobin
 */
public class Person {
    
    private String nama;
    private Address alamat;
    
    public Person() {
    }

    public Person(String nama, Address alamat) {
        this.nama = nama;
        this.alamat = alamat;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the alamat
     */
    public Address getAlamat() {
        return alamat;
    }

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(Address alamat) {
        this.alamat = alamat;
    }
        
}
