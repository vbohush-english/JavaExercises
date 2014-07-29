/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bohush.exercises.chapter46.exercise08;

import java.rmi.*;

public interface CallBack extends Remote {

    /**
     * The server notifies the client for taking a turn
     */
    public void takeTurn(boolean turn) throws RemoteException;

    /**
     * The server sends a message to be displayed by the client
     */
    public void notify(java.lang.String message)
            throws RemoteException;

    /**
     * The server notifies a client of the other player's move
     */
    public void mark(int row, int column, char token)
            throws RemoteException;
}
