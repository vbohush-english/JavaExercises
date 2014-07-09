<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise19" scope = "session" class = "net.bohush.exercises.chapter43.Exercise19" />
<jsp:setProperty name = "exercise19" property = "*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_19</title>
    </head>
    <%
        exercise19.doOperation();
    %>
    <body><form method = "post">
            <center>
                <table>
                    <tr align="center"><td height="50"><p><font color = "#FF0000"><%= exercise19.getErrorMessage() %></font></p></td></tr><tr align="left"><td>
                    <fieldset><legend>Staff Information</legend>
                        <p>ID: <input type = "text" name = "id" style="background-color: yellow" value = "<%= exercise19.getId() %>"></p>
                        <p>Last Name: <input type = "text" name = "lastName" value = "<%= exercise19.getLastName() %>">
                            First Name: <input type = "text" name = "firstName" value = "<%= exercise19.getFirstName() %>">
                            MI: <input type = "text" name = "mi" size = "3" value = "<%= exercise19.getMi() %>"></p>
                        <p>Address: <input type = "text" name = "address" size = "20" value = "<%= exercise19.getAddress() %>"></p>
                        <p>City: <input type = "text" name = "city" size = "20" value = "<%= exercise19.getCity() %>">
                            State: <input type = "text" name = "state" size = "5" value = "<%= exercise19.getState() %>"></p>
                        <p>Telephone: <input type = "text" name = "telephone" size = "20" value = "<%= exercise19.getTelephone() %>"></p>
                    </fieldset></td></tr><tr align="center"><td>
                    <p><input type = "submit" name = "operation" value = "View">
                        <input type = "submit" name = "operation" value = "Insert">
                        <input type = "submit" name = "operation" value = "Update">
                        <input type = "submit" name = "operation" value = "Delete"></p></td></tr>
                </table>
            </center>
        </form>
    </body>
</html>
