/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.threads.ServerMessagesProcessing;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.forms.FrmMain;

/**
 *
 * @author Mr OLOGIZ
 */
public class MainController {
    
    private FrmMain frmMain;
    Trener trener;
    ServerMessagesProcessing smp;
    
    public MainController(FrmMain frmMain) {
        try {
            //smp=new ServerMessagesProcessing();
            //smp.start();
            this.frmMain = frmMain;
            addActionListener();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void openForm() {
        trener = MainCordinator.getInstance().getParamTrener(Constants.LOGGED_TRENER);
        frmMain.getLblUlogovanTrener().setText("Ulogovan trener: " + trener);        
        frmMain.setTitle("Glavna forma");
        frmMain.setLocationRelativeTo(null);
        frmMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmMain.setVisible(true);
    }
    
    private void addActionListener() {
        frmMain.miNalogClanaNewAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miOtvoriFormuZaDodavanjeNaloga(evt);
            }            

            private void miOtvoriFormuZaDodavanjeNaloga(java.awt.event.ActionEvent evt) {                
                MainCordinator.getInstance().openFrmRegistrovanjeNaloga();
            }            
            
        });
        
        frmMain.miNalogClanaSearchAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miOtvoriFormuZaPretraguNaloga(evt);
            }

            private void miOtvoriFormuZaPretraguNaloga(java.awt.event.ActionEvent evt) {                
                MainCordinator.getInstance().openPretragaNalogaForm();
            }            
            
        });
        
        frmMain.miTerminNewAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miOtvoriFormuZaDodavanjeTermina(e);
            }
            
            private void miOtvoriFormuZaDodavanjeTermina(ActionEvent e) {
                MainCordinator.getInstance().openFrmRegistrovanjeTermina();
            }
        });
        
        frmMain.miPretraziTerminAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miOtvoriFormuZaPretragutermina(e);
            }
            
            private void miOtvoriFormuZaPretragutermina(ActionEvent e) {
                MainCordinator.getInstance().openFrmPretragaTermina();
            }
        });
        
        frmMain.miDodajNalogUTermin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miOtvoriFormuZaDodavanjeNalogaUTermin(e);
            }
            
            private void miOtvoriFormuZaDodavanjeNalogaUTermin(ActionEvent e) {
                MainCordinator.getInstance().openFrmDodavanjeNalogaUTermin();
            }
        });
        
        frmMain.izlogujSeBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izlogujSe(e);
            }
            
            private void izlogujSe(ActionEvent e) {
                try {
                    Communication.getInstance().logOut(trener);
                    frmMain.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        frmMain.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               izlogujSe(e);
            }

            private void izlogujSe(WindowEvent e) {
               try {
                    Communication.getInstance().logOut(trener);
                    frmMain.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        
        });
        
    }
    
    public FrmMain getFrmMain() {
        return frmMain;
    }
    
}
