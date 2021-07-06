/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.communication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import rs.ac.bg.fon.ps.domain.Grupa;
import rs.ac.bg.fon.ps.domain.NalogClana;
import rs.ac.bg.fon.ps.domain.PrisustvoTerminu;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;

/**
 *
 * @author Mr OLOGIZ
 */
public class Communication{

    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private static Communication instance;

    private Communication() throws Exception {
        /*Properties properties = new Properties();
        String serverConfigFilePath = new File("src\\rs\\ac\\bg\\fon\\ps\\config\\ServerConfig.properties").getAbsolutePath();
        properties.load(new FileInputStream(serverConfigFilePath));
        String port = properties.getProperty("port");
        String host=properties.getProperty("host");*/
        socket = new Socket("localhost", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public static Communication getInstance() throws Exception {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public boolean izbaciKlijenta() throws Exception {
        Response response = (Response) receiver.receive();
       
        if (response.getResult().equals("Izlogovan/ana od strane servera")) {
            return true;
        } else {
            return false;
        }
    }

    public void logOut(Trener t) throws Exception {
        Request request = new Request();
        request.setOperation(Operation.LOGUT);
        request.setArgument(t);
        sender.send(request);
        socket.close();
        System.out.println(MainCordinator.getInstance().getParamTrener(Constants.LOGGED_TRENER) + " IZLOGOVAN");

    }

    public Trener logIn(String username, String password) throws Exception {
        Trener t = new Trener();
        t.setKorisnickoIme(username);
        t.setSifra(password);
        Request request = new Request();
        request.setOperation(Operation.LOGIN);
        request.setArgument(t);
        sender.send(request);

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (Trener) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public NalogClana zapamtiNalogClana(NalogClana nc) throws Exception {
        Request request = new Request();
        request.setOperation(Operation.ZAPAMTI_NALOG_CLANA);
        request.setArgument(nc);
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (NalogClana) response.getResult();
        } else {
            throw response.getException();
        }

    }

    public List<NalogClana> getSviNalozi() throws Exception {
        List<NalogClana> sviNalozi = new ArrayList<>();
        Request request = new Request();
        request.setOperation(Operation.UCITAJ_LISTU_NALOGA_CLANOVA);
        
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (List<NalogClana>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<NalogClana> getTrazeniNalozi(String kriterijum, String vrednost) throws Exception {
        Request request = new Request();
        request.setOperation(Operation.NADJI_NALOGE_CLANOVA);
        request.setKriterijum(kriterijum);
        request.setVrednost(vrednost);
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (List<NalogClana>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public NalogClana ucitajNalogClana(NalogClana selektovaniNalog) throws Exception {
        Request request = new Request();
        request.setOperation(Operation.UCITAJ_NALOG_CLANA);
        request.setArgument(selektovaniNalog);
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (NalogClana) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void izmeniNalogClana(NalogClana nc) throws Exception {
        Request request = new Request();
        request.setOperation(Operation.IZMENI_NALOG_CLANA);
        request.setArgument(nc);
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void obrisiNalogClana(NalogClana ncZaBrisanje) throws Exception {
        Request request = new Request();
        request.setOperation(Operation.OBRISI_NALOG_CLANA);
        request.setArgument(ncZaBrisanje);
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void zapamtiPrisustvoTerminu(PrisustvoTerminu pt) throws Exception {
        Request request = new Request();
        request.setOperation(Operation.ZAPAMTI_PRISUSTVO_TERMINU);
        request.setArgument(pt);
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public List<Termin> getSviTermini() throws Exception {
        Request request = new Request();
        request.setOperation(Operation.GET_LISTA_TERMINA);
        
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (List<Termin>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<PrisustvoTerminu> getSvaPrisustva() throws Exception {
        Request request = new Request();
        request.setOperation(Operation.UCITAJ_LISTU_PRISUSTVA);
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (List<PrisustvoTerminu>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Grupa> getGrupeUlogovanogTrenera(Trener t) throws Exception {
        Request request = new Request();
        request.setOperation(Operation.UCITAJ_LISTU_GRUPA_ULOGOVANOG_TRENERA);
        request.setArgument(t);
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (List<Grupa>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public Grupa zapamtiGrupu(Grupa g) throws Exception {
        Request request = new Request();
        request.setOperation(Operation.ZAPAMTI_GRUPU);
        request.setArgument(g);
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (Grupa) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void zapamtiTermin(Termin t) throws Exception {
        Request request = new Request();
        request.setOperation(Operation.ZAPAMTI_TERMIN);
        request.setArgument(t);
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public Termin ucitajTermin(Termin selektovaniTermin) throws Exception {
        Request request = new Request();
        request.setOperation(Operation.UCITAJ_TERMIN);
        request.setArgument(selektovaniTermin);
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (Termin) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Termin> getTrazeniTermini(String kriterijum, String vrednost) throws Exception {
        Request request = new Request();
        request.setOperation(Operation.GET_TRAZENI_TERMINI);
        request.setKriterijum(kriterijum);
        request.setVrednost(vrednost);
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (List<Termin>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void izmeniTermin(Termin updateTermin) throws Exception {
        Request request = new Request();
        request.setOperation(Operation.IZMENI_TERMIN);
        request.setArgument(updateTermin);
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void obrisiTermin(Termin terminZaBrisanje) throws Exception { //kad brises nalog, oslobodi sve termine gde je nalog bio za to mesto
        Request request = new Request();
        request.setOperation(Operation.OBRISI_TERMIN);
        request.setArgument(terminZaBrisanje);
        try{
        sender.send(request);
        }catch(SocketException se){
            throw se;
        }

        Response response = (Response) receiver.receive();

        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
