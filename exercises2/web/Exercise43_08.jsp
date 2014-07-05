<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_08</title>
    </head>
    <body>
    <center>
        <% 
            Cookie cookie1 = new Cookie("color", "blue");
            Cookie cookie2 = new Cookie("radius", "10.1");
            Cookie cookie3 = new Cookie("count", "6");
            int cookieTime = 2 * 60 * 60 * 24;
            cookie1.setMaxAge(cookieTime);
            cookie2.setMaxAge(cookieTime);
            cookie3.setMaxAge(cookieTime);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.addCookie(cookie3);
        %></center>
    </body>
</html>
