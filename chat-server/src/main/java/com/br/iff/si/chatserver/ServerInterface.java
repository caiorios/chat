package com.br.iff.si.chatserver;

import com.br.iff.si.chatserver.model.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface ServerInterface extends Remote {
    
    public void connect(User user) throws RemoteException;
    public void disconnect(User user) throws RemoteException;
    public Vector<User> getUsers() throws RemoteException;
    public Vector<String> getUserNicks() throws RemoteException;
}