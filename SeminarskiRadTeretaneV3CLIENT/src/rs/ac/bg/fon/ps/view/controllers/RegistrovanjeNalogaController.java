/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.NalogClana;
import rs.ac.bg.fon.ps.exceptions.ValidationRegistrovanjeNalogaException;
import rs.ac.bg.fon.ps.view.forms.FrmMain;
import rs.ac.bg.fon.ps.view.forms.FrmRegistrovanjeNalogaClana;
import rs.ac.bg.fon.ps.view.utils.Pol;

/**
 *
 * @author Mr OLOGIZ
 */
public class RegistrovanjeNalogaController {

    private FrmRegistrovanjeNalogaClana frmRegistrovanjeNalogaClana;

    public RegistrovanjeNalogaController(FrmRegistrovanjeNalogaClana frmRegistrovanjeNalogaClana) {
        this.frmRegistrovanjeNalogaClana = frmRegistrovanjeNalogaClana;
        addActionListener();
    }

    public void openForm() {

        frmRegistrovanjeNalogaClana.setLocationRelativeTo(null);
        frmRegistrovanjeNalogaClana.setTitle("Forma za registrovanje novog naloga");
        frmRegistrovanjeNalogaClana.setResizable(false);
        frmRegistrovanjeNalogaClana.setVisible(true);

    }

    private void addActionListener() {
        frmRegistrovanjeNalogaClana.registrovanjeNalogaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                addNalogClana(event);
            }

