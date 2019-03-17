<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Welcome Page</title>
    </head>
    <body>
        This is Home Page.
        <c:if test="${not empty userBean}">
            Welcome: ${userBean.username}!
        </c:if>

        <c:if test="${empty userBean}">
            Hello, please login!
        </c:if>
    </body>
</html>