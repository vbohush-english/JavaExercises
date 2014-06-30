package net.bohush.exercises.chapter42;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise06 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ssn = request.getParameter("ssn");
        String course = request.getParameter("course");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercise42_06</title>");            
            out.println("</head><center>");
            
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
            
            out.println("</center><body></body>");
            out.println("</html>");
        }
    }
    
    class Studen {
        private String name;
        private String ssn;
        private double score;

        public Studen(String name, String ssn, double score) {
            this.name = name;
            this.ssn = ssn;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public double getScore() {
            return score;
        }

        public String getSsn() {
            return ssn;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public void setSsn(String ssn) {
            this.ssn = ssn;
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}