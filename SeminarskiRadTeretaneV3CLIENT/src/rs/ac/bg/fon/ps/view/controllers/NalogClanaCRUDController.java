/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.NalogClana;
import rs.ac.bg.fon.ps.exceptions.ValidationRegistrovanjeNalogaException;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.forms.FrmMain;
import rs.ac.bg.fon.ps.view.forms.FrmNalogClanaCRUD;
import rs.ac.bg.fon.ps.view.utils.Pol;

/**
 *
 * @author Mr OLOGIZ
 */
public class NalogClanaCRUDController {

    private FrmNalogClanaCRUD frmNalogClanaCRUD;

    public NalogClanaCRUDController(FrmNalogClanaCRUD frmNalogClanaCRUD) {
        this.frmNalogClanaCRUD = frmNalogClanaCRUD;
        addActionListener();
    }

    public void openForm(NalogClana nc) {
        frmNalogClanaCRUD.setLocationRelativeTo(null);
        frmNalogClanaCRUD.setTitle("Nalog clana");
        frmNalogClanaCRUD.setResizable(false);

        fillForm(nc);

        frmNalogClanaCRUD.setVisible(true);

    }

    private void addActionListener() {
        frmNalogClanaCRUD.OmoguciIzmeneNalogaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableForm(e);
            }

