<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.safronovvladimir.model.TypeAnimal" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Animals list</title>
</head>
<body>
<%--<jsp:include page="fragments/header.jsp"/>--%>
<section>
<%--    <a href="resume?action=add"><img src="img/add.png"></a>--%>
<%--    <br>--%>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>Type</th>
        </tr>
        <c:forEach items="${animals}" var="animal">
            <jsp:useBean id="animal" type="ru.safronovvladimir.model.Animal"/>
            <tr>
                <td><a href="animal?id=${animal.id}">${animal.id}</a></td>
                <td>${animal.name}</td>
                <td>${animal.type}</td>
            </tr>
        </c:forEach>
    </table>
</section>
<%--<jsp:include page="fragments/footer.jsp"/>--%>
</body>
</html>