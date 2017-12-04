/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.model;

import com.muqorrobin.DAO.TarifDao;

public class PerhitunganBiaya {
    
    private double ongkirAwal;
    private final double persenAsuransi = 0.1;
    private double ongkosPlusAsuransi;
    private double hargaAsuransi;
    private double totalBiaya;
    
    private TarifDao TD = new TarifDao();

    public PerhitunganBiaya() {
    }

    public PerhitunganBiaya(Person p, Barang barang, Paket paket) {
        this.p = p;
        this.barang = barang;
        this.paket = paket;
    }
    
    private Person p;
    private Tarif tarif;
    private Barang barang;
    private Paket paket;
    
    public double getOngkirAwal() {
        return this.ongkirAwal;
    }

    public void setOngkirAwal(String tipePaket) {
        String kotaPenerima = getP().getAlamat().getKota();
        
        for(Tarif t : getTD().getTarifTarif()){
            if(t.getKotaTujuan().equalsIgnoreCase(kotaPenerima)){
                switch(tipePaket.toLowerCase()){
                    case "reguler" :
                        this.ongkirAwal = t.getReguler() * getBarang().getBeratBarang();
                        break;
                    case "kilat" :
                        this.ongkirAwal = t.getKilat() * getBarang().getBeratBarang();
                        break;
                    case "sds" :
                        this.ongkirAwal = t.getSds() * getBarang().getBeratBarang();
                        break;
                    case "ons" :
                        this.ongkirAwal = t.getOns() * getBarang().getBeratBarang();
                        break;
                    case "hds" :
                        this.ongkirAwal = t.getHds() * getBarang().getBeratBarang();
                        break;
                    default :
                        this.ongkirAwal = 0.0;
                }
            }
        }
    }

    public double getPersenAsuransi() {
        return persenAsuransi;
    }

    public double getOngkosPlusAsuransi() {
        return getOngkirAwal() + getHargaAsuransi() ;
    }

    public double getHargaAsuransi() {
        return this.hargaAsuransi = getBarang().getHargaBarang() * getPersenAsuransi();
    }

    public double getTotalBiaya() {
        if(getPaket().isAsuransi()){
            this.totalBiaya = getOngkosPlusAsuransi() ;
        } else {
            this.totalBiaya = getOngkirAwal();
        }
        return this.totalBiaya;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }

    public Tarif getTarif() {
        return tarif;
    }

    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }
    
    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }
    
    public Paket getPaket() {
        return paket;
    }

    public void setPaket(Paket paket) {
        this.paket = paket;
    }

    /**
     * @return the TD
     */
    public TarifDao getTD() {
        return TD;
    }

    
}
