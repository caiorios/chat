/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Login.java
 *
 * Created on 31/03/2011, 08:04:16
 */

package com.br.iff.si.chatclient.gui;

import com.br.iff.si.chatserver.ServerInterface;
import com.br.iff.si.chatserver.model.User;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author acpm
 */
public class LoginApp extends javax.swing.JFrame {

    private ServerInterface serverInterface;

    /** Creates new form Login */
    public LoginApp() {
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
        
        this.getRootPane().setDefaultButton(bLogin);

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

        lServidor = new javax.swing.JLabel();
        tfServidor = new javax.swing.JTextField();
        lNome = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        bLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Login");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lServidor.setText("Servidor");

        tfServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfServidorActionPerformed(evt);
            }
        });

        lNome.setText("Nick");

        tfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeActionPerformed(evt);
            }
        });

        bLogin.setText("Login");
        bLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lServidor)
                            .addComponent(lNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfNome)
                            .addComponent(tfServidor, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 191, Short.MAX_VALUE)
                        .addComponent(bLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lServidor)
                    .addComponent(tfServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lNome))
                .addGap(18, 18, 18)
                .addComponent(bLogin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfServidorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfServidorActionPerformed

    private void tfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeActionPerformed

    private void bLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoginActionPerformed
        boolean flag = false;

        if ((!tfServidor.getText().equals("")) || (!tfNome.getText().equals(""))) {
            String serverLocate = tfServidor.getText();
            String nick = tfNome.getText();
            
            User user = new User();
            user.setNick(nick);
            
            try {
                user.setIp(this.getIpAddress());
            } catch (SocketException ex) {
                ex.printStackTrace();
            }

            try {
                this.serverInterface = (ServerInterface) Naming.lookup("rmi://"+ serverLocate +"/ChatServer");
                if (this.serverInterface != null) {
                    Vector<User> usersServer = this.serverInterface.getUsers();
                    for (User userServer : usersServer) {
                        if (!userServer.getNick().toLowerCase().equals(user.getNick().toLowerCase()) && usersServer.size() > 0) {
                            flag = true;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                    
                    if (flag) {
                        ChatApp chat = new ChatApp(this.serverInterface, user, serverLocate);
                        chat.setVisible(true);
                    } else if (usersServer.size() > 0) {
                        JOptionPane.showMessageDialog(null, "Já existe um usuário logado com esse nick, por favor, escolhe outro nick.", "Usuário existente", JOptionPane.ERROR_MESSAGE);
                    }

                    if (usersServer.isEmpty()) {
                        ChatApp chat = new ChatApp(this.serverInterface, user, serverLocate);
                        chat.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "O servidor informado não foi encontrado.", "Erro inesperado!", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NotBoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "NotBoundException.", "Erro inesperado!", JOptionPane.ERROR_MESSAGE);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "MalformedURLException.", "Erro inesperado!", JOptionPane.ERROR_MESSAGE);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "RemoteException.", "Erro inesperado!", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível se conectar ao servidor!", "Erro inesperado!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "É preciso informar o servidor que deseja se conectar e um nick!", "Servidor não encontrado!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bLogin;
    private javax.swing.JLabel lNome;
    private javax.swing.JLabel lServidor;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfServidor;
    // End of variables declaration//GEN-END:variables


    private String getIpAddress() throws SocketException {
        String ip = null;

        Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();

        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();

            if (networkInterface.getName().equals("eth0") || networkInterface.getName().equals("wlan0")) {
                Enumeration inetAddresses = networkInterface.getInetAddresses();

                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (inetAddress instanceof Inet4Address) {
                        ip = inetAddress.getHostAddress();
                    }
                }
            }
        }
        
        return ip;
    }
}