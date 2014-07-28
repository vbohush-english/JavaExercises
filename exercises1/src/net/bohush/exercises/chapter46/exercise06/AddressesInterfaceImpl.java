package net.bohush.exercises.chapter46.exercise06;

import java.rmi.*;
import java.rmi.server.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AddressesInterfaceImpl extends UnicastRemoteObject implements AddressesInterface {

    private ArrayList<Address> addresses = new ArrayList<>();
    private int position = 0;
    private Statement statement;

    public AddressesInterfaceImpl() throws RemoteException {
        try {
            initializeDB();
            ResultSet rset = statement.executeQuery("select firstname, street, city, state, zip from Address;");
            while(rset.next()) {
                addresses.add(new Address(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressesInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            statement.execute("insert into Address (firstname, street, city, state, zip) values (\'" +
                    address.getName() + "\', \'" +
                    address.getStreet() + "\', \'" +
                    address.getCity()+ "\', \'" +
                    address.getState()+ "\', \'" +
                    address.getZip()+ "\');");
            addresses.add(address);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
            statement = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
