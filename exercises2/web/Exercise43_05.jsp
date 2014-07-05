<%@page import="java.util.Scanner"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise43_05</title>
    </head>
    <body>
    <center>
        <%
            String ssn = request.getParameter("ssn");
            String course = request.getParameter("course");
            File file = new File(course + ".txt");
            boolean found = false;
            if (file.exists()) {
                Scanner input = new Scanner(file);
                while(input.hasNextLine()) {
                    String[] student = input.nextLine().split("#");
                    if(student[1].equals(ssn)) {
                        out.println(student[0] + " " + student[2]);
                        found = true;
                        break;
                    }                    
                }
                if(!found){
                    out.println("SSN <b>" + ssn + "</b> not found");        
                }
            } else {
                out.println("Course <b>" + course + "</b> not found");    
            }
        %>
    </center>
    </body>
</html>
