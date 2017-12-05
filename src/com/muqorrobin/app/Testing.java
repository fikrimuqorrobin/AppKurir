/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.app;

import com.muqorrobin.DAO.BiayaDao;
import com.muqorrobin.model.Address;
import com.muqorrobin.model.Barang;
import com.muqorrobin.model.Order;
import com.muqorrobin.model.Paket;
import com.muqorrobin.model.Person;
import java.util.Date;

/**
 *
 * @author M Fikri Muqorrobin
 */
public class Testing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        Date now = new Date();
        Address alamatPengirim = new Address("tes pengirim","Provinsi 1","Kota 1",1234,"089610202");
        Address alamatPenerima = new Address("tes penerima","Provinsi 4","Medan",3422,"034024322");
        Person pengirim = new Person("Fikri", alamatPengirim);
        Person penerima = new Person("Penerima nya", alamatPenerima);
        Person[] persons = {pengirim, penerima};
        
        Barang barang = new Barang("TV", 4, 400000.0);
        Paket paket = new Paket(10, "Medan", now, "reguler", true, barang);
        Order order = new Order(persons, paket);
        BiayaDao biaya = new BiayaDao(order);
        
        Person[] p = biaya.getOrder().getPerson();
        
        System.out.println("Nama Pengirim = "+p[0].getNama());
        System.out.println("Nama Penerima = "+p[1].getNama());
        System.out.println("Kota Pengirim = "+p[0].getAlamat().getKota());
        System.out.println("Kota Penerima = "+p[1].getAlamat().getKota());
        System.out.println("=================================================");
        System.out.println("Nama Barang = "+biaya.getOrder().getPaket().getBarang().getNamaBarang());
        System.out.println("Berat = "+biaya.getOrder().getPaket().getBarang().getBeratBarang());
        System.out.println("Harga Barang = "+biaya.getOrder().getPaket().getBarang().getHargaBarang());
        System.out.println("Alamat Tujuan = "+biaya.getOrder().getPaket().getKotaTujuan());
        System.out.println("==================================================");
        
        biaya.setOngkirAwal("reguler");
        System.out.println("Layanan = Kilat");
        System.out.println("Ongkir Awal = "+biaya.getOngkirAwal());
        System.out.println("Asuransi = "+biaya.getOrder().getPaket().isAsuransi());
        System.out.println("Harga Asuransi = "+biaya.getHargaAsuransi());
        System.out.println("Total Biaya = "+biaya.getTotalBiaya());
        
    }
    
}
