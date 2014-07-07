<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise14" scope = "session" class = "net.bohush.exercises.chapter43.Exercise14" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_14_2</title>
    </head>
    <body><center><br><br><br>
        <table>
            <%
                exercise14.resetCorrectCount();
                for (int i = 0; i < exercise14.getSize(); i++) { %>
                   <tr>
                       <td height=30><% out.print(exercise14.getNumbers1()[i]); %></td>
                       <td height=30> + </td>
                       <td height=30><% out.print(exercise14.getNumbers2()[i]); %></td>
                       <td height=30> = </td>
                       <td height=30><% out.print(request.getParameter("add" + i)); %></td>
                       <td height=30><% out.print(exercise14.check(request.getParameter("add" + i), i)); %></td>
                   </tr> 
                <% }
            %>
        </table>
        <br>The total correct count is <% out.print(exercise14.getCorrectCount()); %>
    </center></body>
</html>
