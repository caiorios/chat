    package com.br.iff.si.chatserver;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerInit {
    
    private static Vector<Socket> sockets = new Vector<Socket>();
    
    public static void main(String[] args) {
        Server server = null;

        try {
            //Inicializa o serviço RMI
            System.setProperty("java.rmi.server.hostname", getIpAddress());
            LocateRegistry.createRegistry(1099);
            server = new Server();
            Naming.rebind("ChatServer", server);

            //Lógica multithread de criação de sockets para comunicação entre os clientes
            ServerSocket serverSocket = new ServerSocket(5000);
            
            System.out.println("Servidor inicializado...");
            
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("CLIENTE COM IP "+ socket.getInetAddress().getHostAddress() +" SE CONECTOU AO SERVIDOR SOCKET...");
                
                sockets.add(socket);
                
                SocketView socketView = new SocketView(socket);
                socketView.start();
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public static String getIpAddress() {
        String ip = null;

        try {
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
        } catch (SocketException ex) {
            Logger.getLogger(ServerInit.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ip;
    }
    
    public static Vector<Socket> getSockets() {
        return sockets;
    }
}