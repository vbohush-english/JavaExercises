package net.bohush.exercises.chapter44;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class Exercise07 {


    private List<List<List<Integer>>> dates = new ArrayList<>();
    private List<Boolean> checked = new ArrayList<>();
    
    public Exercise07() {
        List<List<Integer>> tmpList = new ArrayList<>();
        tmpList.add(Arrays.asList(new Integer[]{1, 3, 5, 7}));
        tmpList.add(Arrays.asList(new Integer[]{9, 11, 13, 15}));
        tmpList.add(Arrays.asList(new Integer[]{17, 19, 21, 23}));
        tmpList.add(Arrays.asList(new Integer[]{25, 27, 29, 31}));
        dates.add(tmpList);
        tmpList = new ArrayList<>();
        tmpList.add(Arrays.asList(new Integer[]{2, 3, 6, 7}));
        tmpList.add(Arrays.asList(new Integer[]{10, 11, 14, 15}));
        tmpList.add(Arrays.asList(new Integer[]{18, 19, 22, 23}));
        tmpList.add(Arrays.asList(new Integer[]{26, 27, 30, 31}));
        dates.add(tmpList);
        tmpList = new ArrayList<>();
        tmpList.add(Arrays.asList(new Integer[]{4, 5, 6, 7}));
        tmpList.add(Arrays.asList(new Integer[]{12, 13, 14, 15}));
        tmpList.add(Arrays.asList(new Integer[]{20, 21, 22, 23}));
        tmpList.add(Arrays.asList(new Integer[]{28, 29, 30, 31}));
        dates.add(tmpList);
        tmpList = new ArrayList<>();
        tmpList.add(Arrays.asList(new Integer[]{8, 9, 10, 11}));
        tmpList.add(Arrays.asList(new Integer[]{12, 13, 14, 15}));
        tmpList.add(Arrays.asList(new Integer[]{24, 25, 26, 27}));
        tmpList.add(Arrays.asList(new Integer[]{28, 29, 30, 31}));
        dates.add(tmpList);
        tmpList = new ArrayList<>();
        tmpList.add(Arrays.asList(new Integer[]{16, 17, 18, 19}));
        tmpList.add(Arrays.asList(new Integer[]{20, 21, 22, 23}));
        tmpList.add(Arrays.asList(new Integer[]{24, 25, 26, 27}));
        tmpList.add(Arrays.asList(new Integer[]{28, 29, 30, 31}));
        dates.add(tmpList);
        for (int i = 0; i < 5; i++) {
            checked.add(Boolean.FALSE);
        }
    }

    public void setChecked(List<Boolean> checked) {
        this.checked = checked;
    }

    public void setDates(List<List<List<Integer>>> dates) {
        this.dates = dates;
    }
    
    public List<Boolean> getChecked() {
        return checked;
    }

    public List<List<List<Integer>>> getDates() {
        return dates;
    }
    
    public String getDay() {
        int day = 0;
        for (int i = 0; i < checked.size(); i++) {
            if(checked.get(i)) {
                day += dates.get(i).get(0).get(0);
            }
        }
        if(day == 0) {
            return "";
        } else {
            return "Your birth day is " + day;
        }
    }    
}
