package net.bohush.exercises.chapter46.exercise03;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.*;

public class VisitCountInterfaceClient extends JApplet {

    private static final long serialVersionUID = 1L;
    private JLabel jlblCount = new JLabel("", JLabel.HORIZONTAL);
    private boolean isStandalone = false;

    public void init() {
        add(jlblCount);

        String host = "";
        if (!isStandalone) {
            host = getCodeBase().getHost();
        }
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            VisitCountInterface visitCount = (VisitCountInterface) registry.lookup("VisitCountInterfaceImpl");
            System.out.println("Visit Count object " + visitCount + " found");
            jlblCount.setText("You are visitor number " + visitCount.getVisitCount());
        } catch (NotBoundException | RemoteException ex) {
            System.out.println(ex);
        }
        


    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("VisitCountInterfaceClient");
        VisitCountInterfaceClient applet = new VisitCountInterfaceClient();
        applet.isStandalone = true;
        frame.add(applet, java.awt.BorderLayout.CENTER);
        applet.init();
        applet.start();
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
