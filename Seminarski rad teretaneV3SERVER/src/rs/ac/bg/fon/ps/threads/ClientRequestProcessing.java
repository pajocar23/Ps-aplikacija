/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.threads;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.communication.Receiver;
import rs.ac.bg.fon.ps.communication.Request;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.Sender;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Grupa;
import rs.ac.bg.fon.ps.domain.NalogClana;
import rs.ac.bg.fon.ps.domain.PrisustvoTerminu;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;

/**
 *
 * @author Mr OLOGIZ
 */
public class ClientRequestProcessing extends Thread {

    Socket socket;
    Sender sender;
    Receiver receiver;
    private boolean kraj = false;

    public ClientRequestProcessing(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {

                Request request = (Request) receiver.receive();
                Response response = new Response();

                try {
                    switch (request.getOperation()) {
                        case LOGIN:
                            Trener trener = (Trener) request.getArgument();
                            if (!MainCordinator.getInstance().sadrziTrenera(Controller.getInstance().logIn(trener.getKorisnickoIme(), trener.getSifra()))) {
                                MainCordinator.getInstance().addTrener(Controller.getInstance().logIn(trener.getKorisnickoIme(), trener.getSifra()));
                                response.setResult(Controller.getInstance().logIn(trener.getKorisnickoIme(), trener.getSifra()));

                                MainCordinator.getInstance().setTerner(Controller.getInstance().logIn(trener.getKorisnickoIme(), trener.getSifra()));
                                MainCordinator.getInstance().setClientSocket(socket); //ovde setujem socket u koordinatoru

                            } else {
                                throw new Exception("Trener je vec ulogovan!");
                            }
                            break;
                        case LOGUT:
                            MainCordinator.getInstance().removeSocket(socket);
                            Trener trener2 = (Trener) request.getArgument();
                            MainCordinator.getInstance().removeTrener(trener2);
                            kraj = true;
                            break;
                        case ZAPAMTI_NALOG_CLANA:
                            NalogClana nc1 = (NalogClana) request.getArgument();
                            response.setResult(Controller.getInstance().zapamtiNalogClana(nc1));
                            break;
                        case NADJI_NALOGE_CLANOVA:
                            String kriterijumNalog = request.getKriterijum();
                            String vrednostNalog = request.getVrednost();
                            response.setResult(Controller.getInstance().getTrazeniNalozi(kriterijumNalog, vrednostNalog));
                            break;
                        case UCITAJ_LISTU_NALOGA_CLANOVA:
                            List<NalogClana> nalozi = Controller.getInstance().getSviNalozi();
                            response.setResult(nalozi);
                            break;
                        case UCITAJ_NALOG_CLANA:
                            NalogClana nc2 = (NalogClana) request.getArgument();
                            response.setResult(Controller.getInstance().ucitajNalogClana(nc2));
                            break;
                        case IZMENI_NALOG_CLANA:
                            NalogClana nc3 = (NalogClana) request.getArgument();
                            Controller.getInstance().izmeniNalogClana(nc3);
                            break;
                        case OBRISI_NALOG_CLANA:
                            NalogClana nc4 = (NalogClana) request.getArgument();
                            Controller.getInstance().obrisiNalogClana(nc4);
                            break;
                        case ZAPAMTI_PRISUSTVO_TERMINU:
                            PrisustvoTerminu pt = (PrisustvoTerminu) request.getArgument();
                            Controller.getInstance().zapamtiPrisustvoTerminu(pt);
                            break;
                        case GET_LISTA_TERMINA:
                            List<Termin> termini = Controller.getInstance().getSviTermini();
                            response.setResult(termini);
                            break;
                        case UCITAJ_LISTU_PRISUSTVA:
                            List<PrisustvoTerminu> prisustva = Controller.getInstance().getSvaPrisustva();
                            response.setResult(prisustva);
                            break;
                        case UCITAJ_LISTU_GRUPA_ULOGOVANOG_TRENERA:
                            Trener ulogovaniTrener = (Trener) request.getArgument();
                            response.setResult(Controller.getInstance().getGrupeUlogovanogTrenera(ulogovaniTrener));
                            break;
                        case ZAPAMTI_GRUPU:
                            Grupa g = (Grupa) request.getArgument();
                            Controller.getInstance().zapamtiGrupu(g);
                            break;
                        case ZAPAMTI_TERMIN:
                            Termin t1 = (Termin) request.getArgument();
                            Controller.getInstance().zapamtiTermin(t1);
                            break;
                        case UCITAJ_TERMIN:
                            Termin t2 = (Termin) request.getArgument();
                            response.setResult(Controller.getInstance().ucitajTermin(t2));
                            break;
                        case GET_TRAZENI_TERMINI:
                            String kriterijumTermin = request.getKriterijum();
                            String vrednostTermin = request.getVrednost();
                            response.setResult(Controller.getInstance().getTrazeniTermini(kriterijumTermin, vrednostTermin));
                            break;
                        case IZMENI_TERMIN:
                            Termin t3 = (Termin) request.getArgument();
                            Controller.getInstance().izmeniTermin(t3);
                            break;
                        case OBRISI_TERMIN:
                            Termin t4 = (Termin) request.getArgument();
                            Controller.getInstance().obrisiTermin(t4);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                }
                sender.send(response);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Greska prilikom obradjivanja klijentskih zahteva\n" + ex.getMessage());
            }
        }
    }

}
