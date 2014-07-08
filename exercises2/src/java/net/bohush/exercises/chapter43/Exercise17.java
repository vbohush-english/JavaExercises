package net.bohush.exercises.chapter43;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Exercise17 {

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
        
    public Exercise17() {
        Collections.shuffle(states);
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

    public boolean isFinish() {
        if(index >= states.size()) {
            return true;
        } else {
            return false;
        }
    }
    
    public void restart() {
        index = 0;
        correctCount = 0;
        wrongCount = 0;
        Collections.shuffle(states);
    }
    
    public int getCorrectCount() {
        return correctCount;
    }

    public int getWrongCount() {
        return wrongCount;
    }
    
    public int getCount() {
        return states.size();
    }
    
    public boolean check(String capital) {
        index++;
        if((capital!= null)&&(capital.equals(states.get(index - 1)[1]))) {
            correctCount++;
            return true;
        } else {
            wrongCount++;
            return false;
        }
    }
    
}
