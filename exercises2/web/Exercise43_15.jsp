<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise15" scope = "session" class = "net.bohush.exercises.chapter43.Exercise15" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_15</title>
    </head>
    <body><center><br>
        <form action = "Exercise43_15_2.jsp" method = "get"><br><br>
        <table>
            <%
                exercise15.renew();
                for (int i = 0; i < exercise15.getSize(); i++) { %>
                   <tr>
                       <td height=30><% out.print(exercise15.getNumbers1()[i]); %></td>
                       <td height=30> - </td>
                       <td height=30><% out.print(exercise15.getNumbers2()[i]); %></td>
                       <td height=30> = </td>
                       <td height=30><input type = "text" name = "sub<% out.print(i); %>" size = "3" /></td>
                   </tr> 
                <% }
            %>
        </table><br>
        <input type = "submit" value = "Submit" /> <br>
        Click the browser's Refresh button to get a new quiz
        </form>
    </center></body>
</html>
