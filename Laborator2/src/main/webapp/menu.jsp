<%--
  Created by IntelliJ IDEA.
  User: Claudiu
  Date: 11/2/2020
  Time: 11:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="language_used" class="java.lang.String" scope="session"/>
<fmt:setLocale value="${sessionScope.language_used}" />
<fmt:setBundle basename="translate"/>
<html>

<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<body>
<div class="container align-items-center">
    <div class="row col-mod-4 offset-4 ">
        <a href="words"> <button class="btn-primary btn-lg btn-block"><fmt:message key="display_word"/></button></a>
    </div>
    <div class="row col-mod-4 offset-4 ">
        <a href="input.jsp"><button class="btn-primary btn-lg btn-block"><fmt:message key="add_word" /></button></a>
    </div>
    <div class="row col-mod-4 offset-4 ">
        <a href="definitionTagTest.jsp"><button class="btn-primary btn-lg btn-block"><fmt:message key="Custom_Definition_Tag" /></button></a>
    </div>
    <div class="row col-mod-4 offset-4 ">
        <a href="tableTag.jsp"><button class="btn-primary btn-lg btn-block"><fmt:message key="Table_Tag"/></button></a>
    </div>
</div>
</body>
</html>

