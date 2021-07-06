/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.config.DataBaseConfig;
import rs.ac.bg.fon.ps.config.ServerConfig;
import rs.ac.bg.fon.ps.server.Server;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.forms.FrmServerConnectionForm;

/**
 *
 * @author Mr OLOGIZ
 */
public class ServerConnectionFormController {

    private FrmServerConnectionForm frmServerConnectionForm;
    private Server server;

    public ServerConnectionFormController(FrmServerConnectionForm frmServerConnectionForm) {
        this.frmServerConnectionForm = frmServerConnectionForm;
        addActionListener();
    }

    public void openForm() {
        frmServerConnectionForm.setLocationRelativeTo(null);
        frmServerConnectionForm.setTitle("Serverska forma");
        frmServerConnectionForm.setResizable(false);
        prepareForm();
        frmServerConnectionForm.setVisible(true);

    }
    
    public FrmServerConnectionForm getFrmServerConnectionForm(){
        return frmServerConnectionForm;
    }

    private void addActionListener() {
        frmServerConnectionForm.pokreniServerBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pokreniServer(e);
            }

            private void pokreniServer(ActionEvent e) {
                frmServerConnectionForm.getBtnPokreniServer().setEnabled(false);
                frmServerConnectionForm.getBtnZaustaviServer().setEnabled(true);
                frmServerConnectionForm.getTxtServerStatus().setText("Server program je pokrenut!");
                frmServerConnectionForm.getTxtServerStatus().setDisabledTextColor(Color.green);
                if (server == null || !server.isAlive()) {
                    server=new Server();
                    server.start();
                }
            }
        });

        frmServerConnectionForm.zaustaviServerBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zaustaviServer(e);
            }

            private void zaustaviServer(ActionEvent e) {
                frmServerConnectionForm.getBtnPokreniServer().setEnabled(true);
                frmServerConnectionForm.getBtnZaustaviServer().setEnabled(false);
                frmServerConnectionForm.getTxtServerStatus().setText("Server program nije pokrenut!");
                frmServerConnectionForm.getTxtServerStatus().setDisabledTextColor(Color.red);
                if (server != null && server.isAlive()) {
                    server.shutServerDown();
                }

            }
        });
        
        frmServerConnectionForm.miKorisniciManagementAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               otvoriFormuZaUpravljanje(e);
            }

            private void otvoriFormuZaUpravljanje(ActionEvent e) {
                MainCordinator.getInstance().openClientManagementForm();
            }
        });
    }

    private void prepareForm() {
        try {
            frmServerConnectionForm.getBtnZaustaviServer().setEnabled(false);
            String port = ServerConfig.getInstance().getServerPort();
            frmServerConnectionForm.getTxtPortServer().setText(port);
            frmServerConnectionForm.getTxtPortServer().setEnabled(false);
            frmServerConnectionForm.getTxtServerStatus().setText("Server program nije pokrenut!");
            frmServerConnectionForm.getTxtServerStatus().setEnabled(false);
            frmServerConnectionForm.getTxtServerStatus().setDisabledTextColor(Color.red);

            String urlDB = DataBaseConfig.getInstance().getURL();
            String userDB = DataBaseConfig.getInstance().getDataBaseUser();
            String passwordDB = DataBaseConfig.getInstance().getDataBasePassword();

            frmServerConnectionForm.getTxtUrlDatabase().setText(urlDB);
            frmServerConnectionForm.getTxtUserDatabase().setText(userDB);
            frmServerConnectionForm.getTxtPasswordDB().setText(passwordDB);

            frmServerConnectionForm.getTxtUrlDatabase().setEnabled(false);
            frmServerConnectionForm.getTxtUserDatabase().setEnabled(false);
            frmServerConnectionForm.getTxtPasswordDB().setEnabled(false);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //C:\Users\Mr OLOGIZ\Documents\NetBeansProjects\Seminarski rad teretaneV3SERVER\src\rs\ac\bg\fon\ps\config
    }

}
