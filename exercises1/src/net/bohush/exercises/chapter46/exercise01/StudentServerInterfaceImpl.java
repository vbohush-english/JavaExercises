package net.bohush.exercises.chapter46.exercise01;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class StudentServerInterfaceImpl extends UnicastRemoteObject implements StudentServerInterface {

    private HashMap<String, Double> scores = new HashMap<>();
    private int count = 0;
    private int maxCount = 10;

    public StudentServerInterfaceImpl() throws RemoteException {
        initializeStudent();
    }

    protected void initializeStudent() {
        scores.put("John", 90.5);
        scores.put("Michael", 100.0);
        scores.put("Michelle", 98.5);
    }

    @Override
    public double findScore(String name) throws RemoteException {
        if(count < maxCount) {
            count++;
            Double d = scores.get(name);
            if (d == null) {
                System.out.println("Student " + name + " is not found, count = " + count);
                count--;
                return -1;
            } else {
                System.out.println("Student " + name + "\'s score is " + d + ", count = " + count);
                count--;
                return d;
            }
        } else {
            return -2;
        }
    }
}
