/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.app;

import com.muqorrobin.DAO.TarifDao;
import com.muqorrobin.model.Address;
import com.muqorrobin.model.Barang;
import com.muqorrobin.model.Paket;
import com.muqorrobin.model.PerhitunganBiaya;
import com.muqorrobin.model.Person;
import com.muqorrobin.model.Tarif;
import java.util.Date;

/**
 *
 * @author user
 */
public class Tes {

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
        
        for (Person person : persons) {
            System.out.println("Nama : " +person.getNama()+", Kota : "+person.getAlamat().getKota());
        }
        
        System.out.println("Nama Pengirim : " + persons[0].getNama());
        System.out.println("Alamat Pengirim : " + persons[0].getAlamat().getKota());
        
        System.out.println("==================================");
        
        Barang barang = new Barang("TV", 6, 400000.0);
        Paket paket = new Paket(10, "Semarang", now, "reguler", true, barang);
        
        PerhitunganBiaya PB = new PerhitunganBiaya(penerima, barang, paket);
        PB.setOngkirAwal("ons");
        System.out.println("Ongkir Awal = "+PB.getOngkirAwal());
        System.out.println("Berat = "+PB.getBarang().getBeratBarang());
        System.out.println("Asuransi = "+paket.isAsuransi());
        System.out.println("Harga Asuransi = "+PB.getHargaAsuransi());
        System.out.println("Ongkos + Asuransi = "+PB.getOngkosPlusAsuransi());
        System.out.println("Total Biaya = "+PB.getTotalBiaya());
    }
    
}
