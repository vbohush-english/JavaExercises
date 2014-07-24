package net.bohush.exercises.chapter46.exercise03;

import java.rmi.RemoteException;
import java.rmi.registry.*;

public class RegisterWithRMIServer {

    public static void main(String[] args) {
        try {
            VisitCountInterface obj = new VisitCountInterfaceImpl();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("VisitCountInterfaceImpl", obj);
            System.out.println("Visit server " + obj + " registered");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
    
}
