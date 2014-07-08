<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise18" class = "net.bohush.exercises.chapter43.Exercise18" scope = "page" />
<jsp:setProperty name = "exercise18" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <title>
            Exercise43_18
        </title>
    </head>
    <body>
        <h3>Compute Factorial Using a Bean</h3>
        <form method = "post">
            Enter new value: <input name = "number" /><br /><br />
            <input type = "submit" name = "Submit" value = "Compute Factorial" />
            <input type = "reset" value = "Reset" /><br /><br />
            Factorial of
            <jsp:getProperty name = "exercise18" property = "number" /> is
            <%= exercise18.getFactorial()%>
        </form>
    </body>