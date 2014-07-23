package net.bohush.exercises.chapter46.exercise01;

import java.rmi.*;

public interface StudentServerInterface extends Remote {

    public double findScore(String name) throws RemoteException;
    
}
