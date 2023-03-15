<%-- 
    Document   : checkOutPage
    Created on : Oct 23, 2022, 11:08:02 PM
    Author     : HAU NUONG MO HANH
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check out page</title>
    </head>
    <body>

        <form action="paymentController">
            <c:set var="list" value="${sessionScope.CHECK_OUT}"/>
            <c:if test="${not empty list}">
                <h1>Check out: </h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantum</th>
                            <th>Price</th>
                            <th>Sum amount</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="tmp" value="${0}"/>
                        <c:forEach var="item" items="${list}" varStatus="counter"> 
                            <c:set var="cartList" value="${sessionScope.CART.items}" />
                            <c:forEach var="selectedItem" items="${cartList}">
                                <c:if test="${item.sku eq selectedItem.key }">

                                    <tr>
                                        <td>
                                            ${counter.count}
                                        </td>
                                        <td>
                                            ${item.name}
                                            <!--<input type="hidden" name="txtName" value="${item.name}" />-->
                                        </td>
                                        <td>
                                            ${selectedItem.value}
                                            <!--<input type="hidden" name="txtQuantity" value="${selectedItem.value}" />-->
                                        </td>
                                        <td>
                                            ${item.price}
                                            <!--<input type="hidden" name="txtPrice" value="${item.price}" />-->
                                        </td>
                                        <td>
                                            ${selectedItem.value * item.price}
                                            <!--<input type="hidden" name="txtSumOf" value="${selectedItem.value * item.price}" />-->
                                            <c:set var="tmp" value="${(selectedItem.value * item.price)+ tmp}"/>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                        <tr>
                            <td colspan="4" style="text-align: center">
                                Total: 
                            </td>
                            <td style="color: red">
                                ${tmp}
                                <input type="hidden" name="txtTotal" value="${tmp}" />
                            </td>
                        </tr>
                    </tbody>                        
                </table>                                        
                <input type="submit" value="Pay" name="btAction" />
        </form>
            </c:if> 
<!--            <form action="shoppingPageController">
                <input type="submit" value="Click here to buy more item" name="btAction" />
            </form>-->
            <a href="shoppingPageController">Click here to buy more item</a>
        <!--</form>-->  
    </body>

</html>
