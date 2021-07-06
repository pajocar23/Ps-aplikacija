/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.domain.Grupa;
import rs.ac.bg.fon.ps.domain.NalogClana;
import rs.ac.bg.fon.ps.domain.PrisustvoTerminu;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.helpObjects.Pretraga;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;
import rs.ac.bg.fon.ps.operation.grupa.GetGrupeUlogovanogTrenera;
import rs.ac.bg.fon.ps.operation.grupa.ZapamtiGrupu;
import rs.ac.bg.fon.ps.operation.nalogClana.GetSviNalozi;
import rs.ac.bg.fon.ps.operation.nalogClana.GetTrazeniNalozi;
import rs.ac.bg.fon.ps.operation.nalogClana.IzmeniNalogClana;
import rs.ac.bg.fon.ps.operation.nalogClana.ObrisiNalogClana;
import rs.ac.bg.fon.ps.operation.nalogClana.SaveNalog;
import rs.ac.bg.fon.ps.operation.nalogClana.UcitajNalogClana;
import rs.ac.bg.fon.ps.operation.prisustvoTerminu.GetSvaPrisustva;
import rs.ac.bg.fon.ps.operation.prisustvoTerminu.ZapamtiPrisustvoTerminu;
import rs.ac.bg.fon.ps.operation.termin.GetSviTermini;
import rs.ac.bg.fon.ps.operation.termin.GetTrazeniTermini;
import rs.ac.bg.fon.ps.operation.termin.IzmeniTermin;
import rs.ac.bg.fon.ps.operation.termin.ObrisiTermin;
import rs.ac.bg.fon.ps.operation.termin.UcitajTermin;
import rs.ac.bg.fon.ps.operation.termin.ZapamtiTermin;
import rs.ac.bg.fon.ps.operation.trener.GetSviTreneri;
import rs.ac.bg.fon.ps.operation.trener.LogIn;
import rs.ac.bg.fon.ps.repository.IRepository;
import rs.ac.bg.fon.ps.repository.db.IDbRepository;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBGeneric;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDbGrupa;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDbNalogClana;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDbPrisustvoTerminu;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDbTermin;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDbTrener;

/**
 *
 * @author Mr OLOGIZ
 */
public class Controller {

    private static Controller instance;

    private final IRepository repositoryDbTrener;
    private final IRepository repositoryDbNalogClana;
    private final IRepository repositoryDbGrupa;
    private final IRepository repositoryDbTermin;
    private final IRepository repositoryDbPrisustvoTerminu;

    private final IRepository repositoryGeneric;

    private Controller() {
        repositoryDbTrener = new RepositoryDbTrener();
        repositoryDbNalogClana = new RepositoryDbNalogClana();
        repositoryDbGrupa = new RepositoryDbGrupa();
        repositoryDbTermin = new RepositoryDbTermin();
        repositoryDbPrisustvoTerminu = new RepositoryDbPrisustvoTerminu();
        repositoryGeneric = new RepositoryDBGeneric();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public List<Trener> getSviTreneri() throws Exception { //       
        AbstractGenericOperation operation = new GetSviTreneri();
        operation.execute(new Object());
        return ((GetSviTreneri) operation).getSviTreneri();
    }

    public Trener logIn(String username, String password) throws Exception {

        Trener pom = new Trener();
        pom.setKorisnickoIme(username);
        pom.setSifra(password);

        AbstractGenericOperation operaiton = new LogIn();
        operaiton.execute(pom);
        Trener t = ((LogIn) operaiton).getTrener();
        return t;
    }

    public NalogClana zapamtiNalogClana(NalogClana nc) throws Exception {
        AbstractGenericOperation operation = new SaveNalog();
        operation.execute(nc);
        return nc;
    }

    public List<NalogClana> getSviNalozi() throws Exception {
        AbstractGenericOperation operation = new GetSviNalozi();
        operation.execute(new Object());
        return ((GetSviNalozi) operation).getSviNalozi();
    }

    public List<NalogClana> getTrazeniNalozi(String kriterijum, String vrednost) throws Exception {

        Pretraga p = new Pretraga(kriterijum, vrednost);

        AbstractGenericOperation operation = new GetTrazeniNalozi();
        operation.execute(p);
        return ((GetTrazeniNalozi) operation).getTrazeniNalozi();

    }

    public void obrisiNalogClana(NalogClana ncZaBrisanje) throws Exception {

        AbstractGenericOperation operation = new ObrisiNalogClana();
        operation.execute(ncZaBrisanje);
    }

    public void izmeniNalogClana(NalogClana nc) throws Exception {

        AbstractGenericOperation operation = new IzmeniNalogClana();
        operation.execute(nc);
    }

    public List<Grupa> getGrupeUlogovanogTrenera(Trener t) throws Exception {

        AbstractGenericOperation operation = new GetGrupeUlogovanogTrenera();
        operation.execute(t);
        return ((GetGrupeUlogovanogTrenera) operation).getGrupeUlogovanogTrenera();
    }

    public Grupa zapamtiGrupu(Grupa g) throws Exception {

        AbstractGenericOperation operation = new ZapamtiGrupu();
        operation.execute(g);
        return ((ZapamtiGrupu) operation).getG();
    }

    public List<Termin> getSviTermini() throws Exception {

        AbstractGenericOperation operation = new GetSviTermini();
        operation.execute(new Termin());
        return ((GetSviTermini) operation).getSviTermini();
    }

    public void zapamtiTermin(Termin t) throws Exception {

        AbstractGenericOperation operation = new ZapamtiTermin();
        operation.execute(t);
    }

    public List<Termin> getTrazeniTermini(String kriterijum, String vrednost) throws Exception {

        Pretraga pret = new Pretraga(kriterijum, vrednost);
        AbstractGenericOperation operation = new GetTrazeniTermini();
        operation.execute(pret);
        return ((GetTrazeniTermini) operation).getTrazeniTermini();
    }

    public void obrisiTermin(Termin terminZaBrisanje) throws Exception { //kad brises nalog, oslobodi sve termine gde je nalog bio za to mesto
        AbstractGenericOperation operation = new ObrisiTermin();
        operation.execute(terminZaBrisanje);

    }

    public void izmeniTermin(Termin updateTermin) throws Exception {
        AbstractGenericOperation operation = new IzmeniTermin();
        operation.execute(updateTermin);
    }

    public void zapamtiPrisustvoTerminu(PrisustvoTerminu pt) throws Exception {
        AbstractGenericOperation operation = new ZapamtiPrisustvoTerminu();
        operation.execute(pt);

    }

    public NalogClana ucitajNalogClana(NalogClana selektovaniNalog) throws Exception {
        AbstractGenericOperation operation = new UcitajNalogClana();
        operation.execute(selektovaniNalog);
        return ((UcitajNalogClana) operation).getSelektovaniNalog();
    }

    public Termin ucitajTermin(Termin selektovaniTermin) throws Exception {
        AbstractGenericOperation operation = new UcitajTermin();
        operation.execute(selektovaniTermin);
        return ((UcitajTermin) operation).getIzabraniTermin();
    }

    public List<PrisustvoTerminu> getSvaPrisustva() throws Exception {
        AbstractGenericOperation operation = new GetSvaPrisustva();
        operation.execute(new Object());
        return ((GetSvaPrisustva) operation).getSvaPrisustva();
    }

}
