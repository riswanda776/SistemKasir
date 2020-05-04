/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Acer
 */
public class control_loginn extends koneksi{
    public control_loginn(){
        super.setKoneksi();
    }
    public ResultSet login(String username,String password) throws SQLException{
        String sql ="SELECT * FROM user where username = '"+username+"' AND password = '"+password+"'";
        rs = st.executeQuery(sql);
        return rs;
    }
}
