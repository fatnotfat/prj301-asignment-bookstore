/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatnt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatnt.cart.CartObject;
import phatnt.product.ProductDAO;
import phatnt.utils.MyApplicationConstants;

/**
 *
 * @author HAU NUONG MO HANH
 */
@WebServlet(name = "RemoveItemFromCartServlet", urlPatterns = {"/RemoveItemFromCartServlet"})
public class RemoveItemFromCartServlet extends HttpServlet {
//    private final String SHOPPING_PAGE_CONTROLLER = "ShoppingPageServlet";
//    private final String VIEW_CART_PAGE = "viewCart2.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");
        String url = siteMaps.getProperty(MyApplicationConstants.ItemsFeature.SHOW_ITEM_CONTROLLER); 
        try {
              //1. Customer goes to his/her cart place
              HttpSession session = request.getSession(false);
              if(session != null){
                  //2. customer take his/her cart
                  CartObject cart = (CartObject) session.getAttribute("CART");
                  if(cart != null){
                      //3.Customer check items 
                      Map<String, Integer> items = cart.getItems();
                      if(items != null){
                          //4.get all selected items
                          String[] removedItems = request.getParameterValues("ckItem");
                          if(removedItems != null){
                              //5 remove each item from cart
                              for (String item : removedItems) {
                                  //6. give back item to shop
                                  ProductDAO dao = new ProductDAO();
                                  cart.removeItemFromCart(item);                             
                              }//end traverse each item 
                              session.setAttribute("CART", cart);
                          }//end removedItems has chooose);
                      }//end items have existed
                  }//end if cart has existed
              }//session is existed
//        }
//        catch(NamingException ex){
//            log("Naming exception :" + ex.getMessage());
//        }catch(SQLException ex){
//            log("SQL Exception :" + ex.getMessage());
        } finally{
            //6. refresh recall view cart again
//            String urlRewriting = "DispatchController?"
//                    + "btAction=View your card";           
//            response.sendRedirect(urlRewriting);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
