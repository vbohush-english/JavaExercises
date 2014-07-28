package net.bohush.exercises.chapter46.exercise05;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AddressesInterfaceClient extends JApplet {

    private AddressesInterface addresses;
    private boolean isStandalone = false;
    
    private JTextField jtfName = new JTextField();
    private JTextField jtfStreet = new JTextField();
    private JTextField jtfCity = new JTextField();
    private JTextField jtfState = new JTextField();
    private JTextField jtfZip = new JTextField();
    private JLabel jlblName = new JLabel("Name");
    private JLabel jlblStreet = new JLabel("Street");
    private JLabel jlblCity = new JLabel("City");
    private JLabel jlblState = new JLabel("State");
    private JLabel jlblZip = new JLabel("Zip");
    private JButton jbtnAdd = new JButton("Add");
    private JButton jbtnFirst = new JButton("First");
    private JButton jbtnNext = new JButton("Next");
    private JButton jbtnPrevious = new JButton("Previous");
    private JButton jbtnLast = new JButton("Last");

    
    public void updateAddress(Address address) {
        if (address != null) {
            jtfName.setText(address.getName());
            jtfStreet.setText(address.getStreet());
            jtfCity.setText(address.getCity());
            jtfState.setText(address.getState());
            jtfZip.setText(address.getZip());
        } else {
            jtfName.setText("");
            jtfStreet.setText("");
            jtfCity.setText("");
            jtfState.setText("");
            jtfZip.setText("");
        }

    }
     
    public void init() {
        initializeRMI();
        jbtnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Address address = new Address(jtfName.getText(), jtfStreet.getText(), jtfCity.getText(), jtfState.getText(), jtfZip.getText());
                try {
                    addresses.add(address);
                    updateAddress(addresses.getLast());
                } catch (RemoteException ex) {
                    Logger.getLogger(AddressesInterfaceClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jbtnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateAddress(addresses.getFirst());
                } catch (RemoteException ex) {
                    Logger.getLogger(AddressesInterfaceClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jbtnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateAddress(addresses.getNext());
                } catch (RemoteException ex) {
                    Logger.getLogger(AddressesInterfaceClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jbtnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateAddress(addresses.getPrevious());
                } catch (RemoteException ex) {
                    Logger.getLogger(AddressesInterfaceClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jbtnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateAddress(addresses.getLast());
                } catch (RemoteException ex) {
                    Logger.getLogger(AddressesInterfaceClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jlblName.setHorizontalAlignment(SwingConstants.RIGHT);
        jlblStreet.setHorizontalAlignment(SwingConstants.RIGHT);
        jlblCity.setHorizontalAlignment(SwingConstants.RIGHT);
        jlblState.setHorizontalAlignment(SwingConstants.RIGHT);
        jlblZip.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel jPanel1 = new JPanel(new GridLayout(3, 1, 5, 5));
        jPanel1.add(jlblName);
        jPanel1.add(jlblStreet);
        jPanel1.add(jlblCity);

        JPanel jPanel2 = new JPanel(new GridLayout(1, 4, 5, 5));
        jPanel2.add(jlblState);
        jPanel2.add(jtfState);
        jPanel2.add(jlblZip);
        jPanel2.add(jtfZip);

        JPanel jPanel3 = new JPanel(new GridLayout(1, 2, 5, 5));
        jPanel3.add(jtfCity);
        jPanel3.add(jPanel2);

        JPanel jPanel4 = new JPanel(new GridLayout(3, 1, 5, 5));
        jPanel4.add(jtfName);
        jPanel4.add(jtfStreet);
        jPanel4.add(jPanel3);

        JPanel jPanel5 = new JPanel(new BorderLayout(5, 5));
        jPanel5.add(jPanel1, BorderLayout.WEST);
        jPanel5.add(jPanel4, BorderLayout.CENTER);

        JPanel jPanel6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel6.add(jbtnAdd);
        jPanel6.add(jbtnFirst);
        jPanel6.add(jbtnNext);
        jPanel6.add(jbtnPrevious);
        jPanel6.add(jbtnLast);

        JPanel jPanel7 = new JPanel(new BorderLayout(10, 10));
        jPanel7.setBorder(new EmptyBorder(10, 10, 10, 10));
        jPanel7.add(jPanel5, BorderLayout.CENTER);
        jPanel7.add(jPanel6, BorderLayout.SOUTH);
        add(jPanel7);

        try {
            updateAddress(addresses.getFirst());
        } catch (RemoteException ex) {
            Logger.getLogger(AddressesInterfaceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
     

    }

    protected void initializeRMI() {
        String host = "";
        if (!isStandalone) {
            host = getCodeBase().getHost();
        }
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            addresses = (AddressesInterface) registry.lookup("AddressesInterfaceImpl");
            System.out.println("Loan object " + addresses + " found");
        } catch (NotBoundException | RemoteException ex) {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args) {        
        AddressesInterfaceClient applet = new AddressesInterfaceClient();
        applet.isStandalone = true;
        JFrame frame = new JFrame();
        frame.setTitle("AddressesInterfaceClient");
        frame.add(applet, BorderLayout.CENTER);
        frame.setSize(600, 180);
        applet.init();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
    }

}
