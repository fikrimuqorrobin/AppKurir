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
public class Address {
    private String alamatLengkap;
    private String provinsi;
    private String kota;
    private int kodePos;
    private String telepon;

    public Address() {
    }

    public Address(String alamatLengkap, String provinsi, String kota, int kodePos, String telepon) {
        this.alamatLengkap = alamatLengkap;
        this.provinsi = provinsi;
        this.kota = kota;
        this.kodePos = kodePos;
        this.telepon = telepon;
    }

    /**
     * @return the alamatLengkap
     */
    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    /**
     * @param alamatLengkap the alamatLengkap to set
     */
    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkap = alamatLengkap;
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
     * @return the kodePos
     */
    public int getKodePos() {
        return kodePos;
    }

    /**
     * @param kodePos the kodePos to set
     */
    public void setKodePos(int kodePos) {
        this.kodePos = kodePos;
    }

    /**
     * @return the telepon
     */
    public String getTelepon() {
        return telepon;
    }

    /**
     * @param telepon the telepon to set
     */
    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
    
    
}
