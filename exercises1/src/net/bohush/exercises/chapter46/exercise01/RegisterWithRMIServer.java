package net.bohush.exercises.chapter46.exercise01;

import java.rmi.RemoteException;
import java.rmi.registry.*;

public class RegisterWithRMIServer {

    public static void main(String[] args) {
        try {
            StudentServerInterface obj = new StudentServerInterfaceImpl();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("StudentServerInterfaceImpl", obj);
            System.out.println("Student server " + obj + " registered");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
    
}
