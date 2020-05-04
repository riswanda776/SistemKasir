/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import gui.Transaksi;
import static gui.Transaksi.txNofak;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class control_transaksi extends koneksi{
    public control_transaksi(){
        super.setKoneksi();
    }
    
    public DefaultTableModel model = new DefaultTableModel();
    public void tampilPengeluaran(String kodebarang,String namaBarang,int harga,int qty,int total){
        String[] kolom = {"Kode Barang","Nama Barang","Harga","Qty","Total"};
        model.setColumnIdentifiers(kolom);
        if(!(kodebarang.equals(""))){
            Object[] data = new Object[5];
            data[0]= kodebarang;
            data[1]= namaBarang;
            data[2]= harga;
            data[3]=qty;
            data[4]=total;
            model.addRow(data);
        }
    }
    
    public void simpanPenjualan(String no_faktur,String kasir,String id_pelanggan,String namaPelanggan,int diskon,int total,int bayar,int kembali,String tgl) throws SQLException{
        String sql = "INSERT INTO penjualan VALUES('"+no_faktur+"','"+kasir+"','"+id_pelanggan+"','"+namaPelanggan+"','"+diskon+"','"+total+"','"+bayar+"','"+kembali+"','"+tgl+"')";
        st.executeUpdate(sql);
    }
    public void insertBarang(String nofak,String kodeBarang,String namaBarang,int harga,int qty,int total) throws SQLException{
        String sql = "INSERT INTO barangPenjualan VALUES('"+nofak+"','"+kodeBarang+"','"+namaBarang+"','"+harga+"','"+qty+"','"+total+"')";
        st.executeUpdate(sql);
    }
    public void updateStok(int sisa,String kode_barang) throws SQLException{
      
        String sql = "UPDATE masterBarang set stok = '"+sisa+"' WHERE kode_barang = '"+kode_barang+"'";
        st.executeUpdate(sql);
    }
    
    public void noFak(){
        try {
            String sql = "SELECT * FROM penjualan ORDER BY no_faktur DESC";

            rs = st.executeQuery(sql);
            if(rs.next()){
                String nofak = rs.getString("no_faktur").substring(1);
                String AN = ""+ (Integer.parseInt(nofak)+1);
                String nol = "";
                
                if(AN.length()==1){
                    nol = "000";
                }else if(AN.length()==2){
                    nol = "00";
                }else if(AN.length()==3){
                    nol = "0";
                }else if(AN.length()==4){
                    nol = "";
                }
                txNofak.setText("F" + nol + AN);
            }else{
                txNofak.setText("F0001");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
