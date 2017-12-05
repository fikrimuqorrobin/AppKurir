/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.DAO;

import com.muqorrobin.model.Barang;

/**
 *
 * @author M Fikri Muqorrobin
 */
public class BarangDao {
    private Barang[] barangs;
    private Barang barang;
    
    public Barang insertBarang(String nama, int berat, double harga){
        Barang b = new Barang(nama, berat, harga);
        return b;
    }
    
    /**
     * @return the barang
     */
    public Barang[] getBarangs() {
        return barangs;
    }

    /**
     * @param barangs the barangs to set
     */
    public void setBarangs(Barang[] barangs) {
        this.barangs = barangs;
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
