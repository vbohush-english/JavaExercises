package net.bohush.exercises.chapter43;

import javax.servlet.http.HttpServletRequest;

public class Exercise16 {
    
    private int[][][] dates = new int[][][]{
      {{ 1,  3,  5,  7},
       { 9, 11, 13, 15},
       {17, 19, 21, 23},
       {25, 27, 29, 31}},
      {{ 2,  3,  6,  7},
       {10, 11, 14, 15},
       {18, 19, 22, 23},
       {26, 27, 30, 31}},
      {{ 4,  5,  6,  7},
       {12, 13, 14, 15},
       {20, 21, 22, 23},
       {28, 29, 30, 31}},
      {{ 8,  9, 10, 11},
       {12, 13, 14, 15},
       {24, 25, 26, 27},
       {28, 29, 30, 31}},
      {{16, 17, 18, 19},
       {20, 21, 22, 23},
       {24, 25, 26, 27},
       {28, 29, 30, 31}}};

    public int[][][] getDates() {
        return dates;
    }
    
    public int getDay(HttpServletRequest request) {
        int day = 0;
        for (int i = 0; i < dates.length; i++) {
            String cb = request.getParameter("cb" + i);
            if((cb != null)&&(cb.equals("on"))) {
                day += dates[i][0][0];
            }
        }
        return day;
    }
}
