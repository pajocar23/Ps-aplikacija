/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controllers;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.view.forms.FrmRegistrovanjeNalogaClana;
import rs.ac.bg.fon.ps.view.forms.FrmLogIn;
import rs.ac.bg.fon.ps.view.forms.FrmMain;

/**
 *
 * @author Mr OLOGIZ
 */
public class ViewController {
    private FrmLogIn loginForm;
    private FrmMain mainForm;
    private FrmRegistrovanjeNalogaClana kreiranjeNalogaClanaForm;
    
    private static ViewController instance;
    
    private Trener loggedTrener;
    private Map<String, Object> paramMap = new HashMap<>();

    private ViewController() {
        
    }
    
    public static ViewController getInstance(){
        if(instance==null){
            instance=new ViewController();
        }
        return instance;
    }
    
    public Trener getLoggedTrener(){
        return loggedTrener;
    }
    
    public void setLogedTrener(Trener loggedTrener) {
        this.loggedTrener = loggedTrener;
    }
    
    public void startApplication(){       
        loginForm=new FrmLogIn();
        loginForm.setVisible(true);
        loginForm.setLocationRelativeTo(null);
        loginForm.setResizable(false);
        loginForm.setTitle("Login forma");      
    }

    public void openMainForm() {
       mainForm=new FrmMain();
       mainForm.setVisible(true);
       mainForm.setLocationRelativeTo(null);
       mainForm.setExtendedState(JFrame.MAXIMIZED_BOTH);
       loginForm.dispose();
       mainForm.setTitle("Main forma");
       
    }
    
    public void openFormKreiranjeNalogaClana(){
        
    }
    
    
}
