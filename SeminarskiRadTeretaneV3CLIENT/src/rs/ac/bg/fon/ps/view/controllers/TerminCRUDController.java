/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.components.table.GrupaTableModel;
import rs.ac.bg.fon.ps.domain.Grupa;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.forms.FrmMain;
import rs.ac.bg.fon.ps.view.forms.FrmTerminCRUD;

/**
 *
 * @author Mr OLOGIZ
 */
public class TerminCRUDController {

    private FrmTerminCRUD frmTerminCRUD;

    public TerminCRUDController(FrmTerminCRUD frmTerminCRUD) {
        this.frmTerminCRUD = frmTerminCRUD;
        addActionListener();
    }

    public void openForm(Termin t) {
        frmTerminCRUD.setLocationRelativeTo(null);
        frmTerminCRUD.setTitle("Izmena termina");
        frmTerminCRUD.setResizable(false);
        fillForm(t);
        frmTerminCRUD.setVisible(true);
    }

    private void addActionListener() {
        frmTerminCRUD.OmoguciIzmeneTerminaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                omoguciIzmene(e);
            }

            private void omoguciIzmene(ActionEvent e) {
                frmTerminCRUD.getBtnObrisiTermin().setEnabled(true);
                frmTerminCRUD.getBtnPotvrdiIzmene().setEnabled(true);
                frmTerminCRUD.getPanelDatum().setVisible(true);
                frmTerminCRUD.getTxtDatum().setVisible(false);

                frmTerminCRUD.getTxtId().setEnabled(false);
                frmTerminCRUD.getTxtVremePocetka().setEnabled(true);
                frmTerminCRUD.getTxtVremeKraja().setEnabled(true);
                frmTerminCRUD.getTxtTrajanje().setEnabled(true);

                frmTerminCRUD.getTxtMaxBrClanova().setEnabled(true);

            }
        });

        frmTerminCRUD.ObrisiTerminAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiTermin(e);
            }

            private void obrisiTermin(ActionEvent e) {
                Termin terminZaBrisanje = MainCordinator.getInstance().getParamTermin(Constants.SELECTED_TERMIN);
                try {
                    //Controller.getInstance().obrisiTermin(terminZaBrisanje);
                    try {
                        Communication.getInstance().obrisiTermin(terminZaBrisanje);
                    } catch (SocketException se) {
                        se.printStackTrace();
                        frmTerminCRUD.dispose();
                        JOptionPane.showMessageDialog(((FrmMain) frmTerminCRUD.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                        ((FrmMain) frmTerminCRUD.getParent()).dispose();
                        System.exit(0);
                        return;
                    }
                    JOptionPane.showMessageDialog(frmTerminCRUD, "Sistem je obrisao termin", "Uspešno brisanje", JOptionPane.INFORMATION_MESSAGE);
                    //JOptionPane.showMessageDialog(frmTerminCRUD, "Sistem nije obrisao termin", "Neuspešno brisanje", JOptionPane.ERROR_MESSAGE);
                    frmTerminCRUD.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmTerminCRUD, "Sistem ne može da obriše termin", "Neuspešno brisanje", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmTerminCRUD.IzmeniTerminAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniTermin(e);
            }

            private void izmeniTermin(ActionEvent e) {
                try {
                    Long id = Long.parseLong(frmTerminCRUD.getTxtId().getText());

                    String dateStr = frmTerminCRUD.getDateChooser().getText();
                    String[] splited = dateStr.split("/");
                    String noviDatum = "20" + splited[2] + "-" + splited[0] + "-" + splited[1];
                    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

                    Date datumTermina = formatter1.parse(noviDatum);

                    Date danasnjiDatum = new Date();
                    if (!datumTermina.after(danasnjiDatum)) {
                        JOptionPane.showMessageDialog(frmTerminCRUD, "Unesite neki datum u buducnosti", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    SimpleDateFormat formatter2 = new SimpleDateFormat("hh:mm:ss");
                    Date vremePocetka;
                    Date vremeKraja;
                    Date trajanje;
                    try {
                        vremePocetka = formatter2.parse(frmTerminCRUD.getTxtVremePocetka().getText());
                        vremeKraja = formatter2.parse(frmTerminCRUD.getTxtVremeKraja().getText());
                        trajanje = formatter2.parse(frmTerminCRUD.getTxtTrajanje().getText());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frmTerminCRUD, "Nevalidan format datum\nPratite fomrat 00:00:00", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (vremePocetka.after(vremeKraja) || vremePocetka.equals(vremeKraja)) {
                        JOptionPane.showMessageDialog(frmTerminCRUD, "Vreme pocetka mora biti pre vremena kraja", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int maksBrojClanova = Integer.valueOf(frmTerminCRUD.getTxtMaxBrClanova().getText());
                    int brojClanova = Integer.valueOf(frmTerminCRUD.getTxtBrojClanova().getText());

                    int selektovanaGrupa = frmTerminCRUD.getTblGrupe().getSelectedRow();
                    Grupa g;
                    if (selektovanaGrupa == -1) {
                        frmTerminCRUD.getLblErrorMessage().setText("Selektujte grupu iz tabele grupa");
                        return;
                    } else {
                        GrupaTableModel gtm = (GrupaTableModel) frmTerminCRUD.getTblGrupe().getModel();
                        List<Grupa> grupe = gtm.getGrupe();
                        g = grupe.get(selektovanaGrupa);
                    }
                    Termin odabraniTermin = MainCordinator.getInstance().getParamTermin(Constants.SELECTED_TERMIN);

                    //validacija
                    if (frmTerminCRUD.getTxtVremePocetka().getText().isEmpty()) {
                        frmTerminCRUD.getLblErrorMessage().setText("Unesite vreme početka");
                        return;
                    } else if (frmTerminCRUD.getTxtVremeKraja().getText().isEmpty()) {
                        frmTerminCRUD.getLblErrorMessage().setText("Unesite vreme kraja");
                        return;
                    } else if (frmTerminCRUD.getTxtTrajanje().getText().isEmpty()) {
                        frmTerminCRUD.getLblErrorMessage().setText("Unesite trajanje");
                        return;
                    } else if (frmTerminCRUD.getTxtMaxBrClanova().getText().isEmpty()) {
                        frmTerminCRUD.getLblErrorMessage().setText("Unesite maksimalni broj članova");
                        return;
                    }
                    //validaicja

                    Termin updateTermin = new Termin(odabraniTermin.getIDTermina(), datumTermina, vremePocetka, vremeKraja, trajanje, brojClanova, maksBrojClanova, g);

                    List<Termin> sviTermini = new ArrayList<>();
                    try {
                        sviTermini = Communication.getInstance().getSviTermini();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    for (Termin termin : sviTermini) {
                        if (termin.getDatum().equals(updateTermin.getDatum())) {
                            System.out.println("id otvorenog:"+updateTermin.getIDTermina());
                            System.out.println("id nekog u bazi:"+termin.getIDTermina());
                            if(Objects.equals(termin.getIDTermina(), updateTermin.getIDTermina())){
                                System.out.println("jednaki");
                                continue;
                            }
                            System.out.println("Naso isti datum " + termin.getDatum());
                            if (updateTermin.getVremePocetka().after(termin.getVremeKraja()) || updateTermin.getVremeKraja().before(termin.getVremePocetka())) {
                                System.out.println("nema pokl");
                                continue;
                            } else {
                                JOptionPane.showMessageDialog(frmTerminCRUD, "Poklapanje termina", "Greska", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }

                    try {
                        //Controller.getInstance().updateTermin(updateTermin);
                        try {
                            Communication.getInstance().izmeniTermin(updateTermin);
                        } catch (SocketException se) {
                            se.printStackTrace();
                            frmTerminCRUD.dispose();
                            JOptionPane.showMessageDialog(((FrmMain) frmTerminCRUD.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                            ((FrmMain) frmTerminCRUD.getParent()).dispose();
                            System.exit(0);
                            return;
                        }

                        JOptionPane.showMessageDialog(frmTerminCRUD, "Sistem je uspešno izmenio termin", "Uspešno menjanje termina", JOptionPane.INFORMATION_MESSAGE);
                        frmTerminCRUD.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frmTerminCRUD, "Sistem je neuspešno izmenio termin", "Neuspešno menjanje termina", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

            }
        });

        frmTerminCRUD.klikNaTabeluAddActionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (frmTerminCRUD.getBtnPotvrdiIzmene().isEnabled()) {
                    Grupa g;
                    int selektovanaGrupa = frmTerminCRUD.getTblGrupe().getSelectedRow();
                    GrupaTableModel gtm = (GrupaTableModel) frmTerminCRUD.getTblGrupe().getModel();
                    List<Grupa> grupe = gtm.getGrupe();
                    g = grupe.get(selektovanaGrupa);
                    frmTerminCRUD.getTxtGrupaa().setText(g.getNazivGrupe());
                }
            }

        });

    }

    private void fillForm(Termin t) {

        frmTerminCRUD.getTxtId().setText(String.valueOf(t.getIDTermina()));
        frmTerminCRUD.getTxtVremePocetka().setText(String.valueOf(t.getVremePocetka()));
        frmTerminCRUD.getTxtVremeKraja().setText(String.valueOf(t.getVremeKraja()));
        frmTerminCRUD.getTxtTrajanje().setText(String.valueOf(t.getTrajanje()));
        frmTerminCRUD.getTxtBrojClanova().setText(String.valueOf(t.getBrojClanova()));
        frmTerminCRUD.getTxtMaxBrClanova().setText(String.valueOf(t.getMaksBrojClanova()));
        frmTerminCRUD.getTxtGrupaa().setText(t.getG().getNazivGrupe());
        frmTerminCRUD.getTxtDatum().setText(String.valueOf(t.getDatum()));

        frmTerminCRUD.getTxtId().setEnabled(false);
        frmTerminCRUD.getTxtVremePocetka().setEnabled(false);
        frmTerminCRUD.getTxtVremeKraja().setEnabled(false);
        frmTerminCRUD.getTxtTrajanje().setEnabled(false);
        frmTerminCRUD.getTxtBrojClanova().setEnabled(false);
        frmTerminCRUD.getTxtMaxBrClanova().setEnabled(false);
        frmTerminCRUD.getTxtGrupaa().setEnabled(false);
        frmTerminCRUD.getTxtUlogovanTrener().setEnabled(false);
        frmTerminCRUD.getTxtDatum().setEnabled(false);

        frmTerminCRUD.getBtnObrisiTermin().setEnabled(false);
        frmTerminCRUD.getBtnPotvrdiIzmene().setEnabled(false);

        frmTerminCRUD.getPanelDatum().setVisible(false);

        Trener ulogovanTrener = MainCordinator.getInstance().getParamTrener(Constants.LOGGED_TRENER);
        frmTerminCRUD.getTxtUlogovanTrener().setText(ulogovanTrener.toString());

        List<Grupa> grupe;
        try {
            //grupe = Controller.getInstance().getSlobodneGrupe(ulogovanTrener);
            try {
                grupe = Communication.getInstance().getGrupeUlogovanogTrenera(ulogovanTrener);
            } catch (SocketException se) {
                se.printStackTrace();
                frmTerminCRUD.dispose();
                JOptionPane.showMessageDialog(((FrmMain) frmTerminCRUD.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                ((FrmMain) frmTerminCRUD.getParent()).dispose();
                System.exit(0);
                return;
            }
            GrupaTableModel gtm = new GrupaTableModel(grupe);
            frmTerminCRUD.getTblGrupe().setModel(gtm);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
