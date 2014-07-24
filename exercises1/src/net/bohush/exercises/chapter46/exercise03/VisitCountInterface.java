package net.bohush.exercises.chapter46.exercise03;

import java.rmi.*;

public interface VisitCountInterface extends Remote {
    
    public int getVisitCount() throws RemoteException;
    
}
