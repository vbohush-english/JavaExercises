<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise17" scope = "session" class = "net.bohush.exercises.chapter43.Exercise17" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_17</title>
    </head>
    <body><center><br>
        <%
            String capital = request.getParameter("capital");
            if(capital == null) {
            %>
                <form action = "" method = "get">
                What is the capital of <%= exercise17.getState() %>
                <input type="text" name = "capital">
                <input type = "submit" value = "Sybmit" />
                </form>
            <%
            } else {
                String result = ". The capital of " + exercise17.getState() + " is " + exercise17.getCapital() + ".<br><br>";
                if(exercise17.check(capital)){
                    result = "Yes" + result;
                } else {
                    result = "No" + result;
                }
                out.print(result);
                out.print("Correct: " + exercise17.getCorrectCount() + "<br>");
                out.print("Wrong " + exercise17.getWrongCount() + "<br>");
                out.print("Total " + exercise17.getCount()+ "<br>");
                if(exercise17.isFinish()) {
                    exercise17.restart();
                    out.print("<br><input value=\"Restart\" onclick=\"location.href='Exercise43_17.jsp'\" type=\"button\" />");
                } else {
                    out.print("<br><input value=\"Next\" onclick=\"location.href='Exercise43_17.jsp'\" type=\"button\" />");
                }
            }
        %>
    </center></body>
</html>
