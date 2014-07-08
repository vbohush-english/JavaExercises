<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise16" scope = "application" class = "net.bohush.exercises.chapter43.Exercise16" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_16</title>
    </head>
    <body><center><br>
        <% int day = exercise16.getDay(request);
            if(day == 0) {
            %>
            <form action = "Exercise43_16.jsp" method = "post"><br><br>
            <table>
                <tr><td  colspan="5"><center>Check the boxes if your birthday is in these sets</center></td></tr>
                <tr>
                <%
                    int[][][] dates = exercise16.getDates();
                    for(int i = 0; i < dates.length; i++) {
                       out.print("<td width = 120><table>");
                       for(int j = 0; j < dates[i].length; j++) {
                           out.print("<tr>");
                            for(int k = 0; k < dates[i][j].length; k++) {
                                out.print("<td width = 20>" + dates[i][j][k] + "</td>");
                            }                       
                           out.print("</tr>");
                       }
                       out.print("<tr><td colspan=4><center><input type=\"checkbox\" name=\"cb" + i + "\"/></center></td></tr>");
                       out.print("</table></td>");
                    }
                %></tr>
            </table><br>
            <input type = "submit" value = "Find date" />
            </form>            
            <%  } else {
                out.print("Your birth day is " + day);
            }
            %>
    </center></body>
</html>
