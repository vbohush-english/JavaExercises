<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise05c" scope = "session" class = "net.bohush.exercises.chapter45.client.Exercise05c" />
<!DOCTYPE html>
<html>
    <body>
        <%
            java.util.List<String> questions = (java.util.ArrayList<String>) (exercise05c.getQuestions());
        %>
        <form method = "post" action = "Exercise45_05_2.jsp">
            <table>
                <% for (int i = 0; i < questions.size(); i++) {%>
                <tr>
                    <td>
                        <label><%= questions.get(i)%></label>
                    </td>
                    <td>
                        <input type = "radio" name = "<%= "question" + i%>" value = "True" /> True
                    </td>
                    <td>
                        <input type = "radio" name = "<%= "question" + i%>" value = "False" /> False
                    </td>
                </tr>
                <%}%>
            </table>
            <p><input type = "submit" name = "Submit" value = "Submit">
                <input type = "reset" value = "Reset">
            </p>
        </form>
    </body>
</html>