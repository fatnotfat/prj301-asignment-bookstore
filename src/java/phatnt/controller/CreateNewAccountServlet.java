/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatnt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phatnt.registration.RegistrationCreateError;
import phatnt.registration.RegistrationDAO;
import phatnt.registration.RegistrationDTO;
import phatnt.utils.MyApplicationConstants;

/**
 *
 * @author HAU NUONG MO HANH
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

//    private final String ERROR_PAGE = "createNewAccount.jsp";
//    private final String LOGIN_PAGE = "login.html";
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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        boolean errorFound = false;
//        String url = ERROR_PAGE;

        //get siteMaps
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");
        String url = siteMaps.getProperty(
                MyApplicationConstants.CreateAccountFeature.CREATE_NEW_ACCOUNT_PAGE2);
        RegistrationCreateError errors = new RegistrationCreateError();
        try {
            //1. Check user's error
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                errorFound = true;
                errors.setUsernameLengthError("Username is required input from 6 to 20 characters");
            }

            if (password.trim().length() < 6 || username.trim().length() > 30) {
                errorFound = true;
                errors.setPasswordLengthError("Password is required input from 6 to 20 characters");
            } else if (!confirm.trim().equals(password.trim())) {
                errorFound = true;
                errors.setConfirmNotMatch("Confirm not matched password");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                errorFound = true;
                errors.setFullnameLengthError("Fullname is required input from 6 to 20 characters");
            }
            if (errorFound) {
                //1.1 cache store
                request.setAttribute("CREATE_ERRORS", errors);
                //1.2Show error to user
            } else {
                //2. insert to DB
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto
                        = new RegistrationDTO(username, password, fullname, false);
                boolean result = dao.createAccount(dto);
                if (result) {
                    url = siteMaps.getProperty(
                            MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
                }//create is successfully
                //3. check system's error
            }
        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            log("Create Account SQL" + errMsg);
            if(errMsg.contains("duplicate")){
                errors.setUsernameIsExisted(username + " is Existed");
                request.setAttribute("CREATE_ERRORS", errors);
            }
        } catch (NamingException ex) {
            log("Create Account Naming" + ex.getMessage());
        } finally {
            //4. transfer specified page
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
