<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise11" scope = "request" class = "net.bohush.exercises.chapter43.Exercise11" />
<jsp:setProperty name = "exercise11" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_11</title>
    </head>
    <body>
    <%= exercise11.highlighte()%>
    </body>
</html>
