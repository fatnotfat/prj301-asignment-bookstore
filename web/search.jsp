<%-- 
    Document   : search
    Created on : Oct 9, 2022, 3:42:35 PM
    Author     : HAU NUONG MO HANH
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="phatnt.registration.RegistrationDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <%--
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                String username = "";
                for (Cookie cookie : cookies) {
                    if (!cookie.getName().equals("JSESSIONID")) {
                        username = cookie.getName();
                    }
                }
        --%>
        <font color = "red">
        Welcome, ${sessionScope.USER.lastname}
        </font>

        <%--
            }//end cookies are existed
        --%>
        <h1>Search Page</h1>
        <form action="searchLastnameController">
            Search Value <input type="text" name="txtSearchValue" <%--
                String temp = "";
                if (request.getParameter("txtSearchValue") != null) {
                    temp = request.getParameter("txtSearchValue");
                }
                                --%>
                                value="${param.txtSearchValue}" /><br/>
            <input type="submit" value="Search" name="btAction" />
            <a href="LogoutServlet">Logout</a>
        </form><br/>

        <%--
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result
                        = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) {
        --%>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}" />
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%--
                            int count = 0;
                            for (RegistrationDTO dto : result) {
                                String urlWriting = "DispatchController"
                                        + "?btAction=Delete"
                                        + "&pk=" + dto.getUsername()
                                        + "&lastSearchValue=" + searchValue;

                        --%>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <c:url var="urlRewriting" value="deleteAccountController">
                                <c:param name="btAction" value="Delete"/>
                                <c:param name="pk" value="${dto.username}"/>
                                <c:param name="lastSearchValue" value="${searchValue}"/>
                            </c:url>
                        <form action="updateAccountController">
                            <tr>
                                <td>
                                    ${counter.count} 
                                    .</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.lastname}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <%--
                                               if (dto.isRole()) {
                                           %>
                                           checked="checked"
                                           <%
                                               }//end admin role is true
                                           --%>
                                           <c:if test="${dto.role eq true}">
                                               checked ="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" 
                                           value="${searchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                No record is matched!!!
            </h2>
        </c:if>
        <%--<c:set var="error" value="${requestScope.UPDATE_ERROR}"/>--%>
        <%--<c:if test="${not empty error}">--%>
            <!--<h3 style="color: red">${error}</h3>--> 
        <%--</c:if>--%>
            <%--<c:if test="${not empty requestScope}">--%>
                <!--<h3 style="color: red">${sessionScope.STATUS}</h3>--> 
            <%--</c:if>--%>
    </c:if>


</body>
</html>
