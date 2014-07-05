<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise07" scope = "application" class = "net.bohush.exercises.chapter43.Exercise07" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_07</title>
    </head>
    <body>
    <center><%= exercise07.changePassword(request)%></center>
    </body>
</html>
