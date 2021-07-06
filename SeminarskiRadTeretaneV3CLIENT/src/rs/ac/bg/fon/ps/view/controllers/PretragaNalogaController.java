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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.components.table.NalogClanaTableModel;
import rs.ac.bg.fon.ps.components.table.PrisustvaGrupamaTableModel;
import rs.ac.bg.fon.ps.components.table.TerminTableModel;
import rs.ac.bg.fon.ps.domain.NalogClana;
import rs.ac.bg.fon.ps.domain.PrisustvoTerminu;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.forms.FrmMain;
import rs.ac.bg.fon.ps.view.forms.FrmPretragaNalogaClanova;

/**
 *
 * @author Mr OLOGIZ
 */
public class PretragaNalogaController {

    List<NalogClana> trazeniNalozi;
    private FrmPretragaNalogaClanova frmPretragaNalogaClanova;
    List<Termin> sviTermini;

    public PretragaNalogaController(FrmPretragaNalogaClanova frmPretragaNalogaClanova) {
        this.frmPretragaNalogaClanova = frmPretragaNalogaClanova;
        trazeniNalozi = new ArrayList<>();
        sviTermini = new ArrayList<>();
        addActionListener();
    }

    public void openForm() {
        frmPretragaNalogaClanova.setLocationRelativeTo(null);
        frmPretragaNalogaClanova.setTitle("Forma za pretragu naloga");
        frmPretragaNalogaClanova.setResizable(false);
        prepareView();
        frmPretragaNalogaClanova.setVisible(true);

    }

    private void addActionListener() {
        frmPretragaNalogaClanova.pretragaNalogaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pronadjiNalogClana(e);
            }

