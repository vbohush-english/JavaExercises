package net.bohush.exercises.chapter46.exercise04;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class AddressesInterfaceImpl extends UnicastRemoteObject implements AddressesInterface {

    private ArrayList<Address> addresses = new ArrayList<>();
    private int position = 0;

    public AddressesInterfaceImpl() throws RemoteException {
        addresses.add(new Address("Viktor", "Shevchenka", "Lviv", "Lviv", "12343"));
        addresses.add(new Address("Andriy", "Stysa", "Ternopil", "Ternopil", "65456"));
        addresses.add(new Address("Slavik", "Ivasuka", "Kyiv", "Kyiv", "87878"));
    }

    @Override
    public Address getFirst() throws RemoteException {
        if(addresses.size() > 0) {
            position = 0;
            return addresses.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Address getLast() throws RemoteException {
        if(addresses.size() > 0) {
            position = addresses.size() - 1;
            return addresses.get(addresses.size() - 1);
        } else {
            return null;
        }
    }

    @Override
    public Address getNext() throws RemoteException {
        if(addresses.size() > 0) {
            if(position < (addresses.size() - 1)) {
                position++;
            }
            return addresses.get(position);
        } else {
            return null;
        }
    }

    @Override
    public Address getPrevious() throws RemoteException {
        if(addresses.size() > 0) {
            if(position > 0) {
                position--;
            }
            return addresses.get(position);
        } else {
            return null;
        }
    }

    @Override
    public void add(Address address) throws RemoteException {
        addresses.add(address);
    }

}
