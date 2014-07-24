package net.bohush.exercises.chapter46.exercise02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComputeLoanInterfaceClient extends JApplet {

    private ComputeLoanInterface loan;
    private boolean isStandalone;
    private JTextField jtfAnnualInterestRate = new JTextField();
    private JTextField jtfNumberOfYears = new JTextField();
    private JTextField jtfLoanAmount = new JTextField();
    private JTextArea jta = new JTextArea();

    public void init() {
        initializeRMI();
        JPanel jPanel1 = new JPanel(new GridLayout(3, 2, 5, 5));
        jPanel1.add(new JLabel("Annual Interest Rate"));
        jPanel1.add(jtfAnnualInterestRate);
        jPanel1.add(new JLabel("Number Of Years"));
        jPanel1.add(jtfNumberOfYears);
        jPanel1.add(new JLabel("Loan Amount"));
        jPanel1.add(jtfLoanAmount);
        JPanel jPanel2 = new JPanel(new BorderLayout(5, 5));
        jPanel2.add(jPanel1, BorderLayout.CENTER);
        JButton jButton1 = new JButton("Submit");
        jPanel2.add(jButton1, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(jPanel2, BorderLayout.NORTH);
        add(new JScrollPane(jta), BorderLayout.CENTER);

        jButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    double interest = Double.parseDouble(jtfAnnualInterestRate.getText());
                    int year = Integer.parseInt(jtfNumberOfYears.getText());
                    double loanAmount = Double.parseDouble(jtfLoanAmount.getText());
                    
                    double monthlyPayment = loan.getMonthlyPayment(interest, year, loanAmount);
                    double totalPayment = loan.getTotalPayment(interest, year, loanAmount);
                    
                    jta.append("Annual Interest Rate " + interest + "\n");
                    jta.append("Number Of Years " + year + "\n");
                    jta.append("Loan Amount " + loanAmount + "\n");
                    jta.append("monthlyPayment " + monthlyPayment + "\n");                
                    jta.append("totalPayment " + totalPayment + "\n");
                } catch (RemoteException ex) {
                    Logger.getLogger(ComputeLoanInterfaceClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }


    protected void initializeRMI() {
        String host = "";
        if (!isStandalone) {
            host = getCodeBase().getHost();
        }
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            loan = (ComputeLoanInterface) registry.lookup("ComputeLoanInterfaceImpl");
            System.out.println("Loan object " + loan + " found");
        } catch (NotBoundException | RemoteException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        ComputeLoanInterfaceClient applet = new ComputeLoanInterfaceClient();
        applet.isStandalone = true;
        JFrame frame = new JFrame();
        frame.setTitle("ComputeLoanInterfaceClient");
        frame.add(applet, BorderLayout.CENTER);
        frame.setSize(500, 300);
        applet.init();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
    }
}
