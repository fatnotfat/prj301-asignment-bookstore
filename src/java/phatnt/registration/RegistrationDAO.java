/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatnt.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phatnt.utils.DBHelpers;

/**
 *
 * @author HAU NUONG MO HANH
 */
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT username, lastname "
                        + "FROM Registration "
                        + "WHERE username = ? AND password = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.executeQuery();

                rs = stm.executeQuery();
                
                if (rs.next()) {
                    String lastname = rs.getString("lastname");
                    result = new RegistrationDTO(username, password, lastname, true);
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastName(String searchValue)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE lastname LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto
                            = new RegistrationDTO(username, password,
                                    lastname, role);

                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }
                    this.accounts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            } 
            if (stm != null) {
                stm.close();
            } 
            if (con != null) {
                con.close();
            }
        }
    }
    
    public boolean deleteAccount(String username) 
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        
        try{
            con = DBHelpers.makeConnection();
            String sql = "DELETE FROM Registration "
                    + "WHERE username = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            int effectedRows = stm.executeUpdate();
            if(effectedRows>0){
                result = true;
            }
        }finally{
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return result;
    }
    

    
     public boolean updateAccount(String username, String password, boolean role)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1.connect db
            con = DBHelpers.makeConnection();
            //2.CRUD, tao cau truy van
            String sql = "UPDATE Registration "
                    + "SET password = ?, isAdmin = ? "
                    + "WHERE username = ?";
            
            //3.prepare stm
            
            stm = con.prepareStatement(sql); 
            stm.setString(1, password);
            String isAdmin = "0";
            if(role){
                isAdmin = "1";
            }
           
            stm.setString(2, isAdmin);
            stm.setString(3, username);
            //4.Excute query
            int effectedRows = stm.executeUpdate();
            //5. Process
            if(effectedRows>0){
                result = true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
     
      public boolean createAccount(RegistrationDTO dto)
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBHelpers.makeConnection();
            String sql = "INSERT INTO Registration("
                    + "username, password, lastname, isAdmin"
                    + ") "
                    + "VALUES("
                    + "?, ?, ?, ?"
                    + ")";
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getUsername());
            stm.setString(2, dto.getPassword());
            stm.setString(3, dto.getLastname());
            stm.setBoolean(4, dto.isRole());
            
            int effectedRows = stm.executeUpdate();
            if (effectedRows > 0) {
                result = true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    
}
