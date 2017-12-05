/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.DAO;

import com.muqorrobin.model.Order;
import com.muqorrobin.model.Person;
import com.muqorrobin.model.Tarif;

/**
 *
 * @author M Fikri Muqorrobin
 */
public class BiayaDao {

    private double ongkirAwal;
    private final double persenAsuransi = 0.03;
    private double hargaAsuransi;
    private double totalBiaya;
    private Order order;
    private TarifDao tarif = new TarifDao();

    public BiayaDao() {
    }

    public BiayaDao(Order order) {
        this.order = order;
    }

    /**
     * @return the ongkirAwal
     */
    public double getOngkirAwal() {
        return ongkirAwal  * getOrder().getPaket().getBarang().getBeratBarang();
    }

    public void setOngkirAwal(String tipePaket) {
        Person[] p = getOrder().getPerson();
        String kotaPenerima = p[1].getAlamat().getKota();

        for (Tarif t : getTarif().getTarifTarif()) {
            if (t.getKotaTujuan().equalsIgnoreCase(kotaPenerima)) {
                switch (tipePaket.toLowerCase()) {
                    case "reguler":
                        this.ongkirAwal = t.getReguler();
                        break;
                    case "kilat":
                        this.ongkirAwal = t.getKilat() ;
                        break;
                    case "sds":
                        this.ongkirAwal = t.getSds() ;
                        break;
                    case "ons":
                        this.ongkirAwal = t.getOns() ;
                        break;
                    case "hds":
                        this.ongkirAwal = t.getHds() ;
                        break;
                    default:
                        this.ongkirAwal = 0.0;
                }
            }
        }
    }

    /**
     * @return the persenAsuransi
     */
    public double getPersenAsuransi() {
        return persenAsuransi;
    }

    /**
     * @return the hargaAsuransi
     */
    public double getHargaAsuransi() {
        return this.hargaAsuransi = getOrder().getPaket().getBarang().getHargaBarang() * getPersenAsuransi();
    }

    /**
     * @return the totalBiaya
     */
    public double getTotalBiaya() {
        if (getOrder().getPaket().isAsuransi()) {
            this.totalBiaya = getOngkirAwal() + getHargaAsuransi();
        } else {
            this.totalBiaya = getOngkirAwal();
        }
        return this.totalBiaya;
    }


    /**
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * @return the tarif
     */
    public TarifDao getTarif() {
        return tarif;
    }

    /**
     * @param tarif the tarif to set
     */
    public void setTarif(TarifDao tarif) {
        this.tarif = tarif;
    }

}
