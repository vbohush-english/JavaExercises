<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "exercise05c" scope = "session" class = "net.bohush.exercises.chapter45.client.Exercise05c" />
<!DOCTYPE html>
<html>
    <body>
        <%
            java.util.List<String> quiz = exercise05c.getQuestions();

            java.util.List<Integer> answers = new java.util.ArrayList<Integer>();
            for (int i = 0; i < quiz.size(); i++) {
                String trueOrFalse = request.getParameter("question" + i);
                if (trueOrFalse == null) {
                    answers.add(-1);
                } else if (trueOrFalse.equals("True")) {
                    answers.add(1); // Answered true
                } else if (trueOrFalse.equals("False")) {
                    answers.add(0); // Answered false
                }
                
            }
            
            java.util.List<Integer> result = exercise05c.gradeQuiz(answers);
            
            int correctCount = 0;
            int totalCount = 0;
            for (int i = 0; i < result.size(); i++) {
                if (!result.get(i).equals(-1)) {
                    totalCount++;
                    if (result.get(i).equals(1)) {
                        correctCount++;
                    }
                }
            }
        %>
        Out of <%= totalCount%> questions, <%= correctCount%> correct.
    </body>
</html>