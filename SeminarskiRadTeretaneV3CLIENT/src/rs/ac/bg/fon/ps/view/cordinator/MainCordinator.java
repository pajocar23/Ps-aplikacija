/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.cordinator;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import rs.ac.bg.fon.ps.domain.NalogClana;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.view.controllers.DodavanjeNalogaUTerminController;
import rs.ac.bg.fon.ps.view.controllers.LogInController;
import rs.ac.bg.fon.ps.view.controllers.MainController;
import rs.ac.bg.fon.ps.view.controllers.NalogClanaCRUDController;
import rs.ac.bg.fon.ps.view.controllers.PretragaNalogaController;
import rs.ac.bg.fon.ps.view.controllers.PretragaTerminaController;
import rs.ac.bg.fon.ps.view.controllers.RegistrovanjeNalogaController;
import rs.ac.bg.fon.ps.view.controllers.RegistrovanjeTerminaController;
import rs.ac.bg.fon.ps.view.controllers.TerminCRUDController;
import rs.ac.bg.fon.ps.view.forms.FrmDodavanjeNalogaUTermin;
import rs.ac.bg.fon.ps.view.forms.FrmLogIn;
import rs.ac.bg.fon.ps.view.forms.FrmMain;
import rs.ac.bg.fon.ps.view.forms.FrmNalogClanaCRUD;
import rs.ac.bg.fon.ps.view.forms.FrmPretragaNalogaClanova;
import rs.ac.bg.fon.ps.view.forms.FrmPretragaTermina;
import rs.ac.bg.fon.ps.view.forms.FrmRegistrovanjeNalogaClana;
import rs.ac.bg.fon.ps.view.forms.FrmRegistrovanjeTermina;
import rs.ac.bg.fon.ps.view.forms.FrmTerminCRUD;

/**
 *
 * @author Mr OLOGIZ
 */
public class MainCordinator {

    private static MainCordinator instance;

    private MainController mainController;

    private Map<String, Object> params;
    
    private MainCordinator() {
        mainController = new MainController(new FrmMain());
        params = new HashMap<>();
    }

    public static MainCordinator getInstance() {
        if (instance == null) {
            instance = new MainCordinator();
        }
        return instance;
    }

    public void openLoginForm() {
        LogInController loginContoller = new LogInController(new FrmLogIn());
        loginContoller.openForm();
    }

    public void openMainForm() {
        mainController.openForm();
    }

    public void openFrmRegistrovanjeNaloga() {
        RegistrovanjeNalogaController rnc = new RegistrovanjeNalogaController(new FrmRegistrovanjeNalogaClana(mainController.getFrmMain(), true));
        rnc.openForm();
    }

    public void addParamTrener(String name, Trener trener) {
        params.put(name, trener);
    }

    public Trener getParamTrener(String name) {
        return (Trener) params.get(name);
    }

    public void addParamNalog(String name, NalogClana nalog) {
        params.put(name, nalog);
    }

    public NalogClana getParamNalog(String name) {
        return (NalogClana) params.get(name);
    }

    public void addParamTermin(String name, Termin termin) {
        params.put(name, termin);
    }

    public Termin getParamTermin(String name) {
        return (Termin) params.get(name);
    }

    public void openPretragaNalogaForm() {
        PretragaNalogaController pncc = new PretragaNalogaController(new FrmPretragaNalogaClanova(mainController.getFrmMain(), true));
        pncc.openForm();
    }

    public void openFrmNalogClanaCRUD(NalogClana nc) {
        NalogClanaCRUDController nccc = new NalogClanaCRUDController(new FrmNalogClanaCRUD(mainController.getFrmMain(), true));
        nccc.openForm(nc);
    }

    public void openFrmRegistrovanjeTermina() {
        RegistrovanjeTerminaController rtc = new RegistrovanjeTerminaController(new FrmRegistrovanjeTermina(mainController.getFrmMain(), true));
        rtc.openForm();
    }

    public void openFrmPretragaTermina() {
        PretragaTerminaController ptc = new PretragaTerminaController(new FrmPretragaTermina(mainController.getFrmMain(), true));
        ptc.openForm();
    }
    
    public void openFrmTerminCRUD(Termin t) {
        TerminCRUDController tcc = new TerminCRUDController(new FrmTerminCRUD(mainController.getFrmMain(), true));
        tcc.openForm(t);
        
    }

    public void openFrmDodavanjeNalogaUTermin() {
        DodavanjeNalogaUTerminController dc=new DodavanjeNalogaUTerminController(new FrmDodavanjeNalogaUTermin(mainController.getFrmMain(), true));
        dc.openForm();
    }


}
