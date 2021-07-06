/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.threads;

import rs.ac.bg.fon.ps.communication.Communication;

/**
 *
 * @author Mr OLOGIZ
 */
public class ServerMessagesProcessing extends Thread {

    
    @Override
    public void run() {
        while (true) {
            try {
                boolean izbaci=Communication.getInstance().izbaciKlijenta();
                if(izbaci==true){
                    System.out.println("izbacen");
                }else{
                    
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
