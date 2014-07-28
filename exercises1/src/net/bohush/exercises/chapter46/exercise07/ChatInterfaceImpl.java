package net.bohush.exercises.chapter46.exercise07;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.ArrayList;

public class ChatInterfaceImpl extends UnicastRemoteObject implements ChatInterface {

    private ArrayList<CallBack> clients = new ArrayList<>();
    
    
    public ChatInterfaceImpl() throws RemoteException {

    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        for (int i = 0; i < clients.size(); i++) {
            try {
                clients.get(i).receiveMessage(message);    
            } catch (ConnectException e) {
                clients.remove(i);
                i--;
            }
            
        }
    }

    @Override
    public void connect(CallBack client) throws RemoteException{
        clients.add(client);
    }
    
    public static void main(String[] args) {
        try {
            ChatInterface obj = new ChatInterfaceImpl();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("ChatInterfaceImpl", obj);
            System.out.println("Server " + obj + " registered");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
