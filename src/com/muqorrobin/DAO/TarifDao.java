/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.DAO;

import com.muqorrobin.model.Person;
import com.muqorrobin.model.Tarif;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class TarifDao {
    SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
    Date now = new Date();
    private List<Tarif> tarifTarif = new ArrayList<>();
    private List<String> kotaKota = new ArrayList<>();
    private Person[] pelanggan;
    
    public TarifDao() {
        try {
            Scanner scan;
            scan = new Scanner(new File(TarifDao.class.getResource("/com/muqorrobin/data/OngkosKirim.txt").getFile()));
            while (scan.hasNextLine()) {
                String barisFile = scan.nextLine();
                String[] dataTarif = barisFile.split(",");
                String kota = (dataTarif[0]);
                double tarifReguler = Double.parseDouble(dataTarif[1]);
                double tarifKilat = Double.parseDouble(dataTarif[2]);
                double tarifSDS = Double.parseDouble(dataTarif[3]);
                double tarifONS = Double.parseDouble(dataTarif[4]);
                double tarifHDS = Double.parseDouble(dataTarif[5]);
                Tarif tarif = new Tarif(kota, tarifReguler, tarifKilat, tarifSDS, tarifONS, tarifHDS);
                getTarifTarif().add(tarif);
                getKotaKota().add(kota);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TarifDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    public String[] kotaTujuannya(){
        String[] list = {} ;
        for (Tarif tarif : getTarifTarif()) {
            list = getKotaKota().toArray(new String[getKotaKota().size()]);
        }
        return list;
    }

    /**
     * @return the tarifTarif
     */
    public List<Tarif> getTarifTarif() {
        return tarifTarif;
    }

    /**
     * @return the kotaKota
     */
    public List<String> getKotaKota() {
        return kotaKota;
    }

    /**
     * @return the pelanggan
     */
    public Person[] getPelanggan() {
        return pelanggan;
    }

    /**
     * @param pelanggan the pelanggan to set
     */
    public void setPelanggan(Person[] pelanggan) {
        this.pelanggan = pelanggan;
    }

    
}
