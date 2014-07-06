<%@page import="net.bohush.exercises.chapter43.Exercise13.Answer"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise13" scope = "application" class = "net.bohush.exercises.chapter43.Exercise13" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_13_2</title>
    </head>
    <body><center><br>
        <form action = "Exercise43_13_2.jsp" method = "get"><br><br>
        <table>
            <%
                exercise13.parseAnswers(request);
                ArrayList<Answer> answers = exercise13.getAnswers();
                for (int i = 0; i < answers.size(); i++) { %>
                   <tr>
                       <td height=30><% out.print(answers.get(i).getQuestion()); %></td>
                       <td height=30>(<% out.print(answers.get(i).getYesCount()); %> Yes)</td>
                       <td height=30>(<% out.print(answers.get(i).getNoCount()); %> No)</td>
                   </tr> 
                <% }
            %>
        </table>
        </form>
    </center></body>
</html>
