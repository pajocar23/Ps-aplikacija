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
import rs.ac.bg.fon.ps.view.forms.FrmDodavanjeNalogaUTermin;
import rs.ac.bg.fon.ps.view.forms.FrmMain;

/**
 *
 * @author Mr OLOGIZ
 */
public class DodavanjeNalogaUTerminController {

    private FrmDodavanjeNalogaUTermin frmDodavanjeNalogaUTermin;

    List<NalogClana> sviNalozi;
    List<Termin> sviTermini;
    List<PrisustvoTerminu> svaPrisustva;

    List<NalogClana> selektovaniiNalozi;

    int selektovanTermin;
    int selektovanNalog;

    public DodavanjeNalogaUTerminController(FrmDodavanjeNalogaUTermin frmDodavanjeNalogaUTermin) {
        this.frmDodavanjeNalogaUTermin = frmDodavanjeNalogaUTermin;
        sviNalozi = new ArrayList<>();
        sviTermini = new ArrayList<>();
        svaPrisustva = new ArrayList<>();
        selektovaniiNalozi = new ArrayList<>();
        prepareForm();

        addActionListener();
    }

    public void openForm() {
        frmDodavanjeNalogaUTermin.setLocationRelativeTo(null);
        frmDodavanjeNalogaUTermin.setTitle("Forma za dodavanje naloga članova u termine");
        frmDodavanjeNalogaUTermin.setResizable(false);
        frmDodavanjeNalogaUTermin.setVisible(true);
    }

    private void addActionListener() {
        frmDodavanjeNalogaUTermin.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillForm(e);
            }

            private void fillForm(WindowEvent e) {
                prepareTableSviNalozi();
                prepareTableSviTermini();
                prepareTableSvaPrisustva();
            }

