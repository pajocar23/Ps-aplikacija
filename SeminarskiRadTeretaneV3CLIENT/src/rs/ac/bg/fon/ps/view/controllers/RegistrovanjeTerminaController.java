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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.components.table.GrupaTableModel;
import rs.ac.bg.fon.ps.components.table.TerminTableModel;
import rs.ac.bg.fon.ps.components.table.TerminiBezGrupeTableModel;
import rs.ac.bg.fon.ps.domain.Grupa;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.forms.FrmMain;
import rs.ac.bg.fon.ps.view.forms.FrmRegistrovanjeTermina;

/**
 *
 * @author Mr OLOGIZ
 */
public class RegistrovanjeTerminaController {

    private FrmRegistrovanjeTermina frmRegistrovanjeTermina;
    Trener ulogovanTrener;
    List<Termin> sviTermini;

    public RegistrovanjeTerminaController(FrmRegistrovanjeTermina frmRegistrovanjeTermina) {
        this.frmRegistrovanjeTermina = frmRegistrovanjeTermina;
        addActionListener();
        ulogovanTrener = MainCordinator.getInstance().getParamTrener(Constants.LOGGED_TRENER);
    }

    public void openForm() {
        frmRegistrovanjeTermina.setLocationRelativeTo(null);
        frmRegistrovanjeTermina.setTitle("Registrovanje termina");
        frmRegistrovanjeTermina.setResizable(false);
        fillSKTable();
        frmRegistrovanjeTermina.setVisible(true);
    }

    private void addActionListener() {
        frmRegistrovanjeTermina.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                List<Grupa> grupe;
                try {
                    //grupe = Controller.getInstance().getSlobodneGrupe(ulogovanTrener); //ako je br termina >0
                    try {
                        grupe = Communication.getInstance().getGrupeUlogovanogTrenera(ulogovanTrener);
                    } catch (SocketException se) {
                        se.printStackTrace();
                        frmRegistrovanjeTermina.dispose();
                        JOptionPane.showMessageDialog(((FrmMain) frmRegistrovanjeTermina.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                        ((FrmMain) frmRegistrovanjeTermina.getParent()).dispose();
                        System.exit(0);
                        return;
                    }
                    GrupaTableModel gtm = new GrupaTableModel(grupe);
                    frmRegistrovanjeTermina.getTblGrupe().setModel(gtm);

                    frmRegistrovanjeTermina.getTxtTrenerIzabraneGrupe().setText(String.valueOf(ulogovanTrener));

                    frmRegistrovanjeTermina.getTxtTrenerIzabraneGrupe().setEnabled(false);

                    frmRegistrovanjeTermina.getTxtTrajanjee().setEnabled(false);
                    frmRegistrovanjeTermina.getTxtDatumTermina().setEnabled(false);
                    frmRegistrovanjeTermina.getTxtKrajTermina().setEnabled(false);
                    frmRegistrovanjeTermina.getTxtPocetakTermina().setEnabled(false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Sistem ne može da učita grupe ulogovanog trenera", "Neuspešno učitavanje grupa", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    //sviTermini = Controller.getInstance().getAllTermini();
                    try {
                        sviTermini = Communication.getInstance().getSviTermini();
                    } catch (SocketException se) {
                        se.printStackTrace();
                        frmRegistrovanjeTermina.dispose();
                        JOptionPane.showMessageDialog(((FrmMain) frmRegistrovanjeTermina.getParent()), "Server ugasen", "Prekid", JOptionPane.ERROR_MESSAGE);
                        ((FrmMain) frmRegistrovanjeTermina.getParent()).dispose();
                        System.exit(0);
                        return;
                    }
                    TerminTableModel ttm = new TerminTableModel(sviTermini);
                    frmRegistrovanjeTermina.getTblTermini().setModel(ttm);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmRegistrovanjeTermina, ex.getMessage(), "Neuspešno učitavanje termina", JOptionPane.ERROR_MESSAGE);

                }

            }

        });

