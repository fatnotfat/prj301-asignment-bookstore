/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatnt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
import phatnt.orders.OrdersDAO;
import phatnt.product.ProductDAO;
import phatnt.product.ProductDTO;
import phatnt.utils.MyApplicationConstants;

/**
 *
 * @author HAU NUONG MO HANH
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

//    private final String SHOPPING_CONTROLLER = "ShoppingPageServlet";

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String totalBill = request.getParameter("txtTotal");

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");

        HttpSession session = request.getSession();
        CartObject cart = (CartObject) session.getAttribute("CART");
        List<ProductDTO> list = (List<ProductDTO>) session.getAttribute("CHECK_OUT");
        if (cart != null) {
//            String url = SHOPPING_CONTROLLER;
            String url = siteMaps.getProperty(
                    MyApplicationConstants.ItemsFeature.SHOW_ITEM_CONTROLLER);
            try {
                //Get item from cart
                Map<String, Integer> items = cart.getItems();

                //1.Call DAO
                OrdersDAO ordersDao = new OrdersDAO();
                ProductDAO productDao = new ProductDAO();
                boolean result = ordersDao.updateOrders(totalBill);
                for (String skuItem : items.keySet()) {
                    //2.1 get item from cart
                    //2.2 Update item in inventory
                    for (ProductDTO productDTO : list) {
                        if (productDTO.getSku().equals(skuItem)) {

                            if (productDTO.getQuantity() >= items.get(skuItem)) {
                                //2. update order and orderdetail
                                ordersDao.updateOrderDetails(skuItem, items.get(skuItem));
                                productDao.updateItemsInInventory(
                                        productDTO.getSku(), items.get(skuItem), productDTO.getQuantity());
                            } else {
                                result = false;
//                                session.invalidate();
                                request.setAttribute("ERROR", result);
                                return;
                            }
                        }

                    }
                }

                session.invalidate();
                request.setAttribute("SIGNAL", result);
            } catch (NamingException ex) {
                log("Naming exception :" + ex.getMessage());
            } catch (SQLException ex) {
                log("SQL Exception :" + ex.getMessage());
            } finally {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
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
