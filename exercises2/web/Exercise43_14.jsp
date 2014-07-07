<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise14" scope = "session" class = "net.bohush.exercises.chapter43.Exercise14" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_14</title>
    </head>
    <body><center><br>
        <form action = "Exercise43_14_2.jsp" method = "get"><br><br>
        <table>
            <%
                exercise14.renew();
                for (int i = 0; i < exercise14.getSize(); i++) { %>
                   <tr>
                       <td height=30><% out.print(exercise14.getNumbers1()[i]); %></td>
                       <td height=30> + </td>
                       <td height=30><% out.print(exercise14.getNumbers2()[i]); %></td>
                       <td height=30> = </td>
                       <td height=30><input type = "text" name = "add<% out.print(i); %>" size = "3" /></td>
                   </tr> 
                <% }
            %>
        </table><br>
        <input type = "submit" value = "Submit" /> <br>
        Click the browser's Refresh button to get a new quiz
        </form>
    </center></body>
</html>