        frmRegistrovanjeTermina.klikNatTabeluTerminaActionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int selektovanTermin = frmRegistrovanjeTermina.getTblTermini().getSelectedRow();

                if (selektovanTermin != -1) {
                    Termin selektovaniTermin = sviTermini.get(selektovanTermin);
                    //System.out.println("Date: " + selektovaniTermin.getDatum() + " vr p: " + selektovaniTermin.getVremePocetka());
                    frmRegistrovanjeTermina.getTxtDatumTermina().setText(String.valueOf(selektovaniTermin.getDatum()));
                    frmRegistrovanjeTermina.getTxtPocetakTermina().setText(String.valueOf(selektovaniTermin.getVremePocetka()));
                    frmRegistrovanjeTermina.getTxtKrajTermina().setText(String.valueOf(selektovaniTermin.getVremeKraja()));
                    frmRegistrovanjeTermina.getTxtTrajanjee().setText(String.valueOf(selektovaniTermin.getTrajanje()));

                } else {
                    System.out.println("nije selektovan red");
                }
            }

        });

        frmRegistrovanjeTermina.dodavanjeGrupeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajGrupu(e);
            }

            private void dodajGrupu(ActionEvent e) {
                String nazivGrupe = frmRegistrovanjeTermina.getTxtNazivGrupe().getText();
                int brojTerminaGrupe = 0;

                if (nazivGrupe.isEmpty()) {
                    frmRegistrovanjeTermina.getLblErrorDodavanjeGrupe().setText("Naziv grupe ne sme biti prazan");
                    return;
                }

                if (frmRegistrovanjeTermina.getTxtBrojTermina().getText().isEmpty()) {
                    frmRegistrovanjeTermina.getLblErrorDodavanjeGrupe().setText("Broj termina ne sme biti prazan");
                    return;
                }
                brojTerminaGrupe = Integer.valueOf(frmRegistrovanjeTermina.getTxtBrojTermina().getText());

                Random r = new Random();
                Integer idInt = r.nextInt(100);
                Long id = new Long(idInt);

                Grupa dodataGrupa = new Grupa(id, brojTerminaGrupe, nazivGrupe, ulogovanTrener);

                try {
                    //Controller.getInstance().zapamtiGrupu(dodataGrupa);
                    Communication.getInstance().zapamtiGrupu(dodataGrupa);
                    JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Sistem je zapamtio grupu", "Uspešno dodavanje grupe", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmRegistrovanjeTermina, ex.getMessage(), "Neuspešno dodavanje grupe", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        frmRegistrovanjeTermina.dodavanjeTerminaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajTermin(e);
                //kada se doda termin, broj termina u grupi mora da se smanji
            }

            private void resetForm() {
                frmRegistrovanjeTermina.getLblErrorTermin().setText("");
            }

            private void dodajTermin(ActionEvent e) {
                resetForm();
                try {

                    String dateStr = frmRegistrovanjeTermina.getDateChooserCombo1().getText();
                    String[] splited = dateStr.split("/");
                    String noviDatum = "20" + splited[2] + "-" + splited[0] + "-" + splited[1];
                    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                    Date datumTermina = formatter1.parse(noviDatum);
                    Date danasnjiDatum = new Date();
                    if (!datumTermina.after(danasnjiDatum)) {
                        JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Unesite neki datum u buducnosti", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    //System.out.println("Datum dodavanje novog termina: " + datumTermina);

                    int selektovanaGrupa = frmRegistrovanjeTermina.getTblGrupe().getSelectedRow();
                    Grupa g;
                    if (selektovanaGrupa == -1) {
                        frmRegistrovanjeTermina.getLblErrorTermin().setText("Selektujte grupu iz tabele grupa");
                        return;
                    } else {
                        GrupaTableModel gtm = (GrupaTableModel) frmRegistrovanjeTermina.getTblGrupe().getModel();
                        List<Grupa> grupe = gtm.getGrupe();
                        g = grupe.get(selektovanaGrupa);
                    }

                    Random r = new Random();
                    Integer idInt = r.nextInt(100);
                    Long id = new Long(idInt);

                    //validacija
                    if (frmRegistrovanjeTermina.getTxtVremePocetka().getText().isEmpty()) {
                        frmRegistrovanjeTermina.getLblErrorTermin().setText("Unesite vreme početka");
                        return;
                    } else if (frmRegistrovanjeTermina.getTxtVremeKraja1().getText().isEmpty()) {
                        frmRegistrovanjeTermina.getLblErrorTermin().setText("Unesite vreme kraja");
                        return;
                    } else if (frmRegistrovanjeTermina.getTxtTrajanje().getText().isEmpty()) {
                        frmRegistrovanjeTermina.getLblErrorTermin().setText("Unesite trajanje");
                        return;
                    } else if (frmRegistrovanjeTermina.getTxtMaxBrojClanova().getText().isEmpty()) {
                        frmRegistrovanjeTermina.getLblErrorTermin().setText("Unesite maksimalni broj članova");
                        return;
                    }

                    //validaicja
                    SimpleDateFormat formatter2 = new SimpleDateFormat("hh:mm:ss");
                    Date vremePocetka;
                    Date vremeKraja;
                    Date trajanje;

                    try {
                        vremePocetka = formatter2.parse(frmRegistrovanjeTermina.getTxtVremePocetka().getText());
                        vremeKraja = formatter2.parse(frmRegistrovanjeTermina.getTxtVremeKraja1().getText());
                        trajanje = formatter2.parse(frmRegistrovanjeTermina.getTxtTrajanje().getText());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Nevalidan format datuma\nPratite fomrat 00:00:00", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    System.out.println("vreme pocetka: " + vremePocetka);
                    System.out.println("vreme kraja: " + vremeKraja);

                    if (frmRegistrovanjeTermina.getTxtVremeKraja1().getText().equals("00:00:00")) {
                        System.out.println("Vreme kraja ne moze biti 00:00");
                        JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Vreme kraja ne moze biti 00:00:00", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (vremePocetka.after(vremeKraja)) {
                        System.out.println("Vreme pocetka je posle vremena kraja");
                        JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Vreme pocetka mora biti pre vremena kraja", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int maksBrojClanova = Integer.valueOf(frmRegistrovanjeTermina.getTxtMaxBrojClanova().getText());

                    Termin t = new Termin(id, datumTermina, vremePocetka, vremeKraja, trajanje, 0, maksBrojClanova, g);

                    for (Termin termin : sviTermini) {
                        if (termin.getDatum().equals(t.getDatum())) {
                            //mora da pocne nakon ili da se zavrsi pre
                            if (t.getVremePocetka().after(termin.getVremeKraja()) || t.getVremeKraja().before(termin.getVremePocetka())) {
                                continue;
                            } else {
                                JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Poklapanje termina", "Greska", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }

                    try {
                        //Controller.getInstance().dodajTermin(t);
                        try {
                            Communication.getInstance().zapamtiTermin(t);
                        } catch (SocketException se) {
                            se.printStackTrace();

                        }
                        //JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Sistem ne može da zapamti termin", "Neuspešno dodavanje termina", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Sistem je zapamtio termin", "Uspešno dodavanje termina", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Sistem ne može da zapamti termin", "Neuspešno dodavanje termina", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

            }
        });

        frmRegistrovanjeTermina.btnOmoguciAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                omoguci(e);
            }

            private void omoguci(ActionEvent e) {
                frmRegistrovanjeTermina.getBtnDodajTermineUGrupu().setEnabled(true);
                frmRegistrovanjeTermina.getBtnDodajTermnUTabelu().setEnabled(true);
                frmRegistrovanjeTermina.getBtnOmoguciSK().setEnabled(false);
            }
        });

        frmRegistrovanjeTermina.btnDodajUTabeluTerminAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajUTabeluTermin(e);
            }

            private void dodajUTabeluTermin(ActionEvent e) {
                try {
                    //Termin t=new Termin(Long.MIN_VALUE, datum, vremePocetka, vremeKraja, trajanje, 0, 0, g);
                    Termin t = new Termin();

                    Random r = new Random();
                    Integer idInt = r.nextInt(100);
                    Long id = new Long(idInt);
                    t.setIDTermina(id);

                    String dateStr = frmRegistrovanjeTermina.getDateChooserCombo1().getText();
                    String[] splited = dateStr.split("/");
                    String noviDatum = "20" + splited[2] + "-" + splited[0] + "-" + splited[1];
                    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-mm-dd");
                    Date datumTermina = formatter1.parse(noviDatum);
                    t.setDatum(datumTermina);
                    Date danasnjiDatum = new Date();
                    if (!datumTermina.after(danasnjiDatum)) {
                        JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Unesite neki datum u buducnosti", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    SimpleDateFormat formatter2 = new SimpleDateFormat("hh:mm:ss");
                    Date vremePocetka;
                    Date vremeKraja;
                    Date trajanje;

                    try {
                        vremePocetka = formatter2.parse(frmRegistrovanjeTermina.getTxtVremePocetka().getText());
                        vremeKraja = formatter2.parse(frmRegistrovanjeTermina.getTxtVremeKraja1().getText());
                        trajanje = formatter2.parse(frmRegistrovanjeTermina.getTxtTrajanje().getText());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Nevalidan format datuma\nPratite fomrat 00:00:00", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    t.setVremePocetka(vremePocetka);
                    t.setVremeKraja(vremeKraja);
                    t.setTrajanje(trajanje);

                    int maksBrojClanova = Integer.valueOf(frmRegistrovanjeTermina.getTxtMaxBrojClanova().getText());
                    t.setBrojClanova(0);
                    t.setMaksBrojClanova(maksBrojClanova);

                    if (frmRegistrovanjeTermina.getTxtVremePocetka().getText().isEmpty()) {
                        frmRegistrovanjeTermina.getLblErrorTermin().setText("Unesite vreme početka");
                        return;
                    } else if (frmRegistrovanjeTermina.getTxtVremeKraja1().getText().isEmpty()) {
                        frmRegistrovanjeTermina.getLblErrorTermin().setText("Unesite vreme kraja");
                        return;
                    } else if (frmRegistrovanjeTermina.getTxtTrajanje().getText().isEmpty()) {
                        frmRegistrovanjeTermina.getLblErrorTermin().setText("Unesite trajanje");
                        return;
                    } else if (frmRegistrovanjeTermina.getTxtMaxBrojClanova().getText().isEmpty()) {
                        frmRegistrovanjeTermina.getLblErrorTermin().setText("Unesite maksimalni broj članova");
                        return;
                    }

                    //System.out.println("vreme pocetka: " + vremePocetka);
                    //System.out.println("vreme kraja: " + vremeKraja);
                    if (frmRegistrovanjeTermina.getTxtVremeKraja1().getText().equals("00:00:00")) {
                        System.out.println("Vreme kraja ne moze biti 00:00");
                        JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Vreme kraja ne moze biti 00:00:00", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (vremePocetka.after(vremeKraja)) {
                        System.out.println("Vreme pocetka je posle vremena kraja");
                        JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Vreme pocetka mora biti pre vremena kraja", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    for (Termin termin : sviTermini) {
                        System.out.println("u bazi:" + termin.getDatum());
                        System.out.println("unet:" + t.getDatum());
                        if (termin.getDatum().equals(t.getDatum())) {
                            System.out.println("Naso isti datum " + termin.getDatum());
                            if (t.getVremePocetka().after(termin.getVremeKraja()) || t.getVremeKraja().before(termin.getVremePocetka())) {
                                System.out.println("nema pokl");
                                continue;
                            } else {
                                JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Poklapanje termina", "Greska", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }

                    TerminiBezGrupeTableModel tbgtm = (TerminiBezGrupeTableModel) frmRegistrovanjeTermina.getTblTerminiBezGrupa().getModel();
                    tbgtm.dodajTermin(t);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        frmRegistrovanjeTermina.btnDodajSveTermineZaJednuGrupuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajSveTermineZaJednuGrupu(e);
            }

            private void dodajSveTermineZaJednuGrupu(ActionEvent e) {
                TerminiBezGrupeTableModel tbgtm = (TerminiBezGrupeTableModel) frmRegistrovanjeTermina.getTblTerminiBezGrupa().getModel();
                List<Termin> terminiUTabeliZaDodavanjeUGrupu = tbgtm.getTermini();

                int selektovanaGrupa = frmRegistrovanjeTermina.getTblGrupe().getSelectedRow();
                Grupa g;
                if (selektovanaGrupa == -1) {
                    frmRegistrovanjeTermina.getLblErrorTermin().setText("Selektujte grupu iz tabele grupa");
                    return;
                } else {
                    GrupaTableModel gtm = (GrupaTableModel) frmRegistrovanjeTermina.getTblGrupe().getModel();
                    List<Grupa> grupe = gtm.getGrupe();
                    g = grupe.get(selektovanaGrupa);
                }

                if (!terminiUTabeliZaDodavanjeUGrupu.isEmpty()) {
                    for (Termin termin : terminiUTabeliZaDodavanjeUGrupu) {
                        termin.setG(g);

                        for (Termin termin1 : sviTermini) {
                            System.out.println("u bazi:" + termin1.getDatum());
                            System.out.println("unet:" + termin.getDatum());
                            if (termin.getDatum().equals(termin.getDatum())) {
                                System.out.println("Naso isti datum " + termin1.getDatum());
                                if (termin.getVremePocetka().after(termin1.getVremeKraja()) || termin.getVremeKraja().before(termin1.getVremePocetka())) {
                                    System.out.println("nema pokl");
                                    continue;
                                } else {
                                    JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Poklapanje termina", "Greska", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                            }
                        }

                        try {
                            Communication.getInstance().zapamtiTermin(termin);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Sistem ne može da zapamti termin", "Neuspešno dodavanje termina", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(frmRegistrovanjeTermina, "Sistem je zapamtio termine", "Uspešno dodavanje termina", JOptionPane.INFORMATION_MESSAGE);
                    List<Termin> terminiSK = new ArrayList<>();
                    TerminiBezGrupeTableModel prazanModel = new TerminiBezGrupeTableModel(terminiSK);
                    frmRegistrovanjeTermina.getTblTerminiBezGrupa().setModel(prazanModel);
                } else {
                    frmRegistrovanjeTermina.getLblErrorTermin().setText("Ubacite termine u tabelu");
                    return;
                }

            }
        });
    }

    private void fillSKTable() {
        List<Termin> terminiSK = new ArrayList<>();
        TerminiBezGrupeTableModel tbgtm = new TerminiBezGrupeTableModel(terminiSK);
        frmRegistrovanjeTermina.getTblTerminiBezGrupa().setModel(tbgtm);

        frmRegistrovanjeTermina.getBtnDodajTermineUGrupu().setEnabled(false);
        frmRegistrovanjeTermina.getBtnDodajTermnUTabelu().setEnabled(false);

        frmRegistrovanjeTermina.getTxtVremePocetka().setText("00:00:00");
        frmRegistrovanjeTermina.getTxtVremeKraja1().setText("00:00:00");
        frmRegistrovanjeTermina.getTxtTrajanje().setText("00:00:00");
    }
}
