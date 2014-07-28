package net.bohush.exercises.chapter46.exercise07;

import java.rmi.*;

public interface ChatInterface extends Remote {
    
    public void connect(CallBack client) throws RemoteException;

    public void sendMessage(String message) throws RemoteException;
    
}