package com.br.iff.si.chatclient.gui;

import com.br.iff.si.chatclient.component.JListAction;
import com.br.iff.si.chatclient.thread.ChatView;
import com.br.iff.si.chatclient.thread.UsersOnlineView;
import com.br.iff.si.chatclient.util.WindowChat;
import com.br.iff.si.chatserver.ServerInterface;
import com.br.iff.si.chatserver.model.Message;
import com.br.iff.si.chatserver.model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ChatApp extends javax.swing.JFrame {

    private Socket socket;
    private ObjectOutputStream output;
    private static User user;
    private ServerInterface serverInterface;
    private JListAction alOnlineUsers;
    private static String currentRecicipent;
    private String serverLocate;
    private static WindowChat windowChat;

    public ChatApp(final ServerInterface serverInterface, final User user, String serverLocate) {
        this.serverInterface = serverInterface;
        setUser(user);
        currentRecicipent = "";
        this.serverLocate = serverLocate;
        
        try {
            this.socket = new Socket(this.serverLocate, 5000);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ChatApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LoginApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LoginApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LoginApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();
        
        this.alOnlineUsers = new JListAction();
        this.alOnlineUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!user.getNick().equals(alOnlineUsers.getSelectedValue())) {
                    try {
                        for (User serverUser : serverInterface.getUsers()) {
                            if (serverUser.getNick().equals(alOnlineUsers.getSelectedValue())) {
                                boolean hasTabUser = false;
                                for (int indice = 0; indice < getTpChats().getTabCount(); indice++) {
                                    System.out.println("Aba - "+ getTpChats().getTitleAt(indice) +" | "+ "serverUser - "+ serverUser.getNick());
                                    if (getTpChats().getTitleAt(indice).equals(serverUser.getNick())) {
                                        hasTabUser = true;
                                        getTpChats().setSelectedIndex(indice);
                                        setCurrentRecicipent(serverUser.getNick());
                                    }
                                }

                                if (!hasTabUser) {
                                    setWindowChat(new WindowChat(serverUser.getNick()));
                                    getTpChats().addTab(getWindowChat().getWindowTitle(), getWindowChat().getjPanel());
                                    getTpChats().setSelectedIndex(getTpChats().getTabCount() - 1);
                                    setCurrentRecicipent(getWindowChat().getWindowTitle());
                                }
                            }
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(ChatApp.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ChatApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        jScrollPane1.setViewportView(this.alOnlineUsers);

        try {
            this.serverInterface.connect(getUser());
        } catch (RemoteException ex) {
            Logger.getLogger(ChatApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        UsersOnlineView usersOnlineView = new UsersOnlineView(this.serverInterface, this.alOnlineUsers);
        usersOnlineView.start();
        
        ChatView chatView = new ChatView(this.socket);
        chatView.start();
        
        this.getRootPane().setDefaultButton(this.bEnviar);

        setLocationRelativeTo(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        lOnlineUsers = new com.br.iff.si.chatclient.component.JListAction();
        tfMensagem = new javax.swing.JTextField();
        bEnviar = new javax.swing.JButton();
        tpChats = new javax.swing.JTabbedPane();
        pBoasVindas = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane1.setViewportView(lOnlineUsers);

        bEnviar.setText("Enviar");
        bEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEnviarActionPerformed(evt);
            }
        });

        tpChats.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tpChatsStateChanged(evt);
            }
        });

        pBoasVindas.setBackground(new java.awt.Color(254, 254, 254));

        javax.swing.GroupLayout pBoasVindasLayout = new javax.swing.GroupLayout(pBoasVindas);
        pBoasVindas.setLayout(pBoasVindasLayout);
        pBoasVindasLayout.setHorizontalGroup(
            pBoasVindasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );
        pBoasVindasLayout.setVerticalGroup(
            pBoasVindasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 522, Short.MAX_VALUE)
        );

        tpChats.addTab("Boas-Vindas", pBoasVindas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tpChats, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tfMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(bEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpChats, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bEnviar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEnviarActionPerformed
        if (!this.tfMensagem.getText().equals("") && !getCurrentRecicipent().equals("")) {
            Message message = new Message();
            message.setAuthor(getUser().getNick());
            message.setRecipient(getCurrentRecicipent());
            message.setContent(this.tfMensagem.getText());

            try {
                this.output = new ObjectOutputStream(this.socket.getOutputStream());
                this.output.writeObject(message);
            } catch (IOException ex) {
                Logger.getLogger(ChatApp.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.tfMensagem.setText("");
            this.tfMensagem.grabFocus();
        }
    }//GEN-LAST:event_bEnviarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            this.serverInterface.disconnect(user);
            System.out.println(getUser().getNick() +" ficou offline!");
        } catch (RemoteException ex) {
            Logger.getLogger(ChatApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void tpChatsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tpChatsStateChanged
        JTabbedPane pane = (JTabbedPane) evt.getSource();
        setCurrentRecicipent(pane.getTitleAt(pane.getSelectedIndex()));
    }//GEN-LAST:event_tpChatsStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEnviar;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lOnlineUsers;
    private javax.swing.JPanel pBoasVindas;
    private javax.swing.JTextField tfMensagem;
    private static javax.swing.JTabbedPane tpChats;
    // End of variables declaration//GEN-END:variables

    public ServerInterface getServerInterface() {
        return this.serverInterface;
    }

    public static JTabbedPane getTpChats() {
        return tpChats;
    }
    
    public static WindowChat getWindowChat() {
        return windowChat;
    }

    public static void setWindowChat(WindowChat windowChat) {
        ChatApp.windowChat = windowChat;
    }

    public static String getCurrentRecicipent() {
        return currentRecicipent;
    }

    public static void setCurrentRecicipent(String CurrentRecicipent) {
        ChatApp.currentRecicipent = CurrentRecicipent;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        ChatApp.user = user;
    }
}
