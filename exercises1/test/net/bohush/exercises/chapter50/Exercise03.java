package net.bohush.exercises.chapter50;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Exercise03 {

    public Exercise03() {
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
    public void testIsPrime() {
        assertTrue(net.bohush.exercises.chapter05.Exercise10.isPrime(23));
        assertTrue(net.bohush.exercises.chapter05.Exercise10.isPrime(67));
        assertTrue(net.bohush.exercises.chapter05.Exercise10.isPrime(109));
        assertFalse(net.bohush.exercises.chapter05.Exercise10.isPrime(24));
        assertFalse(net.bohush.exercises.chapter05.Exercise10.isPrime(68));
        assertFalse(net.bohush.exercises.chapter05.Exercise10.isPrime(110));
    }

}
