/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.model;

import java.util.Date;

/**
 *
 * @author M Fikri Muqorrobin
 */
public class Paket {
    
    private int nomorPaket;
    private String kotaTujuan;
    private Date tanggalKirim;
    private String jenisLayanan;
    private boolean asuransi;
    private Barang barang;

    public Paket() {
    }

    public Paket(int nomorPaket, String kotaTujuan, Date tanggalKirim, String jenisLayanan, boolean asuransi, Barang barang) {
        this.nomorPaket = nomorPaket;
        this.kotaTujuan = kotaTujuan;
        this.tanggalKirim = tanggalKirim;
        this.jenisLayanan = jenisLayanan;
        this.asuransi = asuransi;
        this.barang = barang;
    }

    /**
     * @return the nomorPaket
     */
    public int getNomorPaket() {
        return nomorPaket;
    }

    /**
     * @param nomorPaket the nomorPaket to set
     */
    public void setNomorPaket(int nomorPaket) {
        this.nomorPaket = nomorPaket;
    }

    /**
     * @return the kotaTujuan
     */
    public String getKotaTujuan() {
        return kotaTujuan;
    }

    /**
     * @param kotaTujuan the kotaTujuan to set
     */
    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    /**
     * @return the tanggalKirim
     */
    public Date getTanggalKirim() {
        return tanggalKirim;
    }

    /**
     * @param tanggalKirim the tanggalKirim to set
     */
    public void setTanggalKirim(Date tanggalKirim) {
        this.tanggalKirim = tanggalKirim;
    }

    /**
     * @return the jenisLayanan
     */
    public String getJenisLayanan() {
        return jenisLayanan;
    }

    /**
     * @param jenisLayanan the jenisLayanan to set
     */
    public void setJenisLayanan(String jenisLayanan) {
        this.jenisLayanan = jenisLayanan;
    }

    /**
     * @return the asuransi
     */
    public boolean isAsuransi() {
        return asuransi;
    }

    /**
     * @param asuransi the asuransi to set
     */
    public void setAsuransi(boolean asuransi) {
        this.asuransi = asuransi;
    }

    /**
     * @return the barang
     */
    public Barang getBarang() {
        return barang;
    }

    /**
     * @param barang the barang to set
     */
    public void setBarang(Barang barang) {
        this.barang = barang;
    }
    
    
    
}
