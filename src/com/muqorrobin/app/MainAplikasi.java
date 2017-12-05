/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.app;

import com.muqorrobin.DAO.AddressDao;
import com.muqorrobin.DAO.BarangDao;
import com.muqorrobin.DAO.BiayaDao;
import com.muqorrobin.DAO.OrderDao;
import com.muqorrobin.DAO.PaketDao;
import com.muqorrobin.DAO.PersonDao;
import com.muqorrobin.DAO.TarifDao;
import com.muqorrobin.model.Address;
import com.muqorrobin.model.Barang;
import com.muqorrobin.model.Order;
import com.muqorrobin.model.Paket;
import com.muqorrobin.model.Person;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author M Fikri Muqorrobin
 */
public class MainAplikasi extends javax.swing.JFrame {

    Date sekarang = new Date();
    SimpleDateFormat sd = new SimpleDateFormat("dd-MMMM-yyyy");
    OrderDao orderDao = new OrderDao();
    PersonDao personDao = new PersonDao();
    AddressDao addressDao = new AddressDao();
    BiayaDao biayaDao = new BiayaDao();
    PaketDao paketDao = new PaketDao();
    BarangDao barangDao = new BarangDao();
    TarifDao tarifDao = new TarifDao();

    /**
     * Creates new form KurirForm
     */
    public MainAplikasi() {
        initComponents();
        this.setLocationRelativeTo(null);
        initLoad();
        dataKota();
    }
    
    private void dataKota(){
        for (String s : tarifDao.getKotaKota()) {
            comboKotaPenerima.addItem(s);
        }
    }

    private void initLoad() {
        inNomorPaket.setText("" + paketDao.getNoPaket());
        inTanggalKirim.setDate(sekarang);
        labelTanggal.setText("" + sd.format(sekarang));
    }

    private void inputDataPerson() {
        Address alamatPengirim = addressDao.createAddress(inAlamatPengirim.getText(), inProvinsi.getText(), comboKota.getSelectedItem().toString(), Integer.parseInt(inPosPengirim.getText()), inTeleponPengirim.getText());
        Address alamatPenerima = addressDao.createAddress(inAlamatPenerima.getText(), inProvinsiPenerima.getText(), comboKotaPenerima.getSelectedItem().toString(), Integer.parseInt(inPosPenerima.getText()), inTeleponPenerima.getText());
        Person pengirim = personDao.createPerson(inNamaPengirim.getText(), alamatPengirim);
        Person penerima = personDao.createPerson(inNamaPenerima.getText(), alamatPenerima);
        Person[] persons = {pengirim, penerima};
        personDao.setPelanggan(persons);
    }

    private void ambilDataPerson() {
        Person[] persons = personDao.getPelanggan();
        inNomorPaket.setText(persons[0].getNama());
        inKotaTujuan.setText(persons[1].getAlamat().getKota());
    }

    private boolean getValueAsuransi() {
        boolean isAsuransi = false;
        if (asuransiGroup.isSelected(asuransiYa.getModel())) {
            isAsuransi = true;
        } else if (asuransiGroup.isSelected(asuransiTidak.getModel())) {
            isAsuransi = false;
        }
        return isAsuransi;
    }

    private void inputDataOrder() {
        Barang barang = barangDao.insertBarang(inNamaBarang.getText(), Integer.parseInt(inBerat.getText()), Double.parseDouble(inHargaBarang.getText()));
        Paket paket = paketDao.insertPaket(Integer.parseInt(inNomorPaket.getText()), inKotaTujuan.getText(), sekarang, comboJenisLayanan.getSelectedItem().toString(), getValueAsuransi(), barang);
        Order order = orderDao.insertOrder(personDao.getPelanggan(), paket);
        hitungBiaya(order);
        buttonCetak.setEnabled(true);
    }

    private void hitungBiaya(Order order) {
        biayaDao.setOrder(order);
        biayaDao.setOngkirAwal(comboJenisLayanan.getSelectedItem().toString());
        inTotalBiaya.setText("Rp. " + biayaDao.getTotalBiaya());
    }
    
