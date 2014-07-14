package net.bohush.exercises.chapter44;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Exercise08 {

    private String guessedCapital = "";
    
    private List<String[]> states = Arrays.asList(
        new String[]{"Alabama", "Montgomery"}, 
        new String[]{"Alaska", "Juneau"}, 
        new String[]{"Arizona", "Phoenix"}, 
        new String[]{"Arkansas", "Littlerock"}, 
        new String[]{"California", "Sacramento"}, 
        new String[]{"Colorado", "Denver"}, 
        new String[]{"Connecticut", "Hartford"}, 
        new String[]{"Delaware", "Dover"}, 
        new String[]{"Florida", "Tallahassee"}, 
        new String[]{"Georgia", "Atlanta"}, 
        new String[]{"Hawaii", "Honolulu"}, 
        new String[]{"Idaho", "Boise"}, 
        new String[]{"Illinois", "Springfield"}, 
        new String[]{"Indiana", "Indianapolis"}, 
        new String[]{"Iowa", "Des Moines"}, 
        new String[]{"Kansas", "Topeka"}, 
        new String[]{"Kentucky", "Frankfort"}, 
        new String[]{"Louisiana", "Baton Rouge"}, 
        new String[]{"Maine", "Augusta"}, 
        new String[]{"Maryland", "Annapolis"}, 
        new String[]{"Massachusetts", "Boston"}, 
        new String[]{"Michigan", "Lansing"}, 
        new String[]{"Minnesota", "St. Paul"}, 
        new String[]{"Mississippi", "Jackson"}, 
        new String[]{"Missouri", "Jefferson City"}, 
        new String[]{"Montana", "Helena"}, 
        new String[]{"Nebraska", "Lincoln"}, 
        new String[]{"Nevada", "Carson City"}, 
        new String[]{"New Hampshire", "Concord"}, 
        new String[]{"New Jersey", "Trenton"}, 
        new String[]{"New Mexico", "SantaFe"}, 
        new String[]{"New York", "Albany"}, 
        new String[]{"North Carolina", "Raleigh"}, 
        new String[]{"North Dakota", "Bismarck"}, 
        new String[]{"Ohio", "Columbus"}, 
        new String[]{"Oklahoma", "Oklahoma City"}, 
        new String[]{"Oregon", "Salem"}, 
        new String[]{"Pennsylvania", "Harrisburg"}, 
        new String[]{"Rhode Island", "Providence"}, 
        new String[]{"South Carolina", "Columbia"}, 
        new String[]{"South Dakota", "Pierre"}, 
        new String[]{"Tennessee", "Nashville"}, 
        new String[]{"Texas", "Austin"}, 
        new String[]{"Utah", "Salt Lake City"}, 
        new String[]{"Vermont", "Montpelier"}, 
        new String[]{"Virginia", "Richmond"}, 
        new String[]{"Washington", "Olympia"}, 
        new String[]{"West Virginia", "Charleston"}, 
        new String[]{"Wisconsin", "Madison"}, 
        new String[]{"Wyoming", "Cheyenne"});

        private int index = 0;
        private int correctCount = 0;
        private int wrongCount = 0;
        
    public Exercise08() {
        Collections.shuffle(states);
    }

    public String getGuessedCapital() {
        return guessedCapital;
    }

    public void setGuessedCapital(String guessedCapital) {
        this.guessedCapital = guessedCapital;
    }
    
    public String getState() {
        if(index >= states.size()) {
           return null;
        } else {
            return states.get(index)[0];
        }
    }
    
    public String getCapital() {
        if(index >= states.size()) {
           return null;
        } else {
            return states.get(index)[1];
        }
    }

    public String getButtonName() {
        if(index >= states.size()) {
            index = 0;
            correctCount = 0;
            wrongCount = 0;
            Collections.shuffle(states);
            return "Restart";
        } else {
            return "Next";
        }
    }

    public String check() {
        String result = ". The capital of " + getState() + " is " + getCapital() + ".<br><br>";
        index++;
        if((guessedCapital!= null)&&(guessedCapital.equals(states.get(index - 1)[1]))) {
            correctCount++;
            result = "Yes" + result;
        } else {
            wrongCount++;
            result = "No" + result;
        }
        result += "Correct: " + correctCount + "<br>";
        result += "Wrong " + wrongCount + "<br>";
        result += "Total " + states.size() + "<br>";
        guessedCapital = "";
        return result;
    }
}
