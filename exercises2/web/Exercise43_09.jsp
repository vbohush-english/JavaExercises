<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_09</title>
    </head>
    <body>
    <center>
        <% 
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                out.println(cookie.getName() + "\'s value is " + cookie.getValue() + "<br><br>");
            }
        %>
    </center>
    </body>
</html>