    private void lihatResi(){
        formResi.pack();
        formResi.setVisible(true);
        
        Person[] p = personDao.getPelanggan();
        labelNamaPengirim.setText(p[0].getNama());
        labelAlamatPengirim.setText(p[0].getAlamat().getAlamatLengkap());
        labelKotaPengirim.setText(p[0].getAlamat().getKota());
        labelProvinsiPengirim.setText(p[0].getAlamat().getProvinsi());
        labelPosPengirim.setText(""+p[0].getAlamat().getKodePos());
        labelTeleponPengirim.setText(""+p[0].getAlamat().getTelepon());
        
        labelNamaPenerima.setText(p[1].getNama());
        labelAlamatPenerima.setText(p[1].getAlamat().getAlamatLengkap());
        labelKotaPenerima.setText(p[1].getAlamat().getKota());
        labelProvinsiPenerima.setText(p[1].getAlamat().getProvinsi());
        labelPosPenerima.setText(""+p[1].getAlamat().getKodePos());
        labelTeleponPenerima.setText(""+p[1].getAlamat().getTelepon());
        
        labelResi.setText(labelResi.getText()+""+biayaDao.getOrder().getPaket().getNomorPaket());
        labelNamaBarang.setText(biayaDao.getOrder().getPaket().getBarang().getNamaBarang());
        labelJenisBarang.setText(comboJenisBarang.getSelectedItem().toString());
        labelBerat.setText(""+biayaDao.getOrder().getPaket().getBarang().getBeratBarang());
        labelJenisLayanan.setText(comboJenisLayanan.getSelectedItem().toString());
        labelAsuransi.setText(""+biayaDao.getOrder().getPaket().isAsuransi());
        labelBiaya.setText("Rp. "+biayaDao.getTotalBiaya());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        asuransiGroup = new javax.swing.ButtonGroup();
        pembayaranGroup = new javax.swing.ButtonGroup();
        formResi = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        labelResi = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        labelNamaPengirim = new javax.swing.JLabel();
        labelAlamatPengirim = new javax.swing.JLabel();
        labelKotaPengirim = new javax.swing.JLabel();
        labelProvinsiPengirim = new javax.swing.JLabel();
        labelPosPengirim = new javax.swing.JLabel();
        labelTeleponPengirim = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        labelNamaPenerima = new javax.swing.JLabel();
        labelAlamatPenerima = new javax.swing.JLabel();
        labelKotaPenerima = new javax.swing.JLabel();
        labelProvinsiPenerima = new javax.swing.JLabel();
        labelPosPenerima = new javax.swing.JLabel();
        labelTeleponPenerima = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        labelNamaBarang = new javax.swing.JLabel();
        labelJenisBarang = new javax.swing.JLabel();
        labelBerat = new javax.swing.JLabel();
        labelJenisLayanan = new javax.swing.JLabel();
        labelAsuransi = new javax.swing.JLabel();
        labelBiaya = new javax.swing.JLabel();
        panelJudul = new javax.swing.JPanel();
        labelJudul = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelWaktu = new javax.swing.JLabel();
        labelTanggal = new javax.swing.JLabel();
        panelDataPaket = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        inNomorPaket = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        inKotaTujuan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        comboJenisBarang = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        inNamaBarang = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        inBerat = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        inP = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        inL = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        inT = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        inTanggalKirim = new com.toedter.calendar.JDateChooser();
        comboJenisLayanan = new javax.swing.JComboBox<>();
        asuransiYa = new javax.swing.JRadioButton();
        asuransiTidak = new javax.swing.JRadioButton();
        inHargaBarang = new javax.swing.JFormattedTextField();
        hitungButton = new javax.swing.JButton();
        inTotalBiaya = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        radioCash = new javax.swing.JRadioButton();
        radioDebit = new javax.swing.JRadioButton();
        radioCC = new javax.swing.JRadioButton();
        buttonCetak = new javax.swing.JButton();
        panelPengirim = new javax.swing.JPanel();
        inNamaPengirim = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inAlamatPengirim = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        inTeleponPengirim = new javax.swing.JFormattedTextField();
        comboKota = new javax.swing.JComboBox<>();
        inPosPengirim = new javax.swing.JTextField();
        inProvinsi = new javax.swing.JTextField();
        panelPengirim1 = new javax.swing.JPanel();
        inNamaPenerima = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        inAlamatPenerima = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        inTeleponPenerima = new javax.swing.JFormattedTextField();
        comboKotaPenerima = new javax.swing.JComboBox<>();
        inPosPenerima = new javax.swing.JTextField();
        inProvinsiPenerima = new javax.swing.JTextField();

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, labelResi, org.jdesktop.beansbinding.ELProperty.create("${text}"), formResi, org.jdesktop.beansbinding.BeanProperty.create("title"));
        bindingGroup.addBinding(binding);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 102)));

        labelResi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelResi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelResi.setText("Nomor Resi : JKTDEC17");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelResi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelResi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 102)));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Nama Pengirim ");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Provinsi");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Nomor Telepon");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Alamat");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Kota");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Kode Pos");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addComponent(jLabel33)
                .addGap(18, 18, 18)
                .addComponent(jLabel30)
                .addGap(18, 18, 18)
                .addComponent(jLabel34)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelNamaPengirim.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNamaPengirim.setText("Nama");

        labelAlamatPengirim.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelAlamatPengirim.setText("Alamat");

        labelKotaPengirim.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelKotaPengirim.setText("jLabel37");

        labelProvinsiPengirim.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelProvinsiPengirim.setText("jLabel38");

        labelPosPengirim.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelPosPengirim.setText("jLabel39");

        labelTeleponPengirim.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelTeleponPengirim.setText("jLabel40");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNamaPengirim)
                    .addComponent(labelAlamatPengirim)
                    .addComponent(labelKotaPengirim)
                    .addComponent(labelProvinsiPengirim)
                    .addComponent(labelPosPengirim)
                    .addComponent(labelTeleponPengirim))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNamaPengirim)
                .addGap(18, 18, 18)
                .addComponent(labelAlamatPengirim)
                .addGap(18, 18, 18)
                .addComponent(labelKotaPengirim)
                .addGap(18, 18, 18)
                .addComponent(labelProvinsiPengirim)
                .addGap(18, 18, 18)
                .addComponent(labelPosPengirim)
                .addGap(18, 18, 18)
                .addComponent(labelTeleponPengirim)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 102)));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Nama Penerima ");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Provinsi");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Nomor Telepon");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Alamat");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("Kota");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Kode Pos");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addGap(18, 18, 18)
                .addComponent(jLabel44)
                .addGap(18, 18, 18)
                .addComponent(jLabel45)
                .addGap(18, 18, 18)
                .addComponent(jLabel42)
                .addGap(18, 18, 18)
                .addComponent(jLabel46)
                .addGap(18, 18, 18)
                .addComponent(jLabel43)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelNamaPenerima.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNamaPenerima.setText("Nama");

        labelAlamatPenerima.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelAlamatPenerima.setText("Alamat");

        labelKotaPenerima.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelKotaPenerima.setText("jLabel37");

        labelProvinsiPenerima.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelProvinsiPenerima.setText("jLabel38");

        labelPosPenerima.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelPosPenerima.setText("jLabel39");

        labelTeleponPenerima.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelTeleponPenerima.setText("jLabel40");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNamaPenerima)
                    .addComponent(labelAlamatPenerima)
                    .addComponent(labelKotaPenerima)
                    .addComponent(labelProvinsiPenerima)
                    .addComponent(labelPosPenerima)
                    .addComponent(labelTeleponPenerima))
                .addContainerGap(167, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNamaPenerima)
                .addGap(18, 18, 18)
                .addComponent(labelAlamatPenerima)
                .addGap(18, 18, 18)
                .addComponent(labelKotaPenerima)
                .addGap(18, 18, 18)
                .addComponent(labelProvinsiPenerima)
                .addGap(18, 18, 18)
                .addComponent(labelPosPenerima)
                .addGap(18, 18, 18)
                .addComponent(labelTeleponPenerima)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 102)));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setText("Nama Barang");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("Berat (Kg)");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("Jenis Layanan");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("Asuransi");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setText("Jenis Barang");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Biaya");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56)
                    .addComponent(jLabel57)
                    .addComponent(jLabel35))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53)
                .addGap(18, 18, 18)
                .addComponent(jLabel57)
                .addGap(18, 18, 18)
                .addComponent(jLabel54)
                .addGap(18, 18, 18)
                .addComponent(jLabel55)
                .addGap(18, 18, 18)
                .addComponent(jLabel56)
                .addGap(18, 18, 18)
                .addComponent(jLabel35)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelNamaBarang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNamaBarang.setText("jLabel58");

        labelJenisBarang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelJenisBarang.setText("jLabel59");

        labelBerat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelBerat.setText("jLabel60");

        labelJenisLayanan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelJenisLayanan.setText("jLabel61");

        labelAsuransi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelAsuransi.setText("jLabel62");

        labelBiaya.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelBiaya.setText("jLabel36");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNamaBarang)
                    .addComponent(labelJenisBarang)
                    .addComponent(labelBerat)
                    .addComponent(labelJenisLayanan)
                    .addComponent(labelAsuransi)
                    .addComponent(labelBiaya))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNamaBarang)
                .addGap(18, 18, 18)
                .addComponent(labelJenisBarang)
                .addGap(18, 18, 18)
                .addComponent(labelBerat)
                .addGap(18, 18, 18)
                .addComponent(labelJenisLayanan)
                .addGap(18, 18, 18)
                .addComponent(labelAsuransi)
                .addGap(18, 18, 18)
                .addComponent(labelBiaya)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout formResiLayout = new javax.swing.GroupLayout(formResi.getContentPane());
        formResi.getContentPane().setLayout(formResiLayout);
        formResiLayout.setHorizontalGroup(
            formResiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formResiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formResiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(formResiLayout.createSequentialGroup()
                        .addGroup(formResiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        formResiLayout.setVerticalGroup(
            formResiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formResiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formResiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formResiLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, labelJudul, org.jdesktop.beansbinding.ELProperty.create("${text}"), this, org.jdesktop.beansbinding.BeanProperty.create("title"));
        bindingGroup.addBinding(binding);

        panelJudul.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(102, 102, 102)));

        labelJudul.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelJudul.setText("APLIKASI PENGIRIMAN BARANG");

        javax.swing.GroupLayout panelJudulLayout = new javax.swing.GroupLayout(panelJudul);
        panelJudul.setLayout(panelJudulLayout);
        panelJudulLayout.setHorizontalGroup(
            panelJudulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJudulLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelJudul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelJudulLayout.setVerticalGroup(
            panelJudulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJudulLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelJudul, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(102, 102, 102)));

        labelWaktu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelWaktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelWaktu.setText("Tanggal");

        labelTanggal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelTanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTanggal.setText("Hari dan Tanggal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelWaktu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelWaktu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelTanggal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDataPaket.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(102, 102, 102)), "Data Paket"));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(102, 102, 102)));

        jLabel8.setText("Nomor Paket");

        inNomorPaket.setEditable(false);

        jLabel9.setText("Kota Tujuan");

        inKotaTujuan.setEditable(false);

        jLabel10.setText("Jenis Barang");

        comboJenisBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dokumen", "Elektronik", "Makanan", "Aksesoris", "Lain-lain" }));

        jLabel11.setText("Nama Barang");

        jLabel12.setText("Berat Barang");

        jLabel13.setText("Dimensi");

        jLabel14.setText("Panjang");

        jLabel15.setText("Lebar");

        jLabel16.setText("Tinggi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inNomorPaket)
                    .addComponent(inKotaTujuan)
                    .addComponent(comboJenisBarang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inNamaBarang)
                    .addComponent(inBerat)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inNomorPaket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inKotaTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboJenisBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inBerat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(inP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(inL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(inT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(102, 102, 102)));

        jLabel17.setText("Tanggal Kirim");

        jLabel18.setText("Jenis Layanan");

        jLabel19.setText("Asuransi");

        jLabel20.setText("Harga Barang");

        comboJenisLayanan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reguler", "Kilat", "SDS", "ONS", "HDS" }));

        asuransiGroup.add(asuransiYa);
        asuransiYa.setText("Ya");
        asuransiYa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                asuransiYaItemStateChanged(evt);
            }
        });

        asuransiGroup.add(asuransiTidak);
        asuransiTidak.setText("Tidak");
        asuransiTidak.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                asuransiTidakItemStateChanged(evt);
            }
        });

        inHargaBarang.setEditable(false);

        hitungButton.setText("Hitung Biaya");
        hitungButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungButtonActionPerformed(evt);
            }
        });

        inTotalBiaya.setEditable(false);
        inTotalBiaya.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inTotalBiaya.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Total Biaya");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inTanggalKirim, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addComponent(comboJenisLayanan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hitungButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inHargaBarang, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(asuransiYa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(asuransiTidak))
                            .addComponent(jLabel20))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(inTotalBiaya, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inTanggalKirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboJenisLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(asuransiYa)
                    .addComponent(asuransiTidak))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hitungButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inTotalBiaya)
                .addGap(45, 45, 45))
        );

        jLabel21.setText("Metode Pembayaran :");

        pembayaranGroup.add(radioCash);
        radioCash.setText("Cash");
        radioCash.setEnabled(false);

        pembayaranGroup.add(radioDebit);
        radioDebit.setText("Debit");
        radioDebit.setEnabled(false);

        pembayaranGroup.add(radioCC);
        radioCC.setText("CC");
        radioCC.setEnabled(false);

        buttonCetak.setText("Cetak Resi");
        buttonCetak.setEnabled(false);
        buttonCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDataPaketLayout = new javax.swing.GroupLayout(panelDataPaket);
        panelDataPaket.setLayout(panelDataPaketLayout);
        panelDataPaketLayout.setHorizontalGroup(
            panelDataPaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataPaketLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDataPaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDataPaketLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radioCash)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioDebit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioCC)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDataPaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonCetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDataPaketLayout.setVerticalGroup(
            panelDataPaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataPaketLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDataPaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(panelDataPaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(radioCash)
                    .addComponent(radioDebit)
                    .addComponent(radioCC)
                    .addComponent(buttonCetak))
                .addGap(4, 4, 4))
        );

        panelPengirim.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Data Pengirim"));

        jLabel2.setText("Nama");

        jLabel3.setText("Alamat");

        inAlamatPengirim.setColumns(20);
        inAlamatPengirim.setRows(5);
        jScrollPane1.setViewportView(inAlamatPengirim);

        jLabel4.setText("Kota");

        jLabel5.setText("Provinsi");

        jLabel6.setText("Kode Pos");

        jLabel7.setText("Telepon");

        comboKota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jakarta Barat", "Jakarta Timur", "Jakarta Selatan", "Jakarta Pusat" }));

        javax.swing.GroupLayout panelPengirimLayout = new javax.swing.GroupLayout(panelPengirim);
        panelPengirim.setLayout(panelPengirimLayout);
        panelPengirimLayout.setHorizontalGroup(
            panelPengirimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPengirimLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPengirimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inNamaPengirim)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(inTeleponPengirim)
                    .addComponent(comboKota, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inPosPengirim)
                    .addGroup(panelPengirimLayout.createSequentialGroup()
                        .addGroup(panelPengirimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(inProvinsi))
                .addContainerGap())
        );
        panelPengirimLayout.setVerticalGroup(
            panelPengirimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPengirimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inNamaPengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inPosPengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inTeleponPengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelPengirim1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Data Penerima"));

        jLabel22.setText("Nama");

        jLabel23.setText("Alamat");

        inAlamatPenerima.setColumns(20);
        inAlamatPenerima.setRows(5);
        jScrollPane2.setViewportView(inAlamatPenerima);

        jLabel24.setText("Kota");

        jLabel25.setText("Provinsi");

        jLabel28.setText("Kode Pos");

        jLabel29.setText("Telepon");

        comboKotaPenerima.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboKotaPenerimaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelPengirim1Layout = new javax.swing.GroupLayout(panelPengirim1);
        panelPengirim1.setLayout(panelPengirim1Layout);
        panelPengirim1Layout.setHorizontalGroup(
            panelPengirim1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPengirim1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPengirim1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inNamaPenerima)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(inTeleponPenerima)
                    .addComponent(comboKotaPenerima, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inPosPenerima)
                    .addGroup(panelPengirim1Layout.createSequentialGroup()
                        .addGroup(panelPengirim1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(inProvinsiPenerima))
                .addContainerGap())
        );
        panelPengirim1Layout.setVerticalGroup(
            panelPengirim1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPengirim1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inNamaPenerima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboKotaPenerima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inProvinsiPenerima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inPosPenerima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inTeleponPenerima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJudul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelPengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelPengirim1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(panelDataPaket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelPengirim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPengirim1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDataPaket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCetakActionPerformed
        // TODO add your handling code here:
        lihatResi();
    }//GEN-LAST:event_buttonCetakActionPerformed

    private void comboKotaPenerimaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboKotaPenerimaItemStateChanged
        // TODO add your handling code here:
        inKotaTujuan.setText(comboKotaPenerima.getSelectedItem().toString());
    }//GEN-LAST:event_comboKotaPenerimaItemStateChanged

    private void hitungButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungButtonActionPerformed
        // TODO add your handling code here:
        try {
            inputDataPerson();
            inputDataOrder();
            radioCC.setEnabled(true);
            radioCash.setEnabled(true);
            radioDebit.setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(inT, "Error : "+ex);
        }

    }//GEN-LAST:event_hitungButtonActionPerformed

    private void asuransiYaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_asuransiYaItemStateChanged
        // TODO add your handling code here:
        inHargaBarang.setEditable(true);
    }//GEN-LAST:event_asuransiYaItemStateChanged

    private void asuransiTidakItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_asuransiTidakItemStateChanged
        // TODO add your handling code here:
        inHargaBarang.setEditable(false);
        inHargaBarang.setText("0.0");
    }//GEN-LAST:event_asuransiTidakItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainAplikasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainAplikasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainAplikasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainAplikasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainAplikasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup asuransiGroup;
    private javax.swing.JRadioButton asuransiTidak;
    private javax.swing.JRadioButton asuransiYa;
    private javax.swing.JButton buttonCetak;
    private javax.swing.JComboBox<String> comboJenisBarang;
    private javax.swing.JComboBox<String> comboJenisLayanan;
    private javax.swing.JComboBox<String> comboKota;
    private javax.swing.JComboBox<String> comboKotaPenerima;
    private javax.swing.JDialog formResi;
    private javax.swing.JButton hitungButton;
    private javax.swing.JTextArea inAlamatPenerima;
    private javax.swing.JTextArea inAlamatPengirim;
    private javax.swing.JTextField inBerat;
    private javax.swing.JFormattedTextField inHargaBarang;
    private javax.swing.JTextField inKotaTujuan;
    private javax.swing.JTextField inL;
    private javax.swing.JTextField inNamaBarang;
    private javax.swing.JTextField inNamaPenerima;
    private javax.swing.JTextField inNamaPengirim;
    private javax.swing.JTextField inNomorPaket;
    private javax.swing.JTextField inP;
    private javax.swing.JTextField inPosPenerima;
    private javax.swing.JTextField inPosPengirim;
    private javax.swing.JTextField inProvinsi;
    private javax.swing.JTextField inProvinsiPenerima;
    private javax.swing.JTextField inT;
    private com.toedter.calendar.JDateChooser inTanggalKirim;
    private javax.swing.JFormattedTextField inTeleponPenerima;
    private javax.swing.JFormattedTextField inTeleponPengirim;
    private javax.swing.JFormattedTextField inTotalBiaya;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAlamatPenerima;
    private javax.swing.JLabel labelAlamatPengirim;
    private javax.swing.JLabel labelAsuransi;
    private javax.swing.JLabel labelBerat;
    private javax.swing.JLabel labelBiaya;
    private javax.swing.JLabel labelJenisBarang;
    private javax.swing.JLabel labelJenisLayanan;
    private javax.swing.JLabel labelJudul;
    private javax.swing.JLabel labelKotaPenerima;
    private javax.swing.JLabel labelKotaPengirim;
    private javax.swing.JLabel labelNamaBarang;
    private javax.swing.JLabel labelNamaPenerima;
    private javax.swing.JLabel labelNamaPengirim;
    private javax.swing.JLabel labelPosPenerima;
    private javax.swing.JLabel labelPosPengirim;
    private javax.swing.JLabel labelProvinsiPenerima;
    private javax.swing.JLabel labelProvinsiPengirim;
    private javax.swing.JLabel labelResi;
    private javax.swing.JLabel labelTanggal;
    private javax.swing.JLabel labelTeleponPenerima;
    private javax.swing.JLabel labelTeleponPengirim;
    private javax.swing.JLabel labelWaktu;
    private javax.swing.JPanel panelDataPaket;
    private javax.swing.JPanel panelJudul;
    private javax.swing.JPanel panelPengirim;
    private javax.swing.JPanel panelPengirim1;
    private javax.swing.ButtonGroup pembayaranGroup;
    private javax.swing.JRadioButton radioCC;
    private javax.swing.JRadioButton radioCash;
    private javax.swing.JRadioButton radioDebit;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
