<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise06" scope = "application" class = "net.bohush.exercises.chapter43.Exercise06" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_06</title>
    </head>
    <body>
    <center>
        <%
            String ssn = request.getParameter("ssn");
            String course = request.getParameter("course");
        
            try {
                ResultSet rset = exercise06.getStatement().executeQuery("select * from " + course + " where ssn = \"" + ssn + "\";");
                if (rset.next()) {
                    out.println(rset.getString("name") + " " + rset.getString("score"));    
                } else {
                    out.println("SSN <b>" + ssn + "</b> not found in course <b>" + course);                           
                }
            } catch (SQLException ex) {
                out.println("Error while execuring query");
            }
        %>
    </center>
    </body>
</html>
