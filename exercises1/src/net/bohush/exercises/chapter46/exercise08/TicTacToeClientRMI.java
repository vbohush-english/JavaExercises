package net.bohush.exercises.chapter46.exercise08;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class TicTacToeClientRMI extends JApplet {
// marker is used to indicate the token type

    private char marker;
// myTurn indicates whether the player can move now
    private boolean myTurn = false;
// Each cell can be empty or marked as 'O' or 'X'
    private Cell[][] cell;
// ticTacToe is the game server for coordinating with the players
    private TicTacToeInterface ticTacToe;
// Border for cells and panel
    private Border lineBorder = BorderFactory.createLineBorder(Color.yellow, 1);
    private JLabel jlblStatus = new JLabel("jLabel1");
    private JLabel jlblIdentification = new JLabel();
    boolean isStandalone = false;

    /**
     * Initialize the applet
     */
    public void init() {
        JPanel jPanel1 = new JPanel();
        jPanel1.setBorder(lineBorder);
        jPanel1.setLayout(new GridLayout(3, 3, 1, 1));
        add(jlblStatus, BorderLayout.SOUTH);
        add(jPanel1, BorderLayout.CENTER);
        add(jlblIdentification, BorderLayout.NORTH);

// Create cells and place cells in the panel
        cell = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                jPanel1.add(cell[i][j] = new Cell(this, i, j));
            }
        }
        try {
            initializeRMI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Initialize RMI
     */
    protected void initializeRMI() throws Exception {
        String host = "";
        if (!isStandalone) {
            host = getCodeBase().getHost();
        }
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            ticTacToe = (TicTacToeInterface) registry.lookup("TicTacToeImpl");
            System.out.println("Server object " + ticTacToe + " found");
        } catch (Exception ex) {
            System.out.println(ex);
        }
// Create callback for use by the server to control the client
        CallBackImpl callBackControl = new CallBackImpl(this);
        if ((marker = ticTacToe.connect((CallBack) callBackControl)) != ' ') {
            System.out.println("connected as " + marker + " player.");
            jlblIdentification.setText("You are player " + marker);
        } else {
            System.out.println("already two players connected as ");
            
        }
    }

    /**
     * Set variable myTurn to true or false
     */
    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }
    
    public boolean getMyTurn() {
        return myTurn;
    }

    public char getMarker() {
        return marker;
    }

    public TicTacToeInterface getTicTacToe() {
        return ticTacToe;
    }

    public Border getLineBorder() {
        return lineBorder;
    }
    
    
    
    
    /**
     * Set message on the status label
     */
    public void setMessage(String message) {
        jlblStatus.setText(message);
    }

    /**
     * Mark the specified cell using the token
     */
    public void mark(int row, int column, char token) {
        cell[row][column].setToken(token);

    }


    /**
     * Main method
     */
    public static void main(String[] args) {
        TicTacToeClientRMI applet = new TicTacToeClientRMI();
        applet.isStandalone = true;
        applet.init();
        applet.start();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TicTacToeClientRMI");
        frame.add(applet, BorderLayout.CENTER);
        frame.setSize(400, 320);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
