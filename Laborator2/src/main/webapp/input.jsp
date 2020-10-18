<%@ page import="utils.Languages" %><%--
  Created by IntelliJ IDEA.
  User: Claudiu
  Date: 10/17/2020
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Input</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<body>
<%
    String wordCookie="Word";
    String languageCookie="Language";
    for(Cookie cookie:request.getCookies())
    {
        if(cookie.getName().equals("word"))
        {
            wordCookie=cookie.getValue();
        }
        else if(cookie.getName().equals("language"))
        {
            languageCookie=cookie.getValue();
        }
    }
%>
<div class="container">
    <div class="row col-md-8 ">
        <form class="form" action="capcha" method="post">
            <div class="form-group">
                <label for="wordText">Word :</label>
                <input type="text" class="form-control" id="wordText" PLACEHOLDER="Word" name="word" value=<%=wordCookie%>>
            </div>
            <div class="form-group">
                <label for="language">Language :</label>
                <select class="form-control" id="language" name="language" >
                    <% for (String language : Languages.getLanguages())
                    {
                        if(language.equals(languageCookie)) {
                            out.println("<option selected=\"selected\">" + language + "</option>");
                        }
                        else
                        {
                            out.println("<option>" + language + "</option>");

                        }
                    }%>
                </select>
            </div>
            <div class="form-group">
                <img src="${pageContext.request.contextPath}/capcha"></br>
                <label for="result">Result :</label>
                <input type="text" class="form-control" id="result" PLACEHOLDER="0" name="result">
            </div>
            <div class="form-group">
                <label for="definition">Definition :</label>
                <textarea class="form-control" id="definition" rows="3" placeholder="Word definition goes here" name="definition"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>
</body>
</html>
