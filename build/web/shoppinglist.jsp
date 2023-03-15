<%-- 
    Document   : shoppinglist
    Created on : Oct 19, 2022, 10:05:33 AM
    Author     : HAU NUONG MO HANH
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            List<ProductDTO> result =
                    (List<ProductDTO>) request.getAttribute("ITEMS_RESULT");
            if(result != null){
        --%>
        <c:set var="result" value="${requestScope.ITEMS_RESULT}" />
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
                    int count  = 0;
                    for (ProductDTO item : result) {
                    --%>
                    <c:forEach var="item" items="${result}" varStatus="counter">
                    <form action="addItemToCartController">
                        <tr>
                            <td>
                                <%--<%= ++count %>--%>
                                ${counter.count}
                                .</td>
                            <td>
                                <%--<%= item.getSku() %>--%>
                                ${item.sku}
                                <input type="hidden" name="txtID" value="${item.sku}" />
                            </td>
                            <td>
                                <%--<%= item.getName() %>--%>
                                ${item.name}
                            </td>
                            <td>
                                <%--<%= item.getDescription() %>--%>
                                ${item.description}
                            </td>
                            <td>
                                <%--<%= item.getQuantity() %>--%>
                                ${item.quantity}
                                <input type="hidden" name="txtQuantity" value="${item.quantity}" />
                            </td>
                            <td>
                                <%--<%= item.getPrice() %>--%>
                                ${item.price}
                            </td>
                            <td>                                 
                                <input type="submit" value="Buy" name="btAction" />
                            </td>                            
                        </tr>

                    </form>
                </c:forEach>
                <%--
                    }
                --%>

            </tbody>
        </table>
        <form action="viewCartController">
            <input type="submit" value="View your cart" name="btAction" />
        </form>  
    </c:if>
    <%--
        } else {
    --%>
    <c:if test="${empty result}">
        <h2>We are updating more items! We'll comeback soon!!</h2>
    </c:if>
    <%--
        }
    --%>

    <c:set var="signal" value="${requestScope.SIGNAL}"/>
    <c:if test="${not empty signal}">
        <h1>Buying Successfully!</h1>
    </c:if>

    <c:set var="error" value="${requestScope.ERROR}"/>
    <c:if test="${not empty error}">
        <h1>Some item is invalid!! Please try again!</h1>
    </c:if>
</body>
</html>

