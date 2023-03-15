/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatnt.orders;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import javax.naming.NamingException;
import phatnt.product.ProductDTO;
import phatnt.utils.DBHelpers;

/**
 *
 * @author HAU NUONG MO HANH
 */
public class OrdersDAO implements Serializable {

    public boolean updateOrders(String totalBill)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        PreparedStatement stm2 = null;
        boolean result = false;
        try {
            //1.Open connection
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create sql String
                String sql = "INSERT INTO Orders VALUES(?,?)";
                //3.Prepare statement
                float value = Float.parseFloat(totalBill);
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);
                stm = con.prepareStatement(sql);
                stm.setDate(1, date);
                stm.setFloat(2, value);
                //4. Process
                result = stm.execute();
            }//end if has connection
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

    public int selectCurrentOrderID()
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            //1. Open connection                
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create sql String
                String sql1 = "SELECT TOP 1 id "
                        + "FROM Orders "
                        + "ORDER BY id DESC";
//                String sql1 = "SELECT MAX(id) AS id FROM Orders";
                stm = con.prepareStatement(sql1);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int orderID = rs.getInt("id");
                    result = orderID;
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

    public ProductDTO selectProductByID(String sku)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO dto = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT sku, name, description, price, quantity "
                        + "FROM Product "
                        + "WHERE sku = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, sku);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String skuItem = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    dto = new ProductDTO(sku, name, description, quantity, price);
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
        return dto;
    }
    
    
    
    public boolean updateOrderDetails(String sku, int quantity)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1. Open connection                
            con = DBHelpers.makeConnection();
            if (con != null) {
//                2. Create sql String
                        int orderId = selectCurrentOrderID();
                        ProductDTO dto = selectProductByID(sku);
                        float price = dto.getPrice();
                        float sumOf = price * quantity;
                        String sql = "INSERT INTO OrdersDetail("
                                + "sku, "
                                + "orderID, quantity, "
                                + "price, total) "
                                + "VALUES("
                                + "?, "
                                + "?, ?, "
                                + "?, ?)";
                        stm = con.prepareStatement(sql);
                        stm.setString(1, sku);
                        stm.setInt(2, orderId);
                        stm.setInt(3, quantity);
                        stm.setFloat(4, price);
                        stm.setFloat(5, sumOf);
                        int effectedRows = stm.executeUpdate();
                        if (effectedRows > 0) {
                            result = true;
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

//    public boolean updateOrderDetails(String sku, int quantity)
//            throws NamingException, SQLException {
//        Connection con = null;
//        PreparedStatement stm1 = null;
//        PreparedStatement stm2 = null;
//        PreparedStatement stm3 = null;
//        ResultSet rs = null;
//        boolean result = false;
//        try {
//            //1. Open connection                
//            con = DBHelpers.makeConnection();
//            if (con != null) {
////                2. Create sql String
//                String sql1 = "SELECT TOP 1 id "
//                        + "FROM Orders "
//                        + "ORDER BY id DESC";
////                String sql1 = "SELECT MAX(id) FROM Orders";
//                stm1 = con.prepareStatement(sql1);
//                rs = stm1.executeQuery();
//                if (rs.next()) {
//                    int orderID = rs.getInt("id");
//                    String sql2 = "SELECT sku, name, description, price, quantity "
//                            + "FROM Product "
//                            + "WHERE sku = ?";
//                    stm2 = con.prepareStatement(sql2);
//                    stm2.setString(1, sku);
//                    rs = stm2.executeQuery();
//                    if (rs.next()) {
//                        float price = rs.getFloat("price");
//                        float sumOf = price * quantity;
//                        String sql3 = "INSERT INTO OrdersDetail("
//                                + "sku, "
//                                + "orderID, quantity, "
//                                + "price, total) "
//                                + "VALUES("
//                                + "?, "
//                                + "?, ?, "
//                                + "?, ?)";
//                        stm3 = con.prepareStatement(sql3);
//                        stm3.setString(1, sku);
//                        stm3.setInt(2, orderID);
//                        stm3.setInt(3, quantity);
//                        stm3.setFloat(4, price);
//                        stm3.setFloat(5, sumOf);
//                        int effectedRows = stm3.executeUpdate();
//                        if (effectedRows > 0) {
//                            result = true;
//                        }
//                    }
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm3 != null) {
//                stm3.close();
//            }
//            if (stm2 != null) {
//                stm2.close();
//            }
//            if (stm1 != null) {
//                stm1.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return result;
//    }
}
