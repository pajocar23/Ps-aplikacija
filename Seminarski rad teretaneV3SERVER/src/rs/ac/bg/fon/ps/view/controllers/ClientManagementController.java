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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.Sender;
import rs.ac.bg.fon.ps.components.table.TrenerTableModel;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.forms.FrmClientManagement;

/**
 *
 * @author Mr OLOGIZ
 */
public class ClientManagementController {

    private FrmClientManagement frmClientManagement;
    
    public ClientManagementController(FrmClientManagement frmClientManagement) {
        this.frmClientManagement = frmClientManagement;
        addActionListener();
    }
    
    public void openForm() {
        frmClientManagement.setLocationRelativeTo(null);
        frmClientManagement.setTitle("Forma za upravljanje trenerima");
        frmClientManagement.setResizable(false);
        fillForm();
        frmClientManagement.setVisible(true);
    }
    
    private void addActionListener() {
        frmClientManagement.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillTable(e);
            }
            

            private void fillTable(WindowEvent e) {
                fillForm();
            }
            
        });
        
       
    }
    
    private void fillForm() {
        List<Trener> ulogovaniTreneri = MainCordinator.getInstance().getConnectedUsers();
        TrenerTableModel ttm = new TrenerTableModel(ulogovaniTreneri);
        frmClientManagement.getTblUlogovaniTreneri().setModel(ttm);
    }
    
}
