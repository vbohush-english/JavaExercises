<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise15" scope = "session" class = "net.bohush.exercises.chapter43.Exercise15" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_15_2</title>
    </head>
    <body><center><br><br><br>
        <table>
            <%
                exercise15.resetCorrectCount();
                for (int i = 0; i < exercise15.getSize(); i++) { %>
                   <tr>
                       <td height=30><% out.print(exercise15.getNumbers1()[i]); %></td>
                       <td height=30> - </td>
                       <td height=30><% out.print(exercise15.getNumbers2()[i]); %></td>
                       <td height=30> = </td>
                       <td height=30><% out.print(request.getParameter("sub" + i)); %></td>
                       <td height=30><% out.print(exercise15.check(request.getParameter("sub" + i), i)); %></td>
                   </tr> 
                <% }
            %>
        </table>
        <br>The total correct count is <% out.print(exercise15.getCorrectCount()); %>
    </center></body>
</html>
