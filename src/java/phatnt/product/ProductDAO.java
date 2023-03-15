/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatnt.product;

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
public class ProductDAO implements Serializable {

    private List<ProductDTO> items;

    public List<ProductDTO> getItems() {
        return items;
    }


    
    

    public void getItemsFromInventory()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Open connection
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Prepare sql String
                String sql = "SELECT sku, name, description, quantity, price "
                        + "FROM Product "
                        + "WHERE status = 1";
                //3. Prepare statement
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("quantity")) > 0) {
                        String sku = rs.getString("sku");
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        int quantity = rs.getInt("quantity");
                        float price = rs.getFloat("price");

                        ProductDTO dto
                                = new ProductDTO(sku, name, description, quantity, price);
                        if (this.items == null) {
                            items = new ArrayList<>();
                        }
                        this.items.add(dto);
                    }
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

    public boolean updateItemsInInventory(String id, int quantityBuy, int quantityInInventory)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. open connection
            con = DBHelpers.makeConnection();
            //2. check if connection existed
            if (con != null) {
                //3.create sql String
                String sql = "UPDATE Product "
                        + "SET quantity = ? "
                        + "WHERE sku LIKE ? ";
                //4. prepare statement
                stm = con.prepareStatement(sql);
                stm.setString(2, id);
                int tmp = quantityInInventory - quantityBuy;
                stm.setInt(1, tmp);
                result = stm.execute();
                
                //*check if quantity == 0
                if(tmp == 0){
                    String sql1 = "UPDATE Product "
                        + "SET status = 0 "
                        + "WHERE sku LIKE ? ";
                    stm = con.prepareStatement(sql1);
                    stm.setString(1, id);
                    stm.execute();
                }
                
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
    
    
//    
//    public boolean giveBackRemoveItem(String sku, int quantity)
//            throws NamingException, SQLException{
//        Connection con = null;
//        PreparedStatement stm = null;
//        boolean result = false;
//        int effectedRow = 0;
//        try{
//            //1. open connection
//            con = DBHelpers.makeConnection();
//            if(con!= null){
//                //2. Create sql String
//                String sql1 = "UPDATE Product "
//                        + "SET quantity = (SELECT quantity FROM Product WHERE sku = ?) + ? "
//                        + "WHERE sku = ?";
//                //3. Prepare statement 
//                stm = con.prepareStatement(sql1);
//                stm.setString(1, sku);
//                stm.setInt(2, quantity);
//                stm.setString(3, sku);
//                effectedRow = stm.executeUpdate();
//                if(effectedRow > 0){
//                    result = true;
//                    String sql2 = "UPDATE Product "
//                            + "SET status = 1 "
//                            + "WHERE sku = ?";
//                    stm = con.prepareStatement(sql2);
//                    stm.setString(1, sku);
//                    stm.execute();
//                }//end if has at least 1 row effected
//            }//end if con is existed
//        }finally{
//            if(stm!= null){
//                stm.close();
//            }
//            
//            if(con != null){
//                con.close();
//            }
//        }
//        return result;
//    }
//    
    
    
    private List<ProductDTO> selectedProduct;

    public List<ProductDTO> getSelectedProduct() {
        return selectedProduct;
    }


    
    
    public void checkOut(String id)
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try{
            //Open Connection 
            con = DBHelpers.makeConnection();
            if(con != null){
                //2. Create sql String
                String sql = "SELECT sku, name, price, quantity, description "
                        + "FROM Product "
                        + "WHERE sku = ?";
                //3. Prepare stament
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                while(rs.next()){
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String description = rs.getString("description");
                    ProductDTO dto = new ProductDTO(sku, name, description, quantity, price);
                    if(this.selectedProduct == null){
                        this.selectedProduct = new ArrayList<>();
                    }
                    this.selectedProduct.add(dto);
                }//end if rs has value
            }//end if con has existed
        }finally{
            if(rs!= null){
                rs.close();
            }            
            if(stm!=null){
                stm.close();
            }
            if(con!= null){
                con.close();
            }
        }
    }
    
    
}
