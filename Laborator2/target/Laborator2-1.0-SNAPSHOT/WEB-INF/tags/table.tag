<%@ tag import="java.util.Date, java.text.DateFormat" %>
<%@ tag import="utils.WordModel" %>
<%@ tag import="utils.WordsService" %>
<%@ tag import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--
<sql:setDataSource
        var="wordsDb"
        driver="org.sqlite.JDBC"
        url="jdbc:sqlite:C:\\Users\\Claudiu\\Desktop\\Master\\Java\\Laboratories-Java_Technologies\\Laborator2\\src\\Resources\\words.db"
/>
<sql:query  dataSource="${wordsDb}" var="list_Words">
    SELECT Word,Language,Definition,Date_Added FROM Words;
</sql:query>
-->
<fmt:setLocale value="${sessionScope.language_used}" />
<table border="1" cellpadding="5">
    <tr>
        <th>Word</th>
        <th>Language</th>
        <th>Definition</th>
        <th>Added</th>
    </tr>
    <c:forEach var="word" items="${WordsService.wordsList}">
        <tr>
            <td><c:out value="${word.word}" /></td>
            <td><c:out value="${word.language}" /></td>
            <td><c:out value="${word.definition}" /></td>
            <td><fmt:formatDate value="${word.date}" type="both"
                            dateStyle="full" timeStyle="full" />
            </td>

        </tr>
    </c:forEach>
</table>