            private void prepareTableSviNalozi() {
                try {
                    sviNalozi = Communication.getInstance().getSviNalozi();
                    //System.out.println("sviNalozi:"+sviNalozi);
                } catch (SocketException se) {
                    se.printStackTrace();
                    frmDodavanjeNalogaUTermin.dispose();
                    JOptionPane.showMessageDialog(((FrmMain) frmDodavanjeNalogaUTermin.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                    ((FrmMain) frmDodavanjeNalogaUTermin.getParent()).dispose();
                    System.exit(0);
                    return;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                NalogClanaTableModel nctm = new NalogClanaTableModel(sviNalozi);
                frmDodavanjeNalogaUTermin.getTblNaloziClanova().setModel(nctm);
            }

            private void prepareTableSviTermini() {
                try {
                    sviTermini = Communication.getInstance().getSviTermini();
                    //System.out.println("sviTermini:"+sviTermini);
                } catch (SocketException se) {
                    frmDodavanjeNalogaUTermin.dispose();
                    JOptionPane.showMessageDialog(((FrmMain) frmDodavanjeNalogaUTermin.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                    ((FrmMain) frmDodavanjeNalogaUTermin.getParent()).dispose();
                    System.exit(0);
                    return;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                TerminTableModel ttm = new TerminTableModel(sviTermini);
                frmDodavanjeNalogaUTermin.getTblTermini().setModel(ttm);
            }

            private void prepareTableSvaPrisustva() {
                try {
                    svaPrisustva = Communication.getInstance().getSvaPrisustva();
                    //System.out.println("svaPrisustva:"+svaPrisustva);
                } catch (SocketException se) {
                    frmDodavanjeNalogaUTermin.dispose();
                    JOptionPane.showMessageDialog(((FrmMain) frmDodavanjeNalogaUTermin.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                    ((FrmMain) frmDodavanjeNalogaUTermin.getParent()).dispose();
                    System.exit(0);
                    return;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                PrisustvaGrupamaTableModel ptm = new PrisustvaGrupamaTableModel(svaPrisustva);
                frmDodavanjeNalogaUTermin.getTblPrisustvaa().setModel(ptm);
            }

        });

        frmDodavanjeNalogaUTermin.btnOmoguciSKAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                omoguci(e);
            }

            private void omoguci(ActionEvent e) {
                frmDodavanjeNalogaUTermin.getBtnDodajNalogUTabelu().setEnabled(true);
                frmDodavanjeNalogaUTermin.getBtnOmoguciDodavanjeViseNaloga().setEnabled(false);
            }
        });

        frmDodavanjeNalogaUTermin.btnDodajNalogeUTerminAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajNalogeuTermin(e);
            }

            private void dodajNalogeuTermin(ActionEvent e) {

                selektovanTermin = frmDodavanjeNalogaUTermin.getTblTermini().getSelectedRow();
                Termin selektovaniTermin = null;

                Random r = new Random();
                Integer idInt = r.nextInt(100);
                Long id = new Long(idInt);

                if (selektovanTermin != -1) {
                    selektovaniTermin = sviTermini.get(selektovanTermin);
                } else {
                    System.out.println("nije selektovan red");
                    return;
                }

                for (NalogClana nalogClana : selektovaniiNalozi) {
                    PrisustvoTerminu pt = new PrisustvoTerminu(id, nalogClana, selektovaniTermin);
                    try {
                        Communication.getInstance().zapamtiPrisustvoTerminu(pt);
                    } catch (SocketException se) {
                        frmDodavanjeNalogaUTermin.dispose();
                        JOptionPane.showMessageDialog(((FrmMain) frmDodavanjeNalogaUTermin.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                        ((FrmMain) frmDodavanjeNalogaUTermin.getParent()).dispose();
                        System.exit(0);
                        return;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frmDodavanjeNalogaUTermin, "Sistem nije zapamtio prisustva terminu\n" + ex.getMessage(), "neuspešno dodavanje prisustva", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frmDodavanjeNalogaUTermin, "Sistem je zapamtio prisustvo terminu", "uspešno dodavanje prisustva", JOptionPane.INFORMATION_MESSAGE);
                //JOptionPane.showMessageDialog(frmDodavanjeNalogaUTermin, "Sistem ne može da zapamti prisustvo terminu", "neuspešno dodavanje prisustva", JOptionPane.ERROR_MESSAGE);
            }
        });

        frmDodavanjeNalogaUTermin.btnDodajViseNalogaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajUTabelu(e);
            }

            private void dodajUTabelu(ActionEvent e) {
                NalogClanaTableModel ntm = (NalogClanaTableModel) frmDodavanjeNalogaUTermin.getTblNaloziClanovaZaDodavanje().getModel();

                NalogClana nalog = null;
                Termin termin = null;
                selektovanNalog = frmDodavanjeNalogaUTermin.getTblNaloziClanova().getSelectedRow();
                if (selektovanNalog != -1) {
                    nalog = sviNalozi.get(selektovanNalog);

                    if (!ntm.getNalozi().contains(nalog)) {
                        ntm.insertNalog(nalog);
                        selektovaniiNalozi.add(nalog);
                    } else {
                        frmDodavanjeNalogaUTermin.getLblError().setText("Nalog je vec dodat");
                        return;
                    }
                } else {
                    frmDodavanjeNalogaUTermin.getLblError().setText("Nije selektovan nalog");
                    return;
                }

            }
        });

        frmDodavanjeNalogaUTermin.mouseClickedTblTermin(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillTextFields(e);
            }

            private void fillTextFields(MouseEvent e) {
                selektovanTermin = frmDodavanjeNalogaUTermin.getTblTermini().getSelectedRow();

                if (selektovanTermin != -1) {
                    Termin selektovaniTermin = sviTermini.get(selektovanTermin);

                    frmDodavanjeNalogaUTermin.getTxtDatumTermina().setText(String.valueOf(selektovaniTermin.getDatum()));
                    frmDodavanjeNalogaUTermin.getTxtPocetakTermina().setText(String.valueOf(selektovaniTermin.getVremePocetka()));
                    frmDodavanjeNalogaUTermin.getTxtKrajTermina().setText(String.valueOf(selektovaniTermin.getVremeKraja()));
                    frmDodavanjeNalogaUTermin.getTxtTrajanjee().setText(String.valueOf(selektovaniTermin.getTrajanje()));

                } else {
                    System.out.println("nije selektovan red");
                }
            }

        });

        frmDodavanjeNalogaUTermin.btnDodajNalogUTerminAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajNalogUTermin(e);
            }

            private void dodajNalogUTermin(ActionEvent e) {
                NalogClana nalog = null;
                Termin termin = null;
                selektovanNalog = frmDodavanjeNalogaUTermin.getTblNaloziClanova().getSelectedRow();
                selektovanTermin = frmDodavanjeNalogaUTermin.getTblTermini().getSelectedRow();
                System.out.println("selektovan red naloga: " + selektovanNalog);
                System.out.println("selektovan red termina: " + selektovanTermin);
                if (selektovanNalog != -1) {
                    nalog = sviNalozi.get(selektovanNalog);
                } else {
                    frmDodavanjeNalogaUTermin.getLblError().setText("Nije selektovan nalog");
                    return;
                }

                if (selektovanTermin != -1) {
                    termin = sviTermini.get(selektovanTermin);
                } else {
                    frmDodavanjeNalogaUTermin.getLblError().setText("Nije selektovan termin");
                    return;
                }

                Random r = new Random();
                Integer idInt = r.nextInt(100);
                Long id = new Long(idInt);

                PrisustvoTerminu pt = new PrisustvoTerminu(id, nalog, termin);
                try {
                    try {
                        Communication.getInstance().zapamtiPrisustvoTerminu(pt);
                    } catch (SocketException se) {
                        frmDodavanjeNalogaUTermin.dispose();
                        JOptionPane.showMessageDialog(((FrmMain) frmDodavanjeNalogaUTermin.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                        ((FrmMain) frmDodavanjeNalogaUTermin.getParent()).dispose();
                        System.exit(0);
                        return;
                    }
                    selektovanNalog = -1;
                    selektovanTermin = -1;
                    JOptionPane.showMessageDialog(frmDodavanjeNalogaUTermin, "Sistem je zapamtio prisustvo terminu", "uspešno dodavanje prisustva", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmDodavanjeNalogaUTermin, "Sistem nije zapamtio prisustvo terminu\n" + ex.getMessage(), "neuspešno dodavanje prisustva", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    private void prepareForm() {
        frmDodavanjeNalogaUTermin.getBtnDodajNalogUTabelu().setEnabled(false);

        List<NalogClana> prazniNalozi = new ArrayList<>();
        NalogClanaTableModel nctm = new NalogClanaTableModel(prazniNalozi);
        frmDodavanjeNalogaUTermin.getTblNaloziClanovaZaDodavanje().setModel(nctm);
    }

}
