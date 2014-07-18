<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise04c" scope = "session" class = "net.bohush.exercises.chapter45.client.Exercise04c" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise45_04</title>
    </head>
    <body><center><%= exercise04c.getCount()%>
    </center>
</body>
</html>
