<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise10" scope = "application" class = "net.bohush.exercises.chapter43.Exercise10" />
<jsp:setProperty name = "exercise10" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_10</title>
    </head>
    <body>
    <%= exercise10.getImage() %>
    <%= exercise10.getDescription() %>
    </body>
</html>
