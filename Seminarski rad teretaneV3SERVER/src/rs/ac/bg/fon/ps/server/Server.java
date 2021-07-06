/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.config.ServerConfig;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.threads.ClientRequestProcessing;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;

/**
 *
 * @author Mr OLOGIZ
 */
public class Server extends Thread {

    private boolean serverWorking=true;
    ServerSocket serverSocket;
    ClientRequestProcessing crp;
    Socket socket;

    public Server() {

    }

    @Override
    public void run() {
        startServer();
    }

    public void startServer() {
        try {
            /////////////
            Properties properties=new Properties();
            String serverConfigFilePath = new File("src\\rs\\ac\\bg\\fon\\ps\\config\\ServerConfig.properties").getAbsolutePath();
            properties.load(new FileInputStream(serverConfigFilePath));           
            String port=properties.getProperty("port");
            /////////////
            serverSocket=new ServerSocket(Integer.valueOf(port));
            System.out.println("Server se podigao");
            while (serverWorking) {
                System.out.println("Ceka se korisnik...");
                socket = serverSocket.accept();
                MainCordinator.getInstance().addSocket(socket);
                System.out.println("Koirsnik povezan");
                handleClient(socket);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleClient(Socket socket) throws Exception {
        crp = new ClientRequestProcessing(socket);
        crp.start();
    }

    public boolean isServerWorking() {
        return serverWorking;
    }

    public void setServerWorking(boolean serverWorking) {
        this.serverWorking = serverWorking;
    }

    public void shutServerDown() {
        List<Socket> sviklijenti=MainCordinator.getInstance().getConnectedSockets();
        for (Socket socket1 : sviklijenti) {
            try {
                socket1.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        MainCordinator.getInstance().setConnectedUsers(new ArrayList<>());
        
        serverWorking = false;
        try {           
            serverSocket.close();
            System.out.println("ugasen");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
