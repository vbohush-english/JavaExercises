package net.bohush.exercises.chapter46.exercise04;

import java.rmi.*;

public interface AddressesInterface extends Remote {
    
    public Address getFirst() throws RemoteException;
    
    public Address getLast() throws RemoteException;
    
    public Address getNext() throws RemoteException;
    
    public Address getPrevious() throws RemoteException;
    
    public void add(Address address) throws RemoteException;
    
}