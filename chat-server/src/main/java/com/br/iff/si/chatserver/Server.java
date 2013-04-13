package com.br.iff.si.chatserver;

import com.br.iff.si.chatserver.model.User;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

@SuppressWarnings("serial")
public class Server extends UnicastRemoteObject implements ServerInterface {
    
    Vector<User> users;
    private Socket socket;

    public Server() throws RemoteException {
        this.users = new Vector<User>();
    }

    @Override
    public synchronized void connect(User user) throws RemoteException {
        this.users.add(user);
    }

    @Override
    public synchronized void disconnect(User user) throws RemoteException {
        if (!this.users.isEmpty()) {
            for (int indice = 0; indice < this.users.size(); indice++) {
                if (this.users.get(indice).getNick().equals(user.getNick())) {
                    this.users.remove(indice);
                }
            }
        }
    }

    @Override
    public synchronized Vector<User> getUsers() throws RemoteException {
        return this.users;
    }

    @Override
    public synchronized  Vector<String> getUserNicks() throws RemoteException {
        Vector<String> userNicks = new Vector<String>();

        if (!this.users.isEmpty()) {
            for (User user : this.users) {
                userNicks.add(user.getNick());
            }
        }

        return userNicks;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}