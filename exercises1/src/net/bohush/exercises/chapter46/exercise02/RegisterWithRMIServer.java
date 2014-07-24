package net.bohush.exercises.chapter46.exercise02;

import java.rmi.RemoteException;
import java.rmi.registry.*;

public class RegisterWithRMIServer {

    public static void main(String[] args) {
        try {
            ComputeLoanInterface obj = new ComputeLoanInterfaceImpl();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("ComputeLoanInterfaceImpl", obj);
            System.out.println("Loan server " + obj + " registered");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
    
}
