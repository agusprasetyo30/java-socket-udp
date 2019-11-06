/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TugasSocket;

import ClientServerSocketUDP.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author ciprut
 */
public class InfoServerUDP {
    private final int INFO_PORT=50000;
    private String datafromClient;
    private Socket socket;
    
    public InfoServerUDP() {
        DatagramSocket serverSocket;

        try {
            serverSocket = new DatagramSocket(INFO_PORT);
            System.out.println("Server telah siap...");
            
            while (true) {

                boolean isQUIT = false;
                while (!isQUIT) {
                    byte[] byteFromClient = new byte[1024];
                    byte[] byteToClient = new byte[1024];
                    
                    DatagramPacket receivePacket = new DatagramPacket(byteFromClient, byteFromClient.length);

                        serverSocket.receive(receivePacket);

                        InetAddress IPAddress = receivePacket.getAddress();
                        int port = receivePacket.getPort();

                        String data = new String(receivePacket.getData());

                        if (data.startsWith("TIME")) {
                           String DateNow = new String(new Date().toString());
                           byteToClient = DateNow.getBytes();

                        } else if (data.startsWith("NET")) {
                          String hostname = new String(InetAddress.getByName("localhost").toString());
                          byteToClient = hostname.getBytes();

                        } else if (data.startsWith("QUIT")) {
                          isQUIT = true;
                          String thanks = new String("Terima kasih!");
                          byteToClient = thanks.getBytes();
                        
                        } else if (data.startsWith("HALO")) {
                           isQUIT = true;
                           datafromClient = new String("HALO JUGA, SELAMAT DATANG DI SISTEM INI :)");
                           byteToClient = datafromClient.getBytes();
                           
                        } else if (data.startsWith("SIAPA KAMU")) {
                           isQUIT = true;
                           datafromClient = new String("AKU ADALAH SEBUAH SISTEM SOCKET");
                           byteToClient = datafromClient.getBytes();
                        
                        } else if (data.startsWith("SIAPA YANG MEMBUATMU")) {
                           isQUIT = true;
                           datafromClient = new String("Yang membuatku adalah Agus Prasetyo uwu");
                           byteToClient = datafromClient.getBytes();
                        
                        } else if (data.startsWith("AGUS ADALAH")) {
                           isQUIT = true;
                           datafromClient = new String("Seorang Web Developer (Amiiin)");
                           byteToClient = datafromClient.getBytes();
                        
                        } else {
                           isQUIT = true;
                           datafromClient = new String("Keyword yang anda masukan salah");
                           byteToClient = datafromClient.getBytes();

                        }
                        
                        
                        DatagramPacket sendPacket = new DatagramPacket(byteToClient, byteToClient.length, IPAddress, port);
                        serverSocket.send(sendPacket);
                    }
                    System.out.println("Hub. client tertutup..");
                }
            }

            catch (IOException ioe) {
                System.out.print("error: " + ioe);
            }
            catch (Exception e) {
                System.out.print("error: " + e);
            }
        }
        public static void main(String[] args) {
            new InfoServerUDP();
    }
}
