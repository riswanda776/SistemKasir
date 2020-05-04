/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class pelanggan extends koneksi {
    public pelanggan(){
        super.setKoneksi();
    }
    
    public DefaultTableModel model = new DefaultTableModel();
    public void simpan(String idpelanggan,String nama_pelanggan,String alamat,String nomor) throws SQLException{
        String sql = "INSERT INTO pelanggan VALUES('"+idpelanggan+"','"+nama_pelanggan+"','"+alamat+"','"+nomor+"')";
        st.executeUpdate(sql);
    }
    public void edit(String idpelanggan,String nama_pelanggan,String alamat,String nomor) throws SQLException{
        String sql = "UPDATE pelanggan set nama_pelanggan = '"+nama_pelanggan+"', alamat = '"+alamat+"', nomor = '"+nomor+"' WHERE id_pelanggan = '"+idpelanggan+"'";
        st.executeUpdate(sql);
    }
    public void hapus(String idpelanggan) throws SQLException{
        String sql = "DELETE FROM pelanggan WHERE id_pelanggan = '"+idpelanggan+"'";
        st.executeUpdate(sql);
    }
    public void tampil(){
        try {
            String sql = "SELECT * FROM pelanggan";
            rs = st.executeQuery(sql);
            String[] kolom = {"ID PELANGGAN","NAMA PELANGGAN","ALAMAT","NO.TELEPON"};
            model.setColumnIdentifiers(kolom);
            while(rs.next()){
                Object[] data = new Object[4];
                data[0] = rs.getString("id_pelanggan");
                data[1] = rs.getString("nama_pelanggan");
                data[2] = rs.getString("alamat");
                data[3] = rs.getString("nomor");
                
                model.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(pelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
