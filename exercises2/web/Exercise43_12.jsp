<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise12" scope = "application" class = "net.bohush.exercises.chapter43.Exercise12" />
<jsp:setProperty name = "exercise12" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_12</title>
    </head>
    <body><center><br>
        The current Yes count is <%= exercise12.getYesCount() %><br>
        The current No count is <%= exercise12.getNoCount() %><br>
    </center></body>
</html>
