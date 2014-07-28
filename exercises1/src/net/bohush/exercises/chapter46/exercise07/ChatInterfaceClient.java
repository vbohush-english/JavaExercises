package net.bohush.exercises.chapter46.exercise07;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChatInterfaceClient extends JApplet {

    private ChatInterface chat;
    private boolean isStandalone = false;
    private long lastMessage = 0;
    
    private JTextArea jta = new JTextArea();
    private JTextField jTextField1 = new JTextField();
    private JTextField jTextField2 = new JTextField();

    public void init() {
        initializeRMI();
        setLayout(new BorderLayout(5, 5));
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        jta.setWrapStyleWord(true);
        jta.setLineWrap(true);
        jta.setEditable(false);
        jta.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        JScrollPane jsp = new JScrollPane(jta);

        jsp.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });

        mainPanel.add(jsp, BorderLayout.CENTER);

        JPanel jPanel1 = new JPanel(new GridLayout(2, 1, 5, 5));
        JPanel jPanel2 = new JPanel(new BorderLayout(5, 5));
        jPanel2.add(new JLabel("Name "), BorderLayout.WEST);
        jPanel2.add(jTextField1, BorderLayout.CENTER);
        JPanel jPanel3 = new JPanel(new BorderLayout(5, 5));
        jPanel3.add(new JLabel("Enter text "), BorderLayout.WEST);
        jPanel3.add(jTextField2, BorderLayout.CENTER);
        jPanel1.add(jPanel2);
        jPanel1.add(jPanel3);
        mainPanel.add(jPanel1, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);

        jTextField2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTextField1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter your name!", "Waring", JOptionPane.WARNING_MESSAGE);
                    jTextField1.requestFocus();
                } else if (jTextField2.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter message!", "Waring", JOptionPane.WARNING_MESSAGE);
                    jTextField2.requestFocus();
                } else {
                    try {
                        chat.sendMessage(jTextField1.getText() + ": " + jTextField2.getText());
                        jTextField2.setText("");
                    } catch (RemoteException ex) {
                        Logger.getLogger(ChatInterfaceClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

    }
    
    

    public void receiveMessage(String message) {
        jta.append(message + "\n");
    }

    protected void initializeRMI() {
        String host = "";
        if (!isStandalone) {
            host = getCodeBase().getHost();
        }
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            chat = (ChatInterface) registry.lookup("ChatInterfaceImpl");
            System.out.println("Chat object " + chat + " found");
            CallBackImpl callBackControl = new CallBackImpl(this);
            chat.connect((CallBack)callBackControl);
        } catch (NotBoundException | RemoteException ex) {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args) {        
        ChatInterfaceClient applet = new ChatInterfaceClient();
        applet.isStandalone = true;
        JFrame frame = new JFrame();
        frame.setTitle("ChatInterfaceClient");
        frame.add(applet, BorderLayout.CENTER);
        frame.setSize(800, 500);
        applet.init();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
    }

}
