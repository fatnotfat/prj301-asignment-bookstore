<%-- 
    Document   : viewCart2
    Created on : Oct 19, 2022, 11:13:22 PM
    Author     : HAU NUONG MO HANH
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Map"%>
<%@page import="phatnt.cart.CartObject"%>
<%@page import="phatnt.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Time!!</title>
    </head>
    <body>
        <%--
            List<ProductDTO> result
                    = (List<ProductDTO>) request.getAttribute("ITEMS_RESULT");
            if (result != null) {
        --%>
        <c:set var="result" value="${requestScope.ITEMS_RESULT}"/>
        <c:if test="${not empty result}">
            <h1>Here are some items for chosen:</h1><br/>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Product Description</th>
                        <th>Product Quantity</th>
                        <th>Product Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%--
                        int count = 0;
                        for (ProductDTO item : result) {
                    --%>
                    <c:forEach var="item" items="${result}" varStatus="counter">
                    <form action="addItemToCartController">
                        <tr>
                            <td>
                                <%--<%= ++count%>--%>
                                ${counter.count}
                                .</td>
                            <td>
                                <%--<%= item.getSku()%>--%>
                                ${item.sku}
                                <input type="hidden" name="txtID" value="${item.sku}" />
                            </td>
                            <td>
                                <%--<%= item.getName()%>--%>
                                ${item.name}
                            </td>
                            <td>
                                <%--<%= item.getDescription()%>--%>
                                ${item.description}
                            </td>
                            <td>
                                <%--<%= item.getQuantity()%>--%>
                                ${item.quantity}
                                <input type="hidden" name="txtQuantity" value="${item.quantity}" />
                            </td>
                            <td>
                                <%--<%= item.getPrice()%>--%>
                                ${item.price}
                            </td>
                            <td>                                 
                                <input type="submit" value="Buy" name="btAction" />
                            </td>                            
                        </tr>

                    </form>
                    <%--
                        }
                    --%>
                </c:forEach>
            </tbody>
        </table>
        <form action="viewCartController">
            <!--<input type="submit" value="View your cart" name="btAction" />-->
            <input type="submit" value="View your cart" name="btAction" />
        </form>                       

        <%--
        } else {
        --%>
    </c:if>
    <c:if test="${empty result}">
        <h2>We are updating more items! We'll comeback soon!!</h2>
    </c:if>
    <%--
        }
    --%>



    <%--
        //1.Customer goes to his/her cart place
        if (session != null) {
            //2.Customer takes his/her cart
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart != null) {
                //3.Customer takes items from cart
                Map<String, Integer> items = cart.getItems();
                if (items != null) {
    --%>
    <c:if test="${not empty sessionScope}">
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty CART}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <h2>Your Cart include:</h2>
                <%--
                    //4.show items
                --%>
                <form action="removeItemFromCartController">

                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Product ID</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%--  int count = 0;
                                for (String key : items.keySet()) {
                            --%>

                            <c:set var="cartList" value="${sessionScope.CART.items}" />

                            <c:forEach var="item" items="${cartList}" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${item.key}
                                        <input type="hidden" name="txtId" value="${item.key}" />
                                    </td>
                                    <td>
                                        ${item.value}
                                        <input type="hidden" name="txtQuantity" value="${item.value}" />
                                    </td>
                                    <td>
                                        <input type="checkbox" name="ckItem" 
                                               value="${item.key}" />
                                    </td>
                                </tr>
                            </c:forEach>




                            <%--
                                }//end get each item from items
                            --%>
                        </tbody>
                    </table>
                    <input type="submit" value="Remove Selected Item" name="btAction" />

                </form>
                <form action="checkOutController">
                    <input type="submit" value="Check out" name="btAction" />
                </form>
                <%--
                                return;
                            }//items have value
                        }//cart has existed
                    }//session has existed
                --%>
            </c:if>
        </c:if>
    </c:if>
    <c:if test="${empty sessionScope.CART.items}">
        <h2>Cart is empty!!! You should buy something first!</h2>
    </c:if>

</body>
</html>
