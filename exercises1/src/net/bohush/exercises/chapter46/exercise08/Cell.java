package net.bohush.exercises.chapter46.exercise08;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import javax.swing.JPanel;

/**
 *
 * @author viktor
 */
public class Cell extends JPanel {
// marked indicates whether the cell has been used

    private boolean marked = false;
// row and column indicate where the cell appears on the board
    int row, column;
// The token for the cell
    private char token;
    private TicTacToeClientRMI ticTacToeClientRMI;

    /**
     * Construct a cell
     */
    public Cell(TicTacToeClientRMI ticTacToeClientRMI, final int row, final int column) {
        this.ticTacToeClientRMI = ticTacToeClientRMI;
        this.row = row;
        this.column = column;
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (Cell.this.ticTacToeClientRMI.getMyTurn() && !marked) {
                    setToken(Cell.this.ticTacToeClientRMI.getMarker());
                    try {
                        Cell.this.ticTacToeClientRMI.getTicTacToe().myMove(row, column, Cell.this.ticTacToeClientRMI.getMarker());
                    } catch (RemoteException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        setBorder(Cell.this.ticTacToeClientRMI.getLineBorder());
    }

    /**
     * Set token on a cell (mark a cell)
     */
    public void setToken(char c) {
        token = c;
        marked = true;
        repaint();
    }

    /**
     * Paint the cell to draw a shape for the token
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
// Draw the border
        g.drawRect(0, 0, getSize().width, getSize().height);
        if (token == 'X') {
            g.drawLine(10, 10, getSize().width - 10,
                    getSize().height - 10);
            g.drawLine(getSize().width - 10, 10, 10,
                    getSize().height - 10);
        } else if (token == 'O') {
            g.drawOval(10, 10, getSize().width - 20,
                    getSize().height - 20);
        }
    }
}
