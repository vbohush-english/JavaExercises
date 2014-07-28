package net.bohush.exercises.chapter46.exercise05;

import java.rmi.RemoteException;
import java.rmi.registry.*;

public class RegisterWithRMIServer {

    public static void main(String[] args) {
        try {
            AddressesInterface obj = new AddressesInterfaceImpl();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("AddressesInterfaceImpl", obj);
            System.out.println("Addresses server " + obj + " registered");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
    
}
