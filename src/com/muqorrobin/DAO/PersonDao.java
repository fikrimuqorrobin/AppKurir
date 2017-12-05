/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.DAO;

import com.muqorrobin.model.Address;
import com.muqorrobin.model.Person;

/**
 *
 * @author M Fikri Muqorrobin
 */
public class PersonDao {
    private Person[] pelanggan;
    private Person person;
    
    public Person createPerson(String nama, Address alamat){
        Person person = new Person(nama, alamat);
        return person;
    }
    
    public Person[] getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Person[] pelanggan) {
        this.pelanggan = pelanggan;
    }

    /**
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }
    
}
