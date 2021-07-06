/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.communication;

import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Mr OLOGIZ
 */
public class Receiver {
     private Socket socket;
     

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    public Receiver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Object receive()throws Exception{
        try{
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            return ois.readObject();
        }catch (SocketException se) {
            throw se;
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Greška u primanju objekata!\n"+e.getMessage());
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
   
}
