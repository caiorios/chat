package com.br.iff.si.chatclient.thread;

import com.br.iff.si.chatclient.component.JListAction;
import com.br.iff.si.chatserver.ServerInterface;
import com.br.iff.si.chatserver.model.User;
import java.rmi.RemoteException;
import java.util.Vector;


public class UsersOnlineView extends Thread {

    private ServerInterface serverInterface;
    private JListAction alOnlineUsers;

    public UsersOnlineView(ServerInterface serverInterface, JListAction alOnlineUsers) {
        this.serverInterface = serverInterface;
        this.alOnlineUsers = alOnlineUsers;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Vector<User> users = this.serverInterface.getUsers();

                this.alOnlineUsers.clearSelection();
                this.alOnlineUsers.removeAll();

                if (users != null) {
                    this.alOnlineUsers.setListData(this.serverInterface.getUserNicks());
                    this.alOnlineUsers.setSelectedIndex(this.alOnlineUsers.getSelectedIndex());
                }
                
                Thread.sleep(5000);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
