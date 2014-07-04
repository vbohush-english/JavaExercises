<!DOCTYPE html>
<html>
    <head>
        <title>Exercise43_02</title>
    </head>
    <body><center><table align="center"  border="1">
    <%
        int tableSize = 9;
        String size = request.getParameter("size");
        if(size != null) {
            try {
                tableSize = Integer.parseInt(size);    
            } catch (NumberFormatException ex) {}
        }        
    %>
    <tr><td colspan=<%= (tableSize + 1) %>  align="center"  bgcolor="#cccccc" height=30><b>Multiplication table</b></td></tr>
    <tr><td  width="40" bgcolor="#cccccc"></td>
        
    <% for (int i = 1; i <= tableSize; i++) {%>
        <td align="center"  width="40"  bgcolor="#cccccc" ><%= i %></td>
    <% }%>
    </tr>  
    <% for (int i = 1; i <= tableSize; i++) {%>
        <tr><td align="center"  bgcolor="#cccccc" ><%= i %></td>
        <% for (int j = 1; j <= tableSize; j++) {%>
            <td align="center" ><%= i * j %></td>
        <% }%> 
        </tr>
    <% }%>  

</table></center></body></html>