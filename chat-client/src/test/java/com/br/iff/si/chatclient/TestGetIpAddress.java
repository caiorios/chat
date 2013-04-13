package com.br.iff.si.chatclient;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestGetIpAddress {
    public static void main(String[] args) {
//        printIpAddresses();
        
        System.out.println(getIpAddress());
    }

    public static void printIpAddresses() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();

                if (networkInterface.getName().equals("eth0") || networkInterface.getName().equals("wlan0")) {
                    Enumeration inetAddresses = networkInterface.getInetAddresses();

                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                        if (inetAddress instanceof Inet4Address) {
                            System.out.println("NAME: "+ inetAddress.getCanonicalHostName());
                            System.out.println("IP: "+ inetAddress.getHostAddress());
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(TestGetIpAddress.class.getName()).log(Level.SEVERE, null, ex);
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
                        if (inetAddress instanceof Inet4Address)
                            ip = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(TestGetIpAddress.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ip;
    }
}
