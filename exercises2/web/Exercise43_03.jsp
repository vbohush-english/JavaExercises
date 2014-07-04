<!DOCTYPE html>
<html>
    <head>
        <title>Exercise43_03</title>
    </head>
    <body>
        Last Name: <b><%= request.getParameter("lastName") %></b>
        First Name: <b><%= request.getParameter("firstName") %></b>
        MI: <b><%= request.getParameter("mi") %></b><br>
        Gender: <b><%= request.getParameter("gender") %></b><br>
        Major: <b><%= request.getParameter("major") %></b> Minor: <b>
        <%
        String[] minors = request.getParameterValues("minor");
        if (minors != null) {
            for (int i = 0; i < minors.length; i++) {
                out.println(minors[i] + " ");
            }
        }
        %>
        </b><br> Tennis: <b><%= request.getParameter("tennis") %></b>
        Golf: <b><%= request.getParameter("golf") %></b>
        PingPong: <b><%= request.getParameter("pingPong") %></b><br>
        Remarks: <b><%= request.getParameter("remarks") %></b>
    </body>
</html>