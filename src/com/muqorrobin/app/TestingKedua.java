/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.app;

import com.muqorrobin.DAO.AddressDao;
import com.muqorrobin.DAO.PersonDao;
import com.muqorrobin.model.Address;
import com.muqorrobin.model.Person;

/**
 *
 * @author M Fikri Muqorrobin
 */
public class TestingKedua {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AddressDao ad = new AddressDao();
        Address alamatPengirim = ad.createAddress("Alamat Lengkap Pengirim", "Provinsi Pengirim", "Kota Pengirim", 1234, "Telepon Pengirim");
        Address alamatPenerima = ad.createAddress("Alamat Lengkap Penerima", "Provinsi Penerima", "Kota Penerima", 112233, "Telepon Penerima");
        Address[] alamats = {alamatPenerima, alamatPengirim};
        ad.setAlamats(alamats);
        
        PersonDao pd = new PersonDao();
        Person penerima = pd.createPerson("Nama Penerima", alamatPenerima);
        Person pengirim = pd.createPerson("Nama Pengirim", alamatPengirim);
        Person[] persons = {penerima, pengirim};
        
        for (Person person : persons) {
            System.out.println(person.getNama()+", "+person.getAlamat().getKota());
        }
    
    }
    
}
