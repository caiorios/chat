package com.br.iff.si.chatclient.thread;

import com.br.iff.si.chatclient.gui.ChatApp;
import com.br.iff.si.chatclient.util.WindowChat;
import com.br.iff.si.chatserver.model.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;

public class ChatView extends Thread {

    private Socket socket;
    private Message message;

    public ChatView(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream input = new ObjectInputStream(this.socket.getInputStream());
                this.message = (Message) input.readObject();

                if (this.message != null) {
                    System.out.println("Opa tem mensagem!");
                    JTabbedPane tpChats = ChatApp.getTpChats();

                    if (ChatApp.getUser().getNick().equals(this.message.getAuthor())) {
                        System.out.println("É sou eu mesmo!!!");
                        boolean hasTabUser = false;

                        for (int indice = 0; indice < tpChats.getTabCount(); indice++) {
                            if (tpChats.getTitleAt(indice).equals(this.message.getRecipient())) {
                                hasTabUser = true;
                                tpChats.setSelectedIndex(indice);
                                ChatApp.setCurrentRecicipent(this.message.getRecipient());
                            }
                        }

                        if (!hasTabUser) {
                            ChatApp.setWindowChat(new WindowChat(this.message.getRecipient()));
                            tpChats.addTab(ChatApp.getWindowChat().getWindowTitle(), ChatApp.getWindowChat().getjPanel());
                            tpChats.setSelectedIndex(tpChats.getTabCount() - 1);
                            ChatApp.setCurrentRecicipent(ChatApp.getWindowChat().getWindowTitle());
                        }

                        if (ChatApp.getWindowChat() != null) {
                            ChatApp.getWindowChat().getjTextArea().append("<" + this.message.getAuthor() + ">: " + this.message.getContent() + "\n");
                        }
                    } else {
                        boolean hasTabUser = false;

                        for (int indice = 0; indice < tpChats.getTabCount(); indice++) {
                            if (tpChats.getTitleAt(indice).equals(this.message.getAuthor())) {
                                hasTabUser = true;
                                tpChats.setSelectedIndex(indice);
                                ChatApp.setCurrentRecicipent(this.message.getAuthor());
                            }
                        }

                        if (!hasTabUser) {
                            ChatApp.setWindowChat(new WindowChat(this.message.getAuthor()));
                            tpChats.addTab(ChatApp.getWindowChat().getWindowTitle(), ChatApp.getWindowChat().getjPanel());
                            tpChats.setSelectedIndex(tpChats.getTabCount() - 1);
                            ChatApp.setCurrentRecicipent(ChatApp.getWindowChat().getWindowTitle());
                        }

                        ChatApp.getWindowChat().getjTextArea().append("<" + this.message.getAuthor() + ">: " + this.message.getContent() + "\n");
                    }
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                Logger.getLogger(ChatView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                Logger.getLogger(ChatView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(ChatView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isTabOpened(String tabTittle) {
        boolean isTabOpened = false;

        for (int indice = 0; indice < ChatApp.getTpChats().getTabCount(); indice++) {
            if (ChatApp.getTpChats().getTitleAt(indice).equals(tabTittle)) {
                isTabOpened = true;
            }
        }

        return isTabOpened;
    }
}
