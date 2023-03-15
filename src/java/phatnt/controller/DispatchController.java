/////*
//// * To change this license header, choose License Headers in Project Properties.
//// * To change this template file, choose Tools | Templates
//// * and open the template in the editor.
//// */
////package phatnt.controller;
////
////import java.io.IOException;
////import java.util.Properties;
////import javax.servlet.RequestDispatcher;
////import javax.servlet.ServletContext;
////import javax.servlet.ServletException;
////import javax.servlet.annotation.WebServlet;
////import javax.servlet.http.HttpServlet;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////import phatnt.utils.MyApplicationConstants;
////
/////**
//// *
//// * @author HAU NUONG MO HANH
//// */
////@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
////public class DispatchController extends HttpServlet {
//////    private final String LOGIN_PAGE = "login.html";
//////    private final String LOGIN_CONTROLLER = "LoginServlet";
////
//////    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
//////    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
//////    private final String FIRST_REQUEST_CONTROLLER = "ProcessRequestServlet";
//////    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
//////    private final String ADD_TO_CART = "AddItemToCartServlet";
//////    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemFromCartServlet";
//////    private final String VIEW_CART_PAGE = "viewCart2.jsp";
//////    private final String VIEW_CART_CONTROLLER = "ViewCartServlet";
//////    private final String SHOPPING_PAGE_CONTROLLER = "ShoppingPageServlet";
//////    private final String CHECK_OUT_CONTROLLER = "CheckOutServlet";
//////    private final String PAYMENT_CONTROLLER = "PaymentServlet";
////
////    /**
////     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
////     * methods.
////     *
////     * @param request servlet request
////     * @param response servlet response
////     * @throws ServletException if a servlet-specific error occurs
////     * @throws IOException if an I/O error occurs
////     */
////    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        response.setContentType("text/html;charset=UTF-8");
////        ServletContext context = this.getServletContext();
////        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");
////        String url = siteMaps.getProperty(
////                MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
////        String button = request.getParameter("btAction");
////        try {
////            if (button == null) {
////                url = siteMaps.getProperty(
////                        MyApplicationConstants.DispatchFeature.FIRST_LOGIN_CONTROLLER);
////            } else if (button.equals("Login")) {
////                url = siteMaps.getProperty(
////                        MyApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
////            } else if (button.equals("Search")) {
////                url = siteMaps.getProperty(
////                        MyApplicationConstants.SearchFeature.SEARCH_LASTNAME_CONTROLLER);
////            } else if (button.equals("Delete")) {
////                url = siteMaps.getProperty(
////                        MyApplicationConstants.DeleteFeature.DELETE_ACCOUNT_CONTROLLER);
////            } else if (button.equals("Update")) {
////                url = siteMaps.getProperty(
////                        MyApplicationConstants.UpdateFeature.UPDATE_ACCOUNT_CONTROLLER);
////            } else if (button.equals("Remove Selected Item")) {
////                url = siteMaps.getProperty(
////                        MyApplicationConstants.CartProcessFeature.REMOVE_ITEM_FROM_CART);
////            } else if (button.equals("Click here to buy some items")) {
////                url = siteMaps.getProperty(
////                        MyApplicationConstants.ItemsFeature.SHOW_ITEM_CONTROLLER);
////            } else if (button.equals("Buy")) {
////                url = siteMaps.getProperty(
////                        MyApplicationConstants.CartProcessFeature.ADD_ITEM_TO_CART);
////            } else if (button.equals("View your cart")) {
////                url = siteMaps.getProperty(
////                        MyApplicationConstants.CartProcessFeature.VIEW_CART_CONTROLLER);
////            } else if (button.equals("Check out")) {
////                url = siteMaps.getProperty(
////                        MyApplicationConstants.CheckOutFeature.CHECK_OUT_CONTROLLER);
////            } else if (button.equals("Pay")) {
////                url = siteMaps.getProperty(
////                        MyApplicationConstants.CheckOutFeature.PAYMENT_CONTROLLER);
////            } else if (button.equals("Click here to buy more item")){
////                url = siteMaps.getProperty(
////                        MyApplicationConstants.ItemsFeature.SHOW_ITEM_CONTROLLER);
////            } else if(button.equals("Create New Account")){
////                url = siteMaps.getProperty(
////                        MyApplicationConstants.CreateAccountFeature.CREATE_NEW_ACCOUNT_CONTROLLER);
////            }
////        } finally {
////            RequestDispatcher rd = request.getRequestDispatcher(url);
////            rd.forward(request, response);
////        }
////    }
////
////    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
////    /**
////     * Handles the HTTP <code>GET</code> method.
////     *
////     * @param request servlet request
////     * @param response servlet response
////     * @throws ServletException if a servlet-specific error occurs
////     * @throws IOException if an I/O error occurs
////     */
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        processRequest(request, response);
////    }
////
////    /**
////     * Handles the HTTP <code>POST</code> method.
////     *
////     * @param request servlet request
////     * @param response servlet response
////     * @throws ServletException if a servlet-specific error occurs
////     * @throws IOException if an I/O error occurs
////     */
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        processRequest(request, response);
////    }
////
////    /**
////     * Returns a short description of the servlet.
////     *
////     * @return a String containing servlet description
////     */
////    @Override
////    public String getServletInfo() {
////        return "Short description";
////    }// </editor-fold>
////
////}
