/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.DAO;

import com.muqorrobin.model.Address;

/**
 *
 * @author M Fikri Muqorrobin
 */
public class AddressDao {
    private Address[] alamats;
    private Address alamat;
    
    public Address createAddress(String alamatLengkap, String provinsi, String kota, int pos, String telepon){
        Address ad = new Address(alamatLengkap, provinsi, kota, pos, telepon);
        return ad;
    }
    
    /**
     * @return the alamats
     */
    public Address[] getAlamats() {
        return alamats;
    }

    /**
     * @param alamats the alamats to set
     */
    public void setAlamats(Address[] alamats) {
        this.alamats = alamats;
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
