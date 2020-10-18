<%@ page import="utils.WordModel" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.WordsService" %><%--
  Created by IntelliJ IDEA.
  User: Claudiu
  Date: 10/18/2020
  Time: 3:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Words</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th>#</th>
                    <th>Word</th>
                    <th>Language</th>
                    <th>Definition</th>
                </tr>
                </thead>
                <tbody>
                <% int i=1;
                List<WordModel> words= (List<WordModel>) request.getAttribute("wordsList");%>
                <% for(WordModel word:words)
                {
                    out.println("<tr>");
                    out.println("<td>"+ i++ +"</td>");
                    out.println("<td>"+ word.word+"</td>");
                    out.println("<td>"+ word.language +"</td>");
                    out.println("<td>"+ word.definition +"</td>");
                    out.println("</tr>");

                }%>
                </tbody>

            </table>
        </div>
    </div>
</div>
</body>
</html>