            private void pronadjiNalogClana(ActionEvent e) {
                String kriterijum = String.valueOf(frmPretragaNalogaClanova.getCbKriterijumPretrage().getSelectedItem());
                String vrednost = frmPretragaNalogaClanova.getTxtPretraziValue().getText();

                try {
                    //trazeniNalozi = Controller.getInstance().getTrazeniNalozi(kriterijum, vrednost);
                    try {
                        trazeniNalozi = Communication.getInstance().getTrazeniNalozi(kriterijum, vrednost);
                    } catch (SocketException se) {
                        frmPretragaNalogaClanova.dispose();
                        JOptionPane.showMessageDialog(((FrmMain) frmPretragaNalogaClanova.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                        ((FrmMain) frmPretragaNalogaClanova.getParent()).dispose();
                        System.exit(0);
                        return;
                    }
                    if (!trazeniNalozi.isEmpty()) {
                        frmPretragaNalogaClanova.getLblNoResaults().setText("");
                        frmPretragaNalogaClanova.getBtnUcitajNalogClana().setEnabled(true);
                        NalogClanaTableModel ncm = new NalogClanaTableModel(trazeniNalozi);
                        frmPretragaNalogaClanova.getTblRezultati().setModel(ncm);
                        JOptionPane.showMessageDialog(frmPretragaNalogaClanova, "Sistem je našao naloge članova po zadatoj vrednosti", "Uspešna pretraga", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        frmPretragaNalogaClanova.getBtnUcitajNalogClana().setEnabled(false);
                        frmPretragaNalogaClanova.getLblNoResaults().setText("Nema rezultata pretrage");
                        JOptionPane.showMessageDialog(frmPretragaNalogaClanova, "Sistem nije našao naloge članova po zadatoj vrednosti", "Neuspešna pretraga", JOptionPane.WARNING_MESSAGE);
                        prepareEmptyTable();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });

        frmPretragaNalogaClanova.otvorinalogAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ucitajNalogClana(e);
            }

            private void ucitajNalogClana(ActionEvent e) {
                NalogClanaTableModel nctm = (NalogClanaTableModel) frmPretragaNalogaClanova.getTblRezultati().getModel();
                int row = frmPretragaNalogaClanova.getTblRezultati().getSelectedRow();
                NalogClana selektovaniNalog = trazeniNalozi.get(row);

                try {
                    MainCordinator.getInstance().addParamNalog(Constants.SELECTED_NALOG, selektovaniNalog);

                    //NalogClana nc= Controller.getInstance().ucitajNalogClana(selektovaniNalog);
                    NalogClana nc;
                    try {
                        nc = Communication.getInstance().ucitajNalogClana(selektovaniNalog);
                    } catch (SocketException se) {
                        se.printStackTrace();
                        frmPretragaNalogaClanova.dispose();
                        JOptionPane.showMessageDialog(((FrmMain) frmPretragaNalogaClanova.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                        ((FrmMain) frmPretragaNalogaClanova.getParent()).dispose();
                        System.exit(0);
                        return;
                    }

                    if (nc != null) {
                        JOptionPane.showMessageDialog(frmPretragaNalogaClanova, "Sistem je učitao nalog člana", "Uspešno učitavanje", JOptionPane.INFORMATION_MESSAGE);
                        MainCordinator.getInstance().openFrmNalogClanaCRUD(selektovaniNalog);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPretragaNalogaClanova, "Sistem ne može da učita nalog člana", "Neuspešno učitavanje", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        frmPretragaNalogaClanova.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                try {

                    String kriterijum = String.valueOf(frmPretragaNalogaClanova.getCbKriterijumPretrage().getSelectedItem());
                    String vrednostPretrage = frmPretragaNalogaClanova.getTxtPretraziValue().getText();
                    //trazeniNalozi = Controller.getInstance().getTrazeniNalozi(kriterijum, vrednostPretrage);

                    if (!vrednostPretrage.equals("")) {
                        try {
                            trazeniNalozi = Communication.getInstance().getTrazeniNalozi(kriterijum, vrednostPretrage);
                        } catch (SocketException se) {
                            se.printStackTrace();
                            frmPretragaNalogaClanova.dispose();
                            JOptionPane.showMessageDialog(((FrmMain) frmPretragaNalogaClanova.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                            ((FrmMain) frmPretragaNalogaClanova.getParent()).dispose();
                            System.exit(0);
                            return;
                        }
                    }

                    if (!trazeniNalozi.isEmpty()) {
                        frmPretragaNalogaClanova.getBtnUcitajNalogClana().setEnabled(true);
                        NalogClanaTableModel ncm = new NalogClanaTableModel(trazeniNalozi);
                        frmPretragaNalogaClanova.getTblRezultati().setModel(ncm);
                    } else {
                        frmPretragaNalogaClanova.getBtnUcitajNalogClana().setEnabled(false);
                        prepareEmptyTable();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPretragaNalogaClanova, "Greška u osvežavanju tabele", "Neuspešno osvežavanje", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        frmPretragaNalogaClanova.OmoguciDodavanjeNalogaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                omoguciDodavanje(e);
            }

            private void omoguciDodavanje(ActionEvent e) {
                frmPretragaNalogaClanova.getBtnDodajNalogUTermin().setEnabled(true);
                frmPretragaNalogaClanova.getBtnOmoguciDodavanjeNalogaUTermin().setEnabled(false);
            }
        });

        frmPretragaNalogaClanova.tabelaTerminiaddActionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int selektovanTermin = frmPretragaNalogaClanova.getTblTermini().getSelectedRow();

                if (selektovanTermin != -1) {
                    Termin selektovaniTermin = sviTermini.get(selektovanTermin);

                    frmPretragaNalogaClanova.getTxtDatumTermina().setText(String.valueOf(selektovaniTermin.getDatum()));
                    frmPretragaNalogaClanova.getTxtPocetakTermina().setText(String.valueOf(selektovaniTermin.getVremePocetka()));
                    frmPretragaNalogaClanova.getTxtKrajTermina().setText(String.valueOf(selektovaniTermin.getVremeKraja()));
                    frmPretragaNalogaClanova.getTxtTrajanjee().setText(String.valueOf(selektovaniTermin.getTrajanje()));

                } else {
                    System.out.println("nije selektovan red");
                }
            }

        });

        frmPretragaNalogaClanova.DodajNalogUTerminAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajNalogUTermin(e);
            }

            private void dodajNalogUTermin(ActionEvent e) {
                int brojSelektovanogNaloga = frmPretragaNalogaClanova.getTblRezultati().getSelectedRow();
                NalogClanaTableModel nctm = (NalogClanaTableModel) frmPretragaNalogaClanova.getTblRezultati().getModel();
                List<NalogClana> nalozi = nctm.getNalozi();
                NalogClana nc = null;
                Termin t = null;
                if (brojSelektovanogNaloga != -1) {
                    nc = nalozi.get(brojSelektovanogNaloga);
                }

                int brojSelektovanogTermina = frmPretragaNalogaClanova.getTblTermini().getSelectedRow();
                TerminTableModel ttm = (TerminTableModel) frmPretragaNalogaClanova.getTblTermini().getModel();
                List<Termin> termini = new ArrayList<>();
                try {
                    termini = Communication.getInstance().getSviTermini();
                } catch (SocketException se) {
                    se.printStackTrace();
                    frmPretragaNalogaClanova.dispose();
                    JOptionPane.showMessageDialog(((FrmMain) frmPretragaNalogaClanova.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                    ((FrmMain) frmPretragaNalogaClanova.getParent()).dispose();
                    System.exit(0);
                    return;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (brojSelektovanogTermina != -1) {
                    t = termini.get(brojSelektovanogTermina);
                }

                Random r = new Random();
                Integer idInt = r.nextInt(100);
                Long id = new Long(idInt);

                if (brojSelektovanogNaloga == -1) {
                    frmPretragaNalogaClanova.getLblError().setText("Odaberite nalog koji želite da dodate");
                    return;
                } else if (brojSelektovanogTermina == -1) {
                    frmPretragaNalogaClanova.getLblError().setText("Odaberite termin u koji želite da dodate nalog");
                    return;
                }

                PrisustvoTerminu pt = new PrisustvoTerminu(id, nc, t);

                try {
                    try {
                        Communication.getInstance().zapamtiPrisustvoTerminu(pt);
                    } catch (SocketException se) {
                        se.printStackTrace();
                        frmPretragaNalogaClanova.dispose();
                        JOptionPane.showMessageDialog(((FrmMain) frmPretragaNalogaClanova.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                        ((FrmMain) frmPretragaNalogaClanova.getParent()).dispose();
                        System.exit(0);
                        return;
                    }
                    JOptionPane.showMessageDialog(frmPretragaNalogaClanova, "Sistem je zapamtio prisustvo terminu", "uspešno dodavanje prisustva", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmPretragaNalogaClanova, "Sistem nije zapamtio prisustvo terminu\n" + ex.getMessage(), "neuspešno dodavanje prisustva", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        frmPretragaNalogaClanova.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                updatujTabeluTerminaISvaPrisustvaOdredjenogNaloga(e);
            }

            private void updatujTabeluTerminaISvaPrisustvaOdredjenogNaloga(WindowEvent e) {
                prepareTableTermini();
                prepareTablePrisustva();
            }

        });

        frmPretragaNalogaClanova.rezultatiPretrageNalogaAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ucitajTermineSelektovanogNaloga(e);
            }

            private void ucitajTermineSelektovanogNaloga(MouseEvent e) {
                NalogClanaTableModel nctm = (NalogClanaTableModel) frmPretragaNalogaClanova.getTblRezultati().getModel();
                int selektovaniNalog = frmPretragaNalogaClanova.getTblRezultati().getSelectedRow();
                NalogClana nc = null;
                if (selektovaniNalog != -1) {
                    nc = trazeniNalozi.get(selektovaniNalog);
                }
                popuniTabeluSaTerminaSelektovanogNaloga(nc);
            }

            private void popuniTabeluSaTerminaSelektovanogNaloga(NalogClana selektovaniNalog) {
                List<PrisustvoTerminu> svaPrisustvaSelektovanogNaloga = new ArrayList<>();
                List<PrisustvoTerminu> svaPrisustva = new ArrayList<>();

                try {
                    svaPrisustva = Communication.getInstance().getSvaPrisustva();
                } catch (SocketException se) {
                    frmPretragaNalogaClanova.dispose();
                    JOptionPane.showMessageDialog(((FrmMain) frmPretragaNalogaClanova.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                    ((FrmMain) frmPretragaNalogaClanova.getParent()).dispose();
                    System.exit(0);
                    return;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                for (PrisustvoTerminu prisustvoTerminu : svaPrisustva) {
                    if (Objects.equals(prisustvoTerminu.getNalogClana().getIdClana(), selektovaniNalog.getIdClana())) {
                        svaPrisustvaSelektovanogNaloga.add(prisustvoTerminu);
                    }
                }

                PrisustvaGrupamaTableModel ptm = new PrisustvaGrupamaTableModel(svaPrisustvaSelektovanogNaloga);
                frmPretragaNalogaClanova.getTblPrisustva().setModel(ptm);
            }

        });

    }

    private void prepareView() {
        frmPretragaNalogaClanova.getBtnUcitajNalogClana().setEnabled(false);
        frmPretragaNalogaClanova.getCbKriterijumPretrage().removeAllItems();
        String element1 = "Ime i prezime";
        frmPretragaNalogaClanova.getCbKriterijumPretrage().addItem(element1);
        String element2 = "JBMG";
        frmPretragaNalogaClanova.getCbKriterijumPretrage().addItem(element2);

        prepareEmptyTable();
        prepareTableTermini();
        prepareTablePrisustva();
        Trener ulogovanTrener = MainCordinator.getInstance().getParamTrener(Constants.LOGGED_TRENER);
        frmPretragaNalogaClanova.getTxtUlogovanTrener().setText(ulogovanTrener.toString());
        frmPretragaNalogaClanova.getTxtUlogovanTrener().setEnabled(false);
        frmPretragaNalogaClanova.getBtnDodajNalogUTermin().setEnabled(false);
    }

    private void prepareEmptyTable() {
        List<NalogClana> defaultList = new ArrayList<>();
        NalogClanaTableModel ncm = new NalogClanaTableModel(defaultList);
        frmPretragaNalogaClanova.getTblRezultati().setModel(ncm);
    }

    private void prepareTableTermini() {
        sviTermini = new ArrayList<>();
        try {
            sviTermini = Communication.getInstance().getSviTermini();
        } catch (SocketException e) {
            e.printStackTrace();
            frmPretragaNalogaClanova.dispose();
            JOptionPane.showMessageDialog(((FrmMain) frmPretragaNalogaClanova.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
            ((FrmMain) frmPretragaNalogaClanova.getParent()).dispose();
            System.exit(0);
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        TerminTableModel ttm = new TerminTableModel(sviTermini);
        frmPretragaNalogaClanova.getTblTermini().setModel(ttm);
    }

    private void prepareTablePrisustva() {
        List<PrisustvoTerminu> svaPrisustva = new ArrayList<>();
        PrisustvaGrupamaTableModel ptm = new PrisustvaGrupamaTableModel(svaPrisustva);
        frmPretragaNalogaClanova.getTblPrisustva().setModel(ptm);
    }

}
