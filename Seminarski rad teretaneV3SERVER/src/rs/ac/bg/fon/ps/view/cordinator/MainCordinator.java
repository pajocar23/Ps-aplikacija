/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.cordinator;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.view.controllers.ClientManagementController;
import rs.ac.bg.fon.ps.view.controllers.ServerConnectionFormController;
import rs.ac.bg.fon.ps.view.forms.FrmClientManagement;
import rs.ac.bg.fon.ps.view.forms.FrmServerConnectionForm;

/**
 *
 * @author Mr OLOGIZ
 */
public class MainCordinator {

    private static MainCordinator instance;

    private Map<String, Object> params;

    private List<Socket> connectedSockets;
    private List<Trener> connectedUsers;
    
    private Socket clientSocket;
    private Trener terner;
    
    private ServerConnectionFormController serverConnectionController;

    private MainCordinator() {
        serverConnectionController=new ServerConnectionFormController(new FrmServerConnectionForm());
        connectedSockets =new ArrayList<>();
        connectedUsers=new ArrayList<>();
    }

    public static MainCordinator getInstance() {
        if (instance == null) {
            instance = new MainCordinator();
        }
        return instance;
    }

    public void openServerConnectionForm() {
        ServerConnectionFormController scfc = new ServerConnectionFormController(new FrmServerConnectionForm());
        scfc.openForm();
    }
    
    public void openClientManagementForm(){
        ClientManagementController cmc=new ClientManagementController(new FrmClientManagement(serverConnectionController.getFrmServerConnectionForm(), true));
        cmc.openForm();
    }
    
    public void addSocket(Socket clientSocket){
        connectedSockets.add(clientSocket);
    }
    
    public void removeSocket(Socket clientSocket){
        connectedSockets.remove(clientSocket);
    }
    
    public void addTrener(Trener trener){
        connectedUsers.add(trener);
    }
    
    public void removeTrener(Trener trener){
        for (int i=0;i<connectedUsers.size();i++) {
            if(Objects.equals(trener.getIDTrenera(), connectedUsers.get(i).getIDTrenera())){
                connectedUsers.remove(connectedUsers.get(i));
                return;
            }
        }
    }

    public List<Socket> getConnectedSockets() {
        return connectedSockets;
    }

    public void setConnectedSockets(List<Socket> connectedSockets) {
        this.connectedSockets = connectedSockets;
    }

    public List<Trener> getConnectedUsers() {
        return connectedUsers;
    }

    public void setConnectedUsers(List<Trener> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    public boolean sadrziTrenera(Trener trener) {
        for (int i = 0; i < connectedUsers.size(); i++) {
            if(Objects.equals(trener.getIDTrenera(), connectedUsers.get(i).getIDTrenera())){
                return true;
            }
        }
        return false;
    }

    public void sendLogOutSocket(Socket socket) {
        
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public Trener getTerner() {
        return terner;
    }

    public void setTerner(Trener terner) {
        this.terner = terner;
    }
    
    
    
    
}
