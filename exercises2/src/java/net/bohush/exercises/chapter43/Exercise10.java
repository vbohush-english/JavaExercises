package net.bohush.exercises.chapter43;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exercise10 {
    private String country;
    
    public Exercise10() {
    
    }

    public void setCountry(String country) {
        this.country = country;
        
    }

    public String getCountry() {
        return country;
    }
    
    public String getImage() {
        return "<img src = \"image/" + country + ".gif\" align=left>";
    }
    
    public String getDescription() {
        try {
            String result = "";
            Scanner input = new Scanner(new File("//home//viktor//java//book//resourse//" + country + ".txt"));
            while (input.hasNext()) {
                result += input.nextLine() + "\n";
            }
            return result;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Exercise10.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
