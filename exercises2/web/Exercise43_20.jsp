<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise20" scope = "session" class = "net.bohush.exercises.chapter43.Exercise20" />
<jsp:setProperty name = "exercise20" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_20</title>
    </head>
    <body><center><br><form method = "post">
        Guess number between 1 and 1000:<br>
        <input type = "text" name = "nextNumber" value = "" size = 5>
        <input type = "submit" name = "operation" value = "Check">
        <input type = "submit" name = "operation" value = "Restart">
        <br><%
            String operation = request.getParameter("operation");
            if((operation != null) && (!operation.equals(""))) {
                if(operation.equals("Check")) {
                    out.print(exercise20.getResult());  
                } else if(operation.equals("Restart")) {
                    exercise20.restart();
                }
            }
        %>
    </form></center></body>
</html>
