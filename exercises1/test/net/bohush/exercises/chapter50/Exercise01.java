package net.bohush.exercises.chapter50;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Exercise01 {

    private String string = "Welcome to Java";

    public Exercise01() {
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
    public void testLength() {
        assertEquals(string.length(), 15);
    }

    @Test
    public void testCharAt() {
        assertEquals(string.charAt(11), 'J');
    }

    @Test
    public void testSubstring() {
        assertEquals(string.substring(11), "Java");
        assertEquals(string.substring(8, 10), "to");
    }
    
    @Test
    public void testIndexOf() {
        assertEquals(string.indexOf("Java"), 11);
    }
    
}
