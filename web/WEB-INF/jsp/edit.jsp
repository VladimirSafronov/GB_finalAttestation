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
    <form method="post" action="animal" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value="${animal.id}">
        <h3>Name: </h3>
        <dl>
                <dt>${animal.name}</dt>
        </dl>
        <br>
        <h3>Date of birth: </h3>
        <dl>
                <dt>${animal.dateOfBirth}</dt>
        </dl>
        <br>
        <h3>Commands: </h3>
        <dl>
            <c:forEach var="command" items="<%=animal.getCommands()%>">
                <dl>
                    <dt>${command}</dt>
                </dl>
            </c:forEach>
            <dd><label>
                <h4>Enter new command:</h4>
                <input type="text" name="command" size=100 value="">
            </label></dd>
        </dl>
        <br><br>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
