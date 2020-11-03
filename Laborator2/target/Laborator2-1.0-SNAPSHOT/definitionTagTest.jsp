<%--
  Created by IntelliJ IDEA.
  User: Claudiu
  Date: 11/1/2020
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
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
                    <th>Type</th>
                    <th>Argument</th>
                    <th>Result</th>
                </tr>
                </thead>
                <tbody>
               <tr>
                   <td>Valid Input</td>
                   <td>C#</td>
                   <td><ex:Definition word="C#"/></td>
               </tr>
    <tr>
    <td>Invalid Input</td>
    <td>InvalidWord</td>
    <td><ex:Definition word="InvalidWord"/></td>
        </tr>
        <tr>
        <td>No Input</td>
        <td></td>
        <td><ex:Definition/></td>
            </tr>
                </tbody>

            </table>
        </div>
    </div>
</div>
</body>
</html>
