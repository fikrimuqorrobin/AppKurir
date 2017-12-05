/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.DAO;

import com.muqorrobin.model.Barang;
import com.muqorrobin.model.Paket;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author M Fikri Muqorrobin
 */
public class PaketDao {
    private Paket[] pakets;
    private Paket paket;
    
    private int noPaket;
    
    public Paket insertPaket(int no, String tujuan, Date kirim, String layanan, boolean asuransi, Barang barang){
        Paket p = new Paket(no, tujuan, kirim, layanan, asuransi, barang);
        return p;
    }
    
    /**
     * @return the pakets
     */
    public Paket[] getPakets() {
        return pakets;
    }

    /**
     * @param pakets the pakets to set
     */
    public void setPakets(Paket[] pakets) {
        this.pakets = pakets;
    }

    /**
     * @return the noPaket
     */
    public int getNoPaket() {
        Random rd = new Random();
        int x = rd.nextInt(999);
        return x;
    }

    /**
     * @param noPaket the noPaket to set
     */
    public void setNoPaket(int noPaket) {
        this.noPaket = noPaket;
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
    
}
