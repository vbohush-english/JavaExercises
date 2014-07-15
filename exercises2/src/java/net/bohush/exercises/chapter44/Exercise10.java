package net.bohush.exercises.chapter44;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Exercise10 {
    private int[] deck = new int[54];
    
    public Exercise10() {
        changeCards();
    }
    
    public void changeCards() {
        for (int i = 0; i < deck.length; i++) {
            deck[i] = i + 1;
        }

        for (int i = 0; i < deck.length; i++) {
            int index = (int) (Math.random() * deck.length);
            int temp = deck[i];
            deck[i] = deck[index];
            deck[index] = temp;
        }
    }
    
    public int getCard0() {
        return deck[0];
    }  
    
    public int getCard1() {
        return deck[1];
    }

    public int getCard2() {
        return deck[2];
    }
    
    public int getCard3() {
        return deck[3];
    }
    
}
