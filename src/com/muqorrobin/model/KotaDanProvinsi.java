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
public class KotaDanProvinsi {
    private String kota;
    private String provinsi;

    public KotaDanProvinsi() {
    }

    public KotaDanProvinsi(String kota, String provinsi) {
        this.kota = kota;
        this.provinsi = provinsi;
    }

    /**
     * @return the kota
     */
    public String getKota() {
        return kota;
    }

    /**
     * @param kota the kota to set
     */
    public void setKota(String kota) {
        this.kota = kota;
    }

    /**
     * @return the provinsi
     */
    public String getProvinsi() {
        return provinsi;
    }

    /**
     * @param provinsi the provinsi to set
     */
    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }
    
    
}
