package net.bohush.exercises.chapter46.exercise03;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.rmi.*;
import java.rmi.server.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisitCountInterfaceImpl extends UnicastRemoteObject implements VisitCountInterface {

    private RandomAccessFile raf;
    private int count;

    public VisitCountInterfaceImpl() throws RemoteException {
        try {
            raf = new RandomAccessFile("count.dat", "rw");
            if (raf.length() == 0) {
                count = 0;
            } else {
                count = raf.readInt();
            }
        } catch (IOException ex) {
            Logger.getLogger(VisitCountInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getVisitCount() throws RemoteException {
        count++;
        try {
            raf.seek(0);
            raf.writeInt(count);
        } catch (IOException ex) {
            Logger.getLogger(VisitCountInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

}
