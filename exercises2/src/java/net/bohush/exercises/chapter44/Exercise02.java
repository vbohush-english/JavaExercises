package net.bohush.exercises.chapter44;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Exercise02 {
    
    public String getTable() {
        String result = "<table align=\"center\"  border=\"1\">";
        int tableSize = 9;
        result = result + "<tr><td colspan=" + (tableSize + 1) + " align=\"center\"  bgcolor=\"#cccccc\" height=30><b>Multiplication table</b></td></tr>";
        result = result + "<tr><td  width=\"40\" bgcolor=\"#cccccc\"></td>";
        for (int i = 1; i <= tableSize; i++) {
            result = result + "<td align=\"center\"  width=\"40\"  bgcolor=\"#cccccc\" >" + i + "</td>";
        }
        result = result + "</tr>";
        for (int i = 1; i <= tableSize; i++) {
            result = result + "<tr><td align=\"center\"  bgcolor=\"#cccccc\" >" + i + "</td>";
            for (int j = 1; j <= tableSize; j++) {
                result = result + "<td align=\"center\" >" + (i * j) + "</td>";
            }
            result = result + "</tr>";
        }
        result = result + "</table>";
        return result;
    }
    
    private long computeFactorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * computeFactorial(n - 1);
        }
    }
}