            private void enableForm(ActionEvent e) {
                frmNalogClanaCRUD.getBtnObrisiNalog().setEnabled(true);
                frmNalogClanaCRUD.getBtnPotvrdiIzmene().setEnabled(true);
                frmNalogClanaCRUD.getBtnOmoguciIzmene().setEnabled(false); //na potvrdi izmene, samo obrnuto setuj dugmice

                frmNalogClanaCRUD.getTxtIme().setEnabled(true);
                frmNalogClanaCRUD.getTxtPrezime().setEnabled(true);
                frmNalogClanaCRUD.getTxtBrojTelefona1().setEnabled(true);
                frmNalogClanaCRUD.getTxtUlicaBroj().setEnabled(true);
                frmNalogClanaCRUD.getTxtJBMG().setEnabled(true);

                frmNalogClanaCRUD.getRbDa().setEnabled(true);
                frmNalogClanaCRUD.getRbNe().setEnabled(true);
                frmNalogClanaCRUD.getRbMuski().setEnabled(true);
                frmNalogClanaCRUD.getRbZenski().setEnabled(true);
                frmNalogClanaCRUD.getRbOstalo().setEnabled(true);

            }
        });

        frmNalogClanaCRUD.IzmeniNalogAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniNalog(e);
            }

            private void izmeniNalog(ActionEvent e) {
                NalogClana nc = new NalogClana();

                Long id = Long.parseLong(frmNalogClanaCRUD.getTxtId().getText());
                nc.setIdClana(id);

                String ime = frmNalogClanaCRUD.getTxtIme().getText();
                nc.setImeClana(ime);

                String prezime = frmNalogClanaCRUD.getTxtPrezime().getText();
                nc.setPrezimeClana(prezime);

                Long brTelefona = Long.parseLong(frmNalogClanaCRUD.getTxtBrojTelefona1().getText());
                nc.setBrojTelefonaClana(brTelefona);

                String ulicaBroj = frmNalogClanaCRUD.getTxtUlicaBroj().getText();
                nc.setUlicaBroj(ulicaBroj);

                Long JBMG = Long.parseLong(frmNalogClanaCRUD.getTxtJBMG().getText());
                nc.setJBMGClana(JBMG);

                if (frmNalogClanaCRUD.getRbDa().isSelected()) {
                    nc.setPlatio(true);
                } else {
                    nc.setPlatio(false);
                }

                Pol p = null;
                if (frmNalogClanaCRUD.getRbMuski().isSelected()) {
                    nc.setPolClana("M");
                    p = Pol.MUSKI;
                } else if (frmNalogClanaCRUD.getRbZenski().isSelected()) {
                    nc.setPolClana("Z");
                    p = Pol.ZENSKI;
                } else if (frmNalogClanaCRUD.getRbOstalo().isSelected()) {
                    nc.setPolClana("O");
                    p = Pol.OSTALO;
                }

                try {
                    validateForm(ime, prezime, String.valueOf(brTelefona), String.valueOf(JBMG), ulicaBroj, p);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmNalogClanaCRUD, ex.getMessage(), "Neuspešna izmena", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    try {
                        Communication.getInstance().izmeniNalogClana(nc);
                    } catch (SocketException se) {
                        se.printStackTrace();
                        frmNalogClanaCRUD.dispose();
                        JOptionPane.showMessageDialog(((FrmMain) frmNalogClanaCRUD.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                        ((FrmMain) frmNalogClanaCRUD.getParent()).dispose();
                        System.exit(0);
                        return;
                    }
                    JOptionPane.showMessageDialog(frmNalogClanaCRUD, "Sistem je izmenio nalog člana", "Uspešna izmena", JOptionPane.INFORMATION_MESSAGE);
                    frmNalogClanaCRUD.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmNalogClanaCRUD, "Sistem ne može da izmeni nalog člana", "Neuspešna izmena", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        frmNalogClanaCRUD.ObrisiNalogAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiNalog(e);
            }

            private void obrisiNalog(ActionEvent e) {
                NalogClana nalogZaBrisanje = MainCordinator.getInstance().getParamNalog(Constants.SELECTED_NALOG);
                try {
                    try {
                        Communication.getInstance().obrisiNalogClana(nalogZaBrisanje);
                    } catch (SocketException se) {
                        frmNalogClanaCRUD.dispose();
                        JOptionPane.showMessageDialog(((FrmMain) frmNalogClanaCRUD.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                        ((FrmMain) frmNalogClanaCRUD.getParent()).dispose();
                        System.exit(0);
                        return;
                    }
                    JOptionPane.showMessageDialog(frmNalogClanaCRUD, "Sistem je obrisao nalog člana", "Uspešno brisanje", JOptionPane.INFORMATION_MESSAGE);
                    frmNalogClanaCRUD.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmNalogClanaCRUD, "Sistem ne može da obriše nalog člana", "Neuspešno brisanje", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void fillForm(NalogClana nc) {
        frmNalogClanaCRUD.getTxtId().setText(String.valueOf(nc.getIdClana()));
        frmNalogClanaCRUD.getTxtIme().setText(String.valueOf(nc.getImeClana()));
        frmNalogClanaCRUD.getTxtPrezime().setText(String.valueOf(nc.getPrezimeClana()));
        frmNalogClanaCRUD.getTxtBrojTelefona1().setText(String.valueOf(nc.getBrojTelefonaClana()));
        frmNalogClanaCRUD.getTxtUlicaBroj().setText(String.valueOf(nc.getUlicaBroj()));
        frmNalogClanaCRUD.getTxtJBMG().setText(String.valueOf(nc.getJBMGClana()));

        if (nc.isPlatio()) {
            frmNalogClanaCRUD.getRbDa().setSelected(true);
            frmNalogClanaCRUD.getRbNe().setSelected(false);
        } else {
            frmNalogClanaCRUD.getRbDa().setSelected(false);
            frmNalogClanaCRUD.getRbNe().setSelected(true);
        }

        if (nc.getPolClana().equals("M")) {
            frmNalogClanaCRUD.getRbMuski().setSelected(true);
            frmNalogClanaCRUD.getRbZenski().setSelected(false);
            frmNalogClanaCRUD.getRbOstalo().setSelected(false);
        } else if (nc.getPolClana().equals("Z")) {
            frmNalogClanaCRUD.getRbMuski().setSelected(false);
            frmNalogClanaCRUD.getRbZenski().setSelected(true);
            frmNalogClanaCRUD.getRbOstalo().setSelected(false);
        } else {
            frmNalogClanaCRUD.getRbMuski().setSelected(false);
            frmNalogClanaCRUD.getRbZenski().setSelected(false);
            frmNalogClanaCRUD.getRbOstalo().setSelected(true);
        }

        frmNalogClanaCRUD.getTxtId().setEnabled(false);
        frmNalogClanaCRUD.getTxtIme().setEnabled(false);
        frmNalogClanaCRUD.getTxtPrezime().setEnabled(false);
        frmNalogClanaCRUD.getTxtBrojTelefona1().setEnabled(false);
        frmNalogClanaCRUD.getTxtUlicaBroj().setEnabled(false);
        frmNalogClanaCRUD.getTxtJBMG().setEnabled(false);

        frmNalogClanaCRUD.getRbDa().setEnabled(false);
        frmNalogClanaCRUD.getRbNe().setEnabled(false);
        frmNalogClanaCRUD.getRbMuski().setEnabled(false);
        frmNalogClanaCRUD.getRbZenski().setEnabled(false);
        frmNalogClanaCRUD.getRbOstalo().setEnabled(false);

        frmNalogClanaCRUD.getBtnObrisiNalog().setEnabled(false);
        frmNalogClanaCRUD.getBtnPotvrdiIzmene().setEnabled(false);

    }

    private void validateForm(String ime, String prezime, String brojTelefona, String JBMG, String ulicaBroj, Pol pol) throws ValidationRegistrovanjeNalogaException, Exception {

        String errorMessageEmpty = "";
        String errorMessageHasLettersOrNumbers = "";
        boolean ulicaImaBroj = false;
        boolean ulicaImaSlovo = false;

        //prvo proveravamo da li je sve prazno
        if (ime.isEmpty()) {
            frmNalogClanaCRUD.getLblErrorMessage().setText("Ime ne moze biti prazno!");
            errorMessageEmpty += "Ime ne moze biti prazno!";
        } else if (prezime.isEmpty()) {
            frmNalogClanaCRUD.getLblErrorMessage().setText("Prezime ne moze biti prazno!");
            errorMessageEmpty += "Prezime ne moze biti prazno!";
        } else if (brojTelefona.isEmpty()) {
            frmNalogClanaCRUD.getLblErrorMessage().setText("Broj telefona ne moze biti prazan!");
            errorMessageEmpty += "Broj telefona ne moze biti prazan!";
        } else if (JBMG.isEmpty()) {
            frmNalogClanaCRUD.getLblErrorMessage().setText("JBMG ne moze biti prazan!");
            errorMessageEmpty += "JBMG ne moze biti prazan!";
        } else if (ulicaBroj.isEmpty()) {
            frmNalogClanaCRUD.getLblErrorMessage().setText("Ulica i broj ne mogu biti prazni!");
            errorMessageEmpty += "Ulica i broj ne mogu biti prazni!";
        } else if (pol == null) {
            frmNalogClanaCRUD.getLblErrorMessage().setText("Odaberite pol!");
            errorMessageEmpty += "Odaberite pol";
        } else if (!frmNalogClanaCRUD.getRbDa().isSelected() && !frmNalogClanaCRUD.getRbNe().isSelected()) {
            frmNalogClanaCRUD.getLblErrorMessage().setText("Morate naznaciti da li je clanarina placena!");
            errorMessageEmpty += "Morate naznaciti da li je clanarina placena!";
        }

        if (!errorMessageEmpty.isEmpty()) {
            throw new Exception(errorMessageEmpty);
        }
        //drugo proveravamo ispravnost brojevnih podataka

        for (int i = 0; i < ime.length(); i++) {
            if (!Character.isAlphabetic(ime.charAt(i))) {
                frmNalogClanaCRUD.getLblErrorMessage().setText("Ime sme sadrzati samo slova!");
                errorMessageHasLettersOrNumbers += "Ime sme sadrzati samo slova!";
                throw new ValidationRegistrovanjeNalogaException(errorMessageHasLettersOrNumbers);
            }

        }

        for (int i = 0; i < prezime.length(); i++) {
            if (!Character.isAlphabetic(prezime.charAt(i))) {
                frmNalogClanaCRUD.getLblErrorMessage().setText("Prezime sme sadrzati samo slova!");
                errorMessageHasLettersOrNumbers += "Prezime sme sadrzati samo slova!";
                throw new ValidationRegistrovanjeNalogaException(errorMessageHasLettersOrNumbers);
            }
        }

        for (int i = 0; i < brojTelefona.length(); i++) {
            if (!Character.isDigit(brojTelefona.charAt(i))) {
                frmNalogClanaCRUD.getLblErrorMessage().setText("Broj telefona sme sadrzati samo brojeve!");
                errorMessageHasLettersOrNumbers += "Broj telefona sme sadrzati samo brojeve!";
                throw new ValidationRegistrovanjeNalogaException(errorMessageHasLettersOrNumbers);
            }
        }

        for (int i = 0; i < JBMG.length(); i++) {
            if (!Character.isDigit(JBMG.charAt(i))) {
                frmNalogClanaCRUD.getLblErrorMessage().setText("JBMG sme sadrzati samo brojeve!");
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

    }

}
