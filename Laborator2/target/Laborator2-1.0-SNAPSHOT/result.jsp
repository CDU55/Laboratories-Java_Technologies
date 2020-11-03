<%@ page import="utils.WordModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Words</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<body>
<div class="container">
    <h1></h1>
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
                <% if(request.getAttribute("wordsList")!=null)
                {    int i=1;
                List<WordModel> words= (List<WordModel>) request.getAttribute("wordsList");%>
                <% for(WordModel word:words)
                {
                    out.println("<tr>");
                    out.println("<td>"+ i++ +"</td>");
                    out.println("<td>"+ word.word+"</td>");
                    out.println("<td>"+ word.language +"</td>");
                    out.println("<td>"+ word.definition +"</td>");
                    out.println("</tr>");

                }
                }
                else
                {
                    out.println("<h1 class=\"Warning\">No content</h1>");
                }%>
                </tbody>

            </table>
        </div>
    </div>
</div>
</body>
</html>
