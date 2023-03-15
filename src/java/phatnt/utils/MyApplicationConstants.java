/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatnt.utils;

/**
 *
 * @author HAU NUONG MO HANH
 */
public class MyApplicationConstants {

    public class DispatchFeature {

        public static final String LOGIN_PAGE = "loginPage";
        public static final String INVALID_PAGE = "invalidPage";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String FIRST_LOGIN_CONTROLLER = "";
    }

    public class SearchFeature {

        public static final String SEARCH_PAGE = "searchPage";
        public static final String INVALID_PAGE = "invalidPage";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchLastnameController";
    }

    public class DeleteFeature {

        public static final String ERROR_PAGE = "errorPage";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteAccountController";
    }

    public class UpdateFeature {

        public static final String SEARCH_PAGE = "searchPage";
        public static final String ERROR_PAGE = "errorPage";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController";

    }

    public class CreateAccountFeature {
        public static final String CREATE_NEW_ACCOUNT_PAGE1 = "createNewAccountPage1";
        public static final String CREATE_NEW_ACCOUNT_PAGE2 = "createNewAccountPage2";
        public static final String CREATE_NEW_ACCOUNT_CONTROLLER = "createNewAccountController";
        public static final String ERROR_PAGE = "errorPage";
    }

    public class CartProcessFeature {

        public static final String REMOVE_ITEM_FROM_CART = "removeItemFromCartController";
        public static final String ADD_ITEM_TO_CART = "addItemToCartController";
        public static final String VIEW_CART_CONTROLLER = "viewCartController";
        public static final String VIEW_CART_PAGE = "viewCartPage";
    }

    public class ItemsFeature {

        public static final String SHOW_ITEM_CONTROLLER = "shoppingPageController";
        public static final String SHOPPING_PAGE = "shoppingPage";
        public static final String ERROR_PAGE = "errorPage";
    }

    public class CheckOutFeature {

        public static final String CHECK_OUT_CONTROLLER = "checkOutController";
        public static final String PAYMENT_CONTROLLER = "paymentController";
        public static final String ERROR_PAGE = "errorPage";
        public static final String CHECK_OUT_PAGE = "checkOutPage";
    }
}
