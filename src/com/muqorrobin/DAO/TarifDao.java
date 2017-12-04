/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.DAO;

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
    
    public TarifDao() {
        try {
            //Scanner scan = new Scanner(new File(TarifDao.class.getResource("/com/muqorrobin/data/ongkosKirim.txt").getFile()));
            Scanner scan = new Scanner(new File("D:/Document/BOOTCAMP JAVA/GITHUB/AppKurir(Fikri)/AppKurir/src/com/muqorrobin/data/ongkosKirim.txt"));
            
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
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TarifDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       

    /**
     * @return the tarifTarif
     */
    public List<Tarif> getTarifTarif() {
        return tarifTarif;
    }
   
}