            private void addNalogClana(ActionEvent actionEvent) {
                resetForm();
                try {

                    String ime = frmRegistrovanjeNalogaClana.getTxtIme().getText().trim().toLowerCase();
                    String prezime = frmRegistrovanjeNalogaClana.getTxtPrezime().getText().trim().toLowerCase();
                    String brojTelefona = frmRegistrovanjeNalogaClana.getTxtBrojTelefona().getText().toLowerCase();
                    String UlicaBroj = frmRegistrovanjeNalogaClana.getTxtUlicaBroj().getText().trim().toLowerCase();
                    String JBMG = frmRegistrovanjeNalogaClana.getTxtJBMG().getText().trim().toLowerCase();
                    
                    Pol pol;
                    boolean platio;
                    String polClana;
                    if (frmRegistrovanjeNalogaClana.getRbMuski().isSelected()) {
                        pol = Pol.MUSKI;
                        polClana = "M";
                        System.out.println("muski");
                    } else if (frmRegistrovanjeNalogaClana.getRbZenski().isSelected()) {
                        pol = Pol.ZENSKI;
                        polClana = "Z";
                        System.out.println("zenski");
                    } else if (frmRegistrovanjeNalogaClana.getRbOstalo().isSelected()) {
                        pol = Pol.OSTALO;
                        polClana = "O";
                    } else {
                        pol = null; //ako ni jedan radio button u polu nije selektovan
                        polClana = "N/I";
                    }

                    if (frmRegistrovanjeNalogaClana.getRbDa().isSelected()) {
                        platio = true;
                    } else if (frmRegistrovanjeNalogaClana.getRbDa().isSelected()) {
                        platio = false;
                    } else {
                        platio = false;
                    }

                    try {
                        validateForm(ime, prezime, brojTelefona, JBMG, UlicaBroj, pol);
                    } catch (ValidationRegistrovanjeNalogaException ex) {
                        JOptionPane.showMessageDialog(frmRegistrovanjeNalogaClana, ex.getMessage(), "Sistem ne moze da zapamti nalog clana", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                        return;
                    }

                    ///////////
                    Random r = new Random();
                    Integer idInt = r.nextInt(100);
                    Long id = new Long(idInt);
                    Long brTel = Long.parseLong(brojTelefona);
                    Long JMBGClana = Long.parseLong(JBMG);
                    ///////////

                    NalogClana nc = new NalogClana(id, ime, prezime, brTel, UlicaBroj, platio, polClana, JMBGClana);

                    //odgovor od sistema
                    //NalogClana zapamceniNalog = Controller.getInstance().zapamtiNalogClana(nc);
                    NalogClana zapamceniNalog = null;
                    try {
                        zapamceniNalog = Communication.getInstance().zapamtiNalogClana(nc);
                    } catch (SocketException se) {
                        se.printStackTrace();
                        frmRegistrovanjeNalogaClana.dispose();
                        JOptionPane.showMessageDialog(((FrmMain)frmRegistrovanjeNalogaClana.getParent()), "Server ugasen","Prekid",JOptionPane.ERROR_MESSAGE);
                        ((FrmMain)frmRegistrovanjeNalogaClana.getParent()).dispose();
                        System.exit(0);
                        return;
                    }
                    //JOptionPane.showMessageDialog(frmRegistrovanjeNalogaClana, "Sistem ne moze da zapamti nalog clana", "Sistem ne moze da zapamti nalog clana", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(frmRegistrovanjeNalogaClana,"Sistem je zapamtio nalog člana\n"+zapamceniNalog.toString2(), "Zapamcen nalog", JOptionPane.INFORMATION_MESSAGE);
                    //odgovor od sistema

                    resetTextFields();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmRegistrovanjeNalogaClana, ex.getMessage(), "Sistem ne moze da zapamti nalog clana", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }

            private void resetForm() {
                frmRegistrovanjeNalogaClana.getLblErrorMessage().setText("");
            }

            private void resetTextFields() {
                frmRegistrovanjeNalogaClana.getTxtIme().setText("");
                frmRegistrovanjeNalogaClana.getTxtPrezime().setText("");
                frmRegistrovanjeNalogaClana.getTxtJBMG().setText("");
                frmRegistrovanjeNalogaClana.getTxtUlicaBroj().setText("");
                frmRegistrovanjeNalogaClana.getTxtBrojTelefona().setText("");
            }

            private void validateForm(String ime, String prezime, String brojTelefona, String JBMG, String ulicaBroj, Pol pol) throws ValidationRegistrovanjeNalogaException {

                String errorMessageEmpty = "";
                String errorMessageHasLettersOrNumbers = "";
                boolean ulicaImaBroj = false;
                boolean ulicaImaSlovo = false;

                //prvo proveravamo da li je sve prazno
                if (ime.isEmpty()) {
                    frmRegistrovanjeNalogaClana.getLblErrorMessage().setText("Ime ne moze biti prazno!");
                    errorMessageEmpty += "Ime ne moze biti prazno!";
                } else if (prezime.isEmpty()) {
                    frmRegistrovanjeNalogaClana.getLblErrorMessage().setText("Prezime ne moze biti prazno!");
                    errorMessageEmpty += "Prezime ne moze biti prazno!";
                } else if (brojTelefona.isEmpty()) {
                    frmRegistrovanjeNalogaClana.getLblErrorMessage().setText("Broj telefona ne moze biti prazan!");
                    errorMessageEmpty += "Broj telefona ne moze biti prazan!";
                } else if (JBMG.isEmpty()) {
                    frmRegistrovanjeNalogaClana.getLblErrorMessage().setText("JBMG ne moze biti prazan!");
                    errorMessageEmpty += "JBMG ne moze biti prazan!";
                } else if (ulicaBroj.isEmpty()) {
                    frmRegistrovanjeNalogaClana.getLblErrorMessage().setText("Ulica i broj ne mogu biti prazni!");
                    errorMessageEmpty += "Ulica i broj ne mogu biti prazni!";
                } else if (pol == null) {
                    frmRegistrovanjeNalogaClana.getLblErrorMessage().setText("Odaberite pol!");
                    errorMessageEmpty += "Odaberite pol";
                } else if (!frmRegistrovanjeNalogaClana.getRbDa().isSelected() && !frmRegistrovanjeNalogaClana.getRbNe().isSelected()) {
                    frmRegistrovanjeNalogaClana.getLblErrorMessage().setText("Morate naznaciti da li je clanarina placena!");
                    errorMessageEmpty += "Morate naznaciti da li je clanarina placena!";
                }

                if (!errorMessageEmpty.isEmpty()) {
                    throw new ValidationRegistrovanjeNalogaException(errorMessageEmpty);
                }
                //drugo proveravamo ispravnost brojevnih podataka

                for (int i = 0; i < ime.length(); i++) {
                    if (!Character.isAlphabetic(ime.charAt(i))) {
                        frmRegistrovanjeNalogaClana.getLblErrorMessage().setText("Ime sme sadrzati samo slova!");
                        errorMessageHasLettersOrNumbers += "Ime sme sadrzati samo slova!";
                        throw new ValidationRegistrovanjeNalogaException(errorMessageHasLettersOrNumbers);
                    }

                }

                for (int i = 0; i < prezime.length(); i++) {
                    if (!Character.isAlphabetic(prezime.charAt(i))) {
                        frmRegistrovanjeNalogaClana.getLblErrorMessage().setText("Prezime sme sadrzati samo slova!");
                        errorMessageHasLettersOrNumbers += "Prezime sme sadrzati samo slova!";
                        throw new ValidationRegistrovanjeNalogaException(errorMessageHasLettersOrNumbers);
                    }
                }

                for (int i = 0; i < brojTelefona.length(); i++) {
                    if (!Character.isDigit(brojTelefona.charAt(i))) {
                        frmRegistrovanjeNalogaClana.getLblErrorMessage().setText("Broj telefona sme sadrzati samo brojeve!");
                        errorMessageHasLettersOrNumbers += "Broj telefona sme sadrzati samo brojeve!";
                        throw new ValidationRegistrovanjeNalogaException(errorMessageHasLettersOrNumbers);
                    }
                }

                for (int i = 0; i < JBMG.length(); i++) {
                    if (!Character.isDigit(JBMG.charAt(i))) {
                        frmRegistrovanjeNalogaClana.getLblErrorMessage().setText("JBMG sme sadrzati samo brojeve!");
                        errorMessageHasLettersOrNumbers += "JBMG sme sadrzati samo brojeve!";
                        throw new ValidationRegistrovanjeNalogaException(errorMessageHasLettersOrNumbers);
                    }
                }

                for (int i = 0; i < ulicaBroj.length(); i++) {
                    if (Character.isAlphabetic(ulicaBroj.charAt(i))) {
                        ulicaImaSlovo = true;
                    }
                    if (Character.isDigit(ulicaBroj.charAt(i))) {
                        ulicaImaBroj = true;
                    }
                }

                if (!ulicaImaBroj || !ulicaImaSlovo) {
                    errorMessageHasLettersOrNumbers += "UlicaBroj/StanSprat se mora sastojati od broja i slova!";
                    throw new ValidationRegistrovanjeNalogaException(errorMessageHasLettersOrNumbers);
                }

                if (JBMG.length() != 13) {
                    errorMessageHasLettersOrNumbers += "JBMG mora sadrzati 13 cifara!";
                    throw new ValidationRegistrovanjeNalogaException(errorMessageHasLettersOrNumbers);
                }
                
                Pattern pat=Pattern.compile("/^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])[0-9]{9}$/");
                Matcher mat=pat.matcher(JBMG);
                boolean res=mat.matches();
                
                if(!JBMG.matches("/(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])[0-9]{9}/")){                
                    errorMessageHasLettersOrNumbers += "JBMG"+JBMG+"mora biti u validnom fomratu!";
                    throw new ValidationRegistrovanjeNalogaException(errorMessageHasLettersOrNumbers);
                }
                
            }

            private void fillDefaultRadioButtons() {
                frmRegistrovanjeNalogaClana.getRbNe().setSelected(true);
                frmRegistrovanjeNalogaClana.getRbMuski().setSelected(true);
            }

        });
    }

}
