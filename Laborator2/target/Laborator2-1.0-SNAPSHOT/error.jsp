<%--
  Created by IntelliJ IDEA.
  User: Claudiu
  Date: 10/18/2020
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language_used}" />
<fmt:setBundle basename="translate"/>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1><fmt:message key="Error_lan"/> : ${requestScope['errorMessage']}</h1>
</body>
</html>
