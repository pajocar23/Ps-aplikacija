/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.exceptions.ValidationException;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.forms.FrmLogIn;
import rs.ac.bg.fon.ps.view.forms.FrmMain;

/**
 *
 * @author Mr OLOGIZ
 */
public class LogInController {

    private FrmLogIn frmLogin;

    public LogInController(FrmLogIn frmLogin) {
        this.frmLogin = frmLogin;
        addActionListener();
    }

    public void openForm() {
        frmLogin.setLocationRelativeTo(null);
        frmLogin.setTitle("Log in forma");
        frmLogin.setResizable(false);
        frmLogin.setVisible(true);

    }

    private void addActionListener() {
        frmLogin.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loginUser(actionEvent);
            }

            private void loginUser(ActionEvent actionEvent) {
                resetForm();
                try {
                    String username = frmLogin.getTxtUsername().getText().trim();
                    String password = String.copyValueOf(frmLogin.getTxtPassword().getPassword());

                    validateForm(username, password);

                    //Trener trener = Controller.getInstance().logIn(username, password);  //sistemska operacija
                    Trener trener;
                    try {
                        trener = Communication.getInstance().logIn(username, password);
                    } catch (SocketException se) {
                        frmLogin.dispose();
                        JOptionPane.showMessageDialog(((FrmMain) frmLogin.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                        ((FrmMain) frmLogin.getParent()).dispose();
                        System.exit(0);
                        return;
                    }

                    JOptionPane.showMessageDialog(frmLogin, "Ulogovan trener: " + trener, "Logovanje", JOptionPane.INFORMATION_MESSAGE);
                    MainCordinator.getInstance().addParamTrener(Constants.LOGGED_TRENER, trener);
                    MainCordinator.getInstance().openMainForm();
                    frmLogin.dispose();
                } catch (ValidationException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmLogin, ex.getMessage(), "Greska u logovanju", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void validateForm(String username, String password) throws ValidationException {
                String errorMessage = "";
                if (username.isEmpty()) {
                    frmLogin.getLblUsernameError().setText("Korisnicko ime ne sme biti prazno");
                    errorMessage += "Korisnicko ime ne sme biti prazno\n";
                }
                if (password.isEmpty()) {
                    frmLogin.getLblPasswordError().setText("Sifra ne sme bitri prazna");
                    errorMessage += "Sifra ne sme biti prazna\n";
                }

                if (!errorMessage.isEmpty()) {
                    throw new ValidationException(errorMessage);
                }
            }

            private void resetForm() {
                frmLogin.getLblPasswordError().setText("");
                frmLogin.getLblUsernameError().setText("");
            }

        });

    }

}
