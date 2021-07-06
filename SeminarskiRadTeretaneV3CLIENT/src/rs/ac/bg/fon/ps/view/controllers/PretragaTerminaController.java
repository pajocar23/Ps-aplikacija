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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.components.table.TerminTableModel;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.forms.FrmMain;
import rs.ac.bg.fon.ps.view.forms.FrmPretragaTermina;

/**
 *
 * @author Mr OLOGIZ
 */
public class PretragaTerminaController {

    private FrmPretragaTermina frmPretragaTermina;
    private List<Termin> trazeniTermini;
    String vrednost;
    String kriterijum;
    Termin selektovaniTerminn;
    int selektovanTermin;

    public PretragaTerminaController(FrmPretragaTermina frmPretragaTermina) {
        this.frmPretragaTermina = frmPretragaTermina;
        addActionListener();
    }

    public void openForm() {
        frmPretragaTermina.setLocationRelativeTo(null);
        frmPretragaTermina.setResizable(false);
        frmPretragaTermina.setTitle("Forma za pretragu termina");
        prepareView();
        frmPretragaTermina.setVisible(true);
    }

    private void addActionListener() {
        frmPretragaTermina.klikNatTabeluTerminaActionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selektovanTermin = frmPretragaTermina.getTblTermini().getSelectedRow();

                if (selektovanTermin != -1) {
                    selektovaniTerminn = trazeniTermini.get(selektovanTermin);
                    MainCordinator.getInstance().addParamTermin(Constants.SELECTED_TERMIN, selektovaniTerminn);
                    Termin t = MainCordinator.getInstance().getParamTermin(Constants.SELECTED_TERMIN);

                    frmPretragaTermina.getTxtDatumTermina().setText(String.valueOf(selektovaniTerminn.getDatum()));
                    frmPretragaTermina.getTxtPocetakTermina().setText(String.valueOf(selektovaniTerminn.getVremePocetka()));
                    frmPretragaTermina.getTxtKrajTermina().setText(String.valueOf(selektovaniTerminn.getVremeKraja()));
                    frmPretragaTermina.getTxtTrajanje().setText(String.valueOf(selektovaniTerminn.getTrajanje()));

                } else {
                    System.out.println("nije selektovan red");
                }
            }

        });

        frmPretragaTermina.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                osveziTabelu(e);
            }

            private void osveziTabelu(WindowEvent e) {
                //vrednost = frmPretragaTermina.getTxtPretraziValue().getText();
                kriterijum = String.valueOf(frmPretragaTermina.getCbKriterijumPretrage().getSelectedItem());
                if (kriterijum.equals("Datum")) {
                    vrednost = frmPretragaTermina.getDateChooser().getText();
                } else {
                    vrednost = frmPretragaTermina.getTxtPretraziValue().getText();
                }

                if (!"".equals(vrednost)) {
                    /*try {
                        trazeniTermini = Communication.getInstance().getTrazeniTermini(kriterijum, vrednost);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    TerminTableModel ttm = new TerminTableModel(trazeniTermini);
                    frmPretragaTermina.getTblTermini().setModel(ttm);*/
                }
            }

        });

        frmPretragaTermina.ucitajTerminAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ucitajTermin(e);
            }

            private void ucitajTermin(ActionEvent e) {
                Termin termin;
                try {
                    //Termin termin = Controller.getInstance().ucitajTermin(selektovaniTerminuTabeli);
                    try {
                        termin = Communication.getInstance().ucitajTermin(selektovaniTerminn);
                    } catch (SocketException se) {
                        frmPretragaTermina.dispose();
                        JOptionPane.showMessageDialog(((FrmMain) frmPretragaTermina.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                        ((FrmMain) frmPretragaTermina.getParent()).dispose();
                        System.exit(0);
                        return;
                    }

                    if (termin != null && selektovanTermin != -1) {
                        JOptionPane.showMessageDialog(frmPretragaTermina, "Sistem je učitao termin", "Usepešno učitavanje", JOptionPane.INFORMATION_MESSAGE);
                        //JOptionPane.showMessageDialog(frmPretragaTermina, "Sistem ne može da učita termin", "Greška", JOptionPane.ERROR_MESSAGE);
                        MainCordinator.getInstance().openFrmTerminCRUD(termin);
                        selektovanTermin = -1;
                        ///////////////

                        kriterijum = String.valueOf(frmPretragaTermina.getCbKriterijumPretrage().getSelectedItem());
                        if (kriterijum.equals("Datum")) {
                            vrednost = frmPretragaTermina.getDateChooser().getText();
                        } else {
                            vrednost = frmPretragaTermina.getTxtPretraziValue().getText();
                        }

                        //trazeniTermini = Controller.getInstance().getTrazeniTermini(kriterijum, vrednost);
                        try {
                            trazeniTermini = Communication.getInstance().getTrazeniTermini(kriterijum, vrednost);
                        } catch (SocketException se) {
                            frmPretragaTermina.dispose();
                            JOptionPane.showMessageDialog(((FrmMain) frmPretragaTermina.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                            ((FrmMain) frmPretragaTermina.getParent()).dispose();
                            System.exit(0);
                            return;
                        }
                        TerminTableModel ttm = new TerminTableModel(trazeniTermini);
                        frmPretragaTermina.getTblTermini().setModel(ttm);
                        //////////////
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPretragaTermina, "Sistem nije učitao termin", "Neusepešno učitavanje", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        frmPretragaTermina.promeniNaDatum(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDatumCbPanel(e);
            }

            private void handleDatumCbPanel(ActionEvent e) {
                if (frmPretragaTermina.getCbKriterijumPretrage().getSelectedItem() == "Datum") {
                    frmPretragaTermina.getPanelPtretraziPoDatumu().setVisible(true);
                    frmPretragaTermina.getTxtPretraziValue().setEnabled(false);
                } else {
                    frmPretragaTermina.getPanelPtretraziPoDatumu().setVisible(false);
                    frmPretragaTermina.getTxtPretraziValue().setEnabled(true);
                }

            }
        });

        frmPretragaTermina.pretragaTerminaAddActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pronadjiTermin(e);
            }

            private void pronadjiTermin(ActionEvent e) {

                kriterijum = String.valueOf(frmPretragaTermina.getCbKriterijumPretrage().getSelectedItem());
                if (frmPretragaTermina.getCbKriterijumPretrage().getSelectedItem() == "Datum") {

                    String dateStr = frmPretragaTermina.getDateChooser().getText();
                    String[] splited = dateStr.split("/");

                    if (splited[0].length() == 1) {
                        splited[0] = "0" + splited[0];
                    }
                    if (splited[1].length() == 1) {
                        splited[1] = "0" + splited[1];
                    }
                    String noviDatum = "20" + splited[2] + "-" + splited[0] + "-" + splited[1];

                    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-mm-dd");
                    try {
                        Date datumTermina = formatter1.parse(noviDatum);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    vrednost = noviDatum;
                } else {
                    vrednost = frmPretragaTermina.getTxtPretraziValue().getText();
                }

                try {
                    //trazeniTermini = Controller.getInstance().getTrazeniTermini(kriterijum, vrednost);
                    try {
                        trazeniTermini = Communication.getInstance().getTrazeniTermini(kriterijum, vrednost);
                    } catch (SocketException se) {
                        se.printStackTrace();
                        frmPretragaTermina.dispose();
                        JOptionPane.showMessageDialog(((FrmMain) frmPretragaTermina.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                        ((FrmMain) frmPretragaTermina.getParent()).dispose();
                        System.exit(0);
                        return;
                    }

                    if (!trazeniTermini.isEmpty()) {
                        frmPretragaTermina.getLblNoResaults().setText("");
                        frmPretragaTermina.getBtnUcitajTermin().setEnabled(true);
                        TerminTableModel ttm = new TerminTableModel(trazeniTermini);
                        frmPretragaTermina.getTblTermini().setModel(ttm);
                        JOptionPane.showMessageDialog(frmPretragaTermina, "Sistem je našao termine po zadatoj vrednosti", "Uspešna pretraga", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        frmPretragaTermina.getBtnUcitajTermin().setEnabled(false);
                        prepareEmptyTable();
                        frmPretragaTermina.getLblNoResaults().setText("Nema rezultata pretrage");
                        JOptionPane.showMessageDialog(frmPretragaTermina, "Sistem nije našao termine po zadatoj vrednosti", "Neuspešna pretraga", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private void prepareView() {
        frmPretragaTermina.getBtnUcitajTermin().setEnabled(false);
        frmPretragaTermina.getCbKriterijumPretrage().removeAllItems();
        String element1 = "Datum";
        frmPretragaTermina.getCbKriterijumPretrage().addItem(element1);
        String element2 = "Naziv grupe";
        frmPretragaTermina.getCbKriterijumPretrage().addItem(element2);
        String element3 = "Ime i prezime trenera";
        frmPretragaTermina.getCbKriterijumPretrage().addItem(element3);
        prepareEmptyTable();

    }

    private void prepareEmptyTable() {
        List<Termin> defaultList = new ArrayList<>();
        TerminTableModel ttm = new TerminTableModel(defaultList);
        frmPretragaTermina.getTblTermini().setModel(ttm);
    }

}
