package net.bohush.exercises.chapter50;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Exercise02 {

    private HashSet<Integer> hashSet = new HashSet<>();

    public Exercise02() {
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
    public void testAdd() {
        hashSet.clear();
        hashSet.add(1);
        hashSet.add(5);
        hashSet.add(26);
        assertEquals(hashSet.size(), 3);
        assertTrue(hashSet.contains(1));
        assertTrue(hashSet.contains(5));
        assertTrue(hashSet.contains(26));
    }

    @Test
    public void testRemove() {
        hashSet.add(147);
        assertTrue(hashSet.contains(147));
        hashSet.remove(147);
        assertFalse(hashSet.contains(147));
    }
    
    @Test
    public void testAddAll() {
        hashSet.clear();
        List<Integer> list = Arrays.asList(new Integer[]{43, 56, 34});
        hashSet.addAll(list);
        assertEquals(hashSet.size(), list.size());
        for (Integer integer : list) {
            assertTrue(hashSet.contains(integer));
        }        
    }
    
    @Test
    public void testRemoveAll() {
        hashSet.clear();
        hashSet.add(34);
        hashSet.add(-34);
        hashSet.add(54);  
        hashSet.add(-534);  
        hashSet.removeAll(Arrays.asList(new Integer[]{-34, 54}));
        assertTrue(hashSet.contains(34));
        assertTrue(hashSet.contains(-534));
        assertFalse(hashSet.contains(-34));
        assertFalse(hashSet.contains(-54));
        assertEquals(hashSet.size(), 2);
    }
    
    @Test
    public void testSize() {
        hashSet.clear();
        assertEquals(hashSet.size(), 0);
        hashSet.add(3);
        assertEquals(hashSet.size(), 1);
        hashSet.add(4563);
        assertEquals(hashSet.size(), 2);        
        hashSet.remove(3);
        assertEquals(hashSet.size(), 1);        
        hashSet.remove(4563);
        assertEquals(hashSet.size(), 0);                
    }
    
    @Test
    public void testIsEmpty() {
        hashSet.clear();
        assertTrue(hashSet.isEmpty());
        hashSet.add(987);
        assertFalse(hashSet.isEmpty());
    }
    
    @Test
    public void testContains() {
        hashSet.clear();
        hashSet.add(147);
        assertTrue(hashSet.contains(147));
        hashSet.remove(147);
        assertFalse(hashSet.contains(147));
    }    
    
}
