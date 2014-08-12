package net.bohush.exercises.chapter50;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Exercise04 {

    public Exercise04() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testBmiStatus() {
        for (int i = 1; i < 300; i++) {
            net.bohush.exercises.chapter10.Exercise02 exercise02 = new net.bohush.exercises.chapter10.Exercise02("Tratata", i, 70);
            double bmi = exercise02.getBMI();
            if (bmi < 18.5) {
                assertEquals(exercise02.getStatus(), "Underweight");
            } else if (bmi < 25) {
                assertEquals(exercise02.getStatus(), "Normal");
            } else if (bmi < 30) {
                assertEquals(exercise02.getStatus(), "Overweight");
            } else {
                assertEquals(exercise02.getStatus(), "Obese");
            }
        }
    }

    @Test
    public void testBmi() {
        net.bohush.exercises.chapter10.Exercise02 exercise02 = new net.bohush.exercises.chapter10.Exercise02("Tratata", 50, 70);
        assertEquals(exercise02.getStatus(), "Underweight");
        exercise02 = new net.bohush.exercises.chapter10.Exercise02("Tratata", 150, 70);
        assertEquals(exercise02.getStatus(), "Normal");
        exercise02 = new net.bohush.exercises.chapter10.Exercise02("Tratata", 200, 70);
        assertEquals(exercise02.getStatus(), "Overweight");
        exercise02 = new net.bohush.exercises.chapter10.Exercise02("Tratata", 250, 70);
        assertEquals(exercise02.getStatus(), "Obese");
    }    
}
