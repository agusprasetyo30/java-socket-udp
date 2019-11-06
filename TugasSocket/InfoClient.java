/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TugasSocket;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 *
 * @author gokpraz
 */
public class InfoClient {

   private final int INFO_PORT = 50000;
   private final String TargetHost = "localhost";
   private final String QUIT = "QUIT";
   private DatagramSocket clientSocket;
   private ArrayList dataClient = new ArrayList();
   private ArrayList dataServer = new ArrayList();
   
   public InfoClient() {
      
   }
   
   public void setData(String data)
   {
      try {
         clientSocket = new DatagramSocket();

         InetAddress IPAddress = InetAddress.getByName(TargetHost);

         boolean isQuit = false;
//         while (!isQuit) {
            byte[] byteFromServer = new byte[1024];
            byte[] byteToServer = new byte[1024];

            
            InputStream inputStream = new ByteArrayInputStream(data.getBytes(Charset.forName("UTF-8")));
            
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String cmd = bufferedReader.readLine();
            cmd = cmd.toUpperCase();
            isQuit = cmd.equals(QUIT);
            byteToServer = cmd.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(byteToServer, byteToServer.length, IPAddress, INFO_PORT);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(byteFromServer, byteFromServer.length);

            clientSocket.receive(receivePacket);

            String result = new String(receivePacket.getData());
            String isiClient = "[ Client ] : " + data;
            String isiServer = "[ Server ] : " + result;
            
            System.out.println(isiClient);
            System.out.println(isiServer);
            
            this.setDataClient(isiClient);
            this.setDataServer(isiServer);
            
//         }

         clientSocket.close();
      } catch (IOException ioe) {
         System.out.println("Error:" + ioe);
      } catch (Exception e) {
         System.out.println("Error:" + e);
      }
   }
   
   private void setDataServer(String data)
   {
      this.dataServer.add(data);
   }
   
   private void setDataClient(String data)
   {
      this.dataClient.add(data);
   }
   
   public ArrayList getDataServer()
   {
      return dataServer;
   }
   
   public ArrayList getDataClient()
   {
      return dataClient;
   }
   
}
