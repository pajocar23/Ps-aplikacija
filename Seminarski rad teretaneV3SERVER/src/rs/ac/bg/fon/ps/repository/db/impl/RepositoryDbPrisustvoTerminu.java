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
import rs.ac.bg.fon.ps.domain.NalogClana;
import rs.ac.bg.fon.ps.domain.PrisustvoTerminu;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.IDbRepository;

/**
 *
 * @author Mr OLOGIZ
 */
public class RepositoryDbPrisustvoTerminu implements IDbRepository<PrisustvoTerminu, Long> {

    @Override
    public void insert(PrisustvoTerminu t) throws Exception {
         try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();

            String query2 = "ALTER TABLE prisustvoterminu AUTO_INCREMENT =1";
            Statement statement2 = connection.createStatement();

            statement2.executeUpdate(query2);
            statement2.close();

            String query = "INSERT INTO prisustvoterminu(idClana,idTermina) VALUES (?,?)";
            //System.out.println("Upit za insertovanje prisustva terminu: " + query);
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, t.getNalogClana().getIdClana());
            statement.setLong(2, t.getTermin().getIDTermina());


            statement.executeUpdate();
            statement.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Greska u insertovanju  prisustva terminu u bazu!\n" + ex.getMessage());
        }
    }

    @Override
    public void update(PrisustvoTerminu t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PrisustvoTerminu t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PrisustvoTerminu> getAll() throws Exception {
        try {
            /*String query = "SELECT prisustvoterminu.id,prisustvoterminu.idClana,prisustvoterminu.idTermina,"
                    + "nalogclana.idClana,nalogclana.imeClana,nalogclana.prezimeClana,"
                    + "termin.idTermina,termin.datum,termin.vremePocetka,termin.vremeKraja,termin.idGrupe,"
                    + "grupa.idGrupe,grupa.nazivGrupe"
                    + " FROM prisustvoterminu "+
                    "LEFT JOIN nalogclana ON prisustvoterminu.idClana=nalogclana.idClana"
                    + " LEFT JOIN termin ON prisustvoterminu.idTermina=termin.idTermina"
                    + " LEFT JOIN grupa ON termin.idGrupe=grupa.idGrupe";*/
            
            String query = "SELECT *"
                    + " FROM prisustvoterminu "+
                    "LEFT JOIN nalogclana ON prisustvoterminu.idClana=nalogclana.idClana"
                    + " LEFT JOIN termin ON prisustvoterminu.idTermina=termin.idTermina"
                    + " LEFT JOIN grupa ON termin.idGrupe=grupa.idGrupe";
            
            //System.out.println("Upit za importovanje svih prisustva terminu: " + query);
            
            List<PrisustvoTerminu> pt = new ArrayList<>();

            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);           
            while (rs.next()) {
                NalogClana nc=new NalogClana();
                nc.setIdClana(rs.getLong("prisustvoterminu.idClana"));
                nc.setImeClana(rs.getString("nalogclana.imeClana"));
                nc.setPrezimeClana(rs.getString("nalogclana.prezimeClana"));
                
                Grupa g=new Grupa();
                g.setIdGrupe(rs.getLong("grupa.idGrupe"));
                g.setNazivGrupe(rs.getString("grupa.nazivGrupe"));
                
                Termin t=new Termin();
                
                t.setIDTermina(rs.getLong("prisustvoterminu.idTermina"));
                t.setDatum(rs.getDate("termin.datum"));
                t.setVremePocetka(rs.getTime("termin.vremePocetka"));
                t.setVremeKraja(rs.getTime("termin.vremeKraja"));
                t.setG(g);
         
                PrisustvoTerminu ps=new PrisustvoTerminu(rs.getLong("prisustvoterminu.id"), nc, t);

                pt.add(ps);
            }
            return pt;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska u importovanju prisustva terminu iz baze!\n" + e.getMessage());
        }
    }

    @Override
    public PrisustvoTerminu get(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PrisustvoTerminu> getAll(PrisustvoTerminu param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PrisustvoTerminu get(Long k, PrisustvoTerminu t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
