<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="animal" type="ru.safronovvladimir.model.Animal" scope="request"/>
    <title>Animal ${animal.name}</title>
</head>
<body>
<section>
    <h1>${animal.name}&nbsp;<a href="animal?id=${animal.id}&action=edit"><img
            src="img/pencil.png"></a></h1>
    <tr>
        <td><h3>Date of birth: </h3></td>
        <td>${animal.dateOfBirth}</td>
    </tr>
    <br>
    <td><h3>Commands: </h3></td>
    <p>
        <c:forEach var="commands" items="${animal.commands}">
            <jsp:useBean id="commands" type="java.lang.String"/>
            <%=commands%><br/>
        </c:forEach>
    </p>
    <br><br>
    <button onclick="window.history.back()">OK</button>
</section>
</body>
</html>
