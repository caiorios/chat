package com.br.iff.si.chatserver;

import com.br.iff.si.chatserver.model.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketView extends Thread {
    
    private Socket socket;
    private ObjectInputStream inputStream;

    public SocketView(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                this.inputStream = new ObjectInputStream(this.socket.getInputStream());
                
                if (this.inputStream != null) {
                    Message message = (Message) this.inputStream.readObject();

                    System.out.println("=====================================================");
                    System.out.println("Chegou uma mensagem do ip " + this.socket.getInetAddress().getHostAddress() + "!");
                    System.out.println("De: " + message.getAuthor());
                    System.out.println("Para: " + message.getRecipient());
                    System.out.println("Conteudo: " + message.getContent());
                    System.out.println("=====================================================");
                    
                    for (Socket socket : ServerInit.getSockets()) {
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(message);
                    }
                }
                
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(SocketView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SocketView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
