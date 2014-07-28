package net.bohush.exercises.chapter46.exercise07;

import java.rmi.*;
import java.rmi.server.*;

public class CallBackImpl extends UnicastRemoteObject implements CallBack {

    private ChatInterfaceClient thisClient;

    public CallBackImpl(Object client) throws RemoteException {
        thisClient = (ChatInterfaceClient) client;
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        thisClient.receiveMessage(message);
    }
}
