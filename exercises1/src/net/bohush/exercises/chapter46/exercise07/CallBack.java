package net.bohush.exercises.chapter46.exercise07;

import java.rmi.*;

public interface CallBack extends Remote {

    public void receiveMessage(String message) throws RemoteException;

}
