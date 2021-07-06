/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Grupa;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.IDbRepository;

/**
 *
 * @author Mr OLOGIZ
 */
public class RepositoryDbTermin implements IDbRepository<Termin, Long> {

    @Override
    public void insert(Termin t) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();

            String query2 = "ALTER TABLE termin AUTO_INCREMENT =1";
            Statement statement2 = connection.createStatement();

            statement2.executeUpdate(query2);
            statement2.close();

            String query = "INSERT INTO termin(datum,VremePocetka,vremeKraja,trajanje,brojClanova,maksBrojClanova,idGrupe) VALUES (?,?,?,?,?,?,?)";
            //System.out.println("Upit za insertovanje termina: " + query);
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setDate(1, new java.sql.Date(t.getDatum().getTime()));
            statement.setTimestamp(2, new java.sql.Timestamp(t.getVremePocetka().getTime()));
            statement.setTimestamp(3, new java.sql.Timestamp(t.getVremeKraja().getTime()));
            statement.setTimestamp(4, new java.sql.Timestamp(t.getTrajanje().getTime()));
            statement.setInt(5, t.getBrojClanova());
            statement.setInt(6, t.getMaksBrojClanova());
            statement.setLong(7, t.getG().getIdGrupe());

            statement.executeUpdate();
            statement.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Greska u insertovanju termina u bazu!\n" + ex.getMessage());
        }
    }

    @Override
    public void update(Termin t) throws Exception {
        try {
            String query = "UPDATE termin SET datum='" + new java.sql.Date(t.getDatum().getTime())
                    + "',vremePocetka='" + new java.sql.Timestamp(t.getVremePocetka().getTime()) + "',vremeKraja='" + new java.sql.Timestamp(t.getVremeKraja().getTime())
                    + "',trajanje='" + new java.sql.Timestamp(t.getTrajanje().getTime()) + "',brojClanova=" + t.getBrojClanova() + ",maksBrojClanova=" + t.getMaksBrojClanova()
                    +",idGrupe="+t.getG().getIdGrupe()
                    + " WHERE idTermina=" + t.getIDTermina();

            //System.out.println("Upit za izmenu termina: " + query);

            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska u izmeni termina u bazi!\n");
        }
    }
    
    @Override
    public void delete(Termin t) throws Exception {
        try {
            String query = "DELETE FROM termin"
                    + " WHERE idTermina=" + t.getIDTermina().toString();

            //System.out.println("Upit za brisanje termina iz baze: " + query);

            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska u brisanju termina iz baze!\n" + e.getMessage());
        }
    }

    @Override
    public List<Termin> getAll() throws Exception {
        try {
            /*String query = "SELECT termin.idTermina,termin.datum,termin.vremePocetka,termin.vremeKraja,termin.trajanje,termin.brojClanova,termin.maksBrojClanova,"
                    + "grupa.nazivGrupe,grupa.idGrupe,grupa.brojTermina,trener.idTrenera,trener.imeTrenera,trener.prezimeTrenera"
                    + " FROM termin" + " LEFT JOIN grupa ON termin.idGrupe=grupa.idGrupe LEFT JOIN trener ON grupa.idTrenera=trener.idTrenera";*/
            
            String query = "SELECT *"
                    + " FROM termin" + " LEFT JOIN grupa ON termin.idGrupe=grupa.idGrupe LEFT JOIN trener ON grupa.idTrenera=trener.idTrenera";

            //System.out.println("Upit za importovanje svih termina: " + query);
            List<Termin> termini = new ArrayList<>();
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                Trener t = new Trener();
                t.setIDTrenera(rs.getLong("trener.idTrenera"));
                t.setImeTrenera(rs.getString("trener.imeTrenera"));
                t.setPrezimeTrenera(rs.getString("trener.prezimeTrenera"));

                Grupa g = new Grupa();
                g.setIdGrupe(rs.getLong("grupa.idGrupe"));
                g.setTrener(t);
                g.setNazivGrupe(rs.getString("grupa.nazivGrupe"));
                g.setBrojTermina(rs.getInt("grupa.brojTermina"));

                Termin termin = new Termin(rs.getLong("termin.idTermina"), rs.getDate("termin.datum"),
                        rs.getTime("termin.vremePocetka"), rs.getTime("termin.vremeKraja"),
                        rs.getTime("termin.trajanje"), rs.getInt("termin.brojClanova"), rs.getInt("termin.maksBrojClanova"),
                        g);
                
                termini.add(termin);
            }
            return termini;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska u importovanju svih termina iz baze!\n" + e.getMessage());
        }
    }

    @Override
    public Termin get(Long k) throws Exception {
        try {
            String query = "SELECT termin.idTermina,termin.datum,termin.vremePocetka,termin.vremeKraja,termin.trajanje,termin.brojClanova,termin.maksBrojClanova,"
                    + "grupa.nazivGrupe,grupa.idGrupe,grupa.brojTermina,trener.idTrenera,trener.imeTrenera,trener.prezimeTrenera"
                    + " FROM termin" + " LEFT JOIN grupa ON termin.idGrupe=grupa.idGrupe LEFT JOIN trener ON grupa.idTrenera=trener.idTrenera "+
                    "WHERE termin.idTermina="+k;

            //System.out.println("Upit za importovanje termina: " + query);
            Termin termin = null;
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                Trener t = new Trener();
                t.setIDTrenera(rs.getLong("trener.idTrenera"));
                t.setImeTrenera(rs.getString("trener.imeTrenera"));
                t.setPrezimeTrenera(rs.getString("trener.prezimeTrenera"));

                Grupa g = new Grupa();
                g.setIdGrupe(rs.getLong("grupa.idGrupe"));
                g.setTrener(t);
                g.setNazivGrupe(rs.getString("grupa.nazivGrupe"));
                g.setBrojTermina(rs.getInt("grupa.brojTermina"));

                termin = new Termin(rs.getLong("termin.idTermina"), rs.getDate("termin.datum"),
                        rs.getTime("termin.vremePocetka"), rs.getTime("termin.vremeKraja"),
                        rs.getTime("termin.trajanje"), rs.getInt("termin.brojClanova"), rs.getInt("termin.maksBrojClanova"),
                        g);
                

            }
            return termin;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska u importovanju svih termina iz baze!\n" + e.getMessage());
        }
    }

    @Override
    public List<Termin> getAll(Termin param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Termin get(Long k, Termin t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
