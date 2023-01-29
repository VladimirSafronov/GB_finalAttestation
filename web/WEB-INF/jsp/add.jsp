<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/style.css">
  <jsp:useBean id="animal" type="ru.safronovvladimir.model.Animal" scope="request"/>
  <title>New animal </title>
</head>
<body>
<section>
  <form method="post" action="animal" enctype="application/x-www-form-urlencoded">
    <h3>Name: </h3>
    <dl>
      <label>
        <input type="text" name="name" size=100 value="">
      </label>
    </dl>
    <br>
    <h3>Date of birth: </h3>
    <dl>
      <dd><label>
        <input type="text" name="dateOfBirth" size=100 value="">
      </label></dd>
    </dl>
    <br>
    <h3>Type: </h3>
    <dl>
      <dd><label>
        <input type="text" name="animal_type" size=100 value="">
      </label></dd>
    </dl>
    <h3>Commands: </h3>
    <dl>
      <dd><label>
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
