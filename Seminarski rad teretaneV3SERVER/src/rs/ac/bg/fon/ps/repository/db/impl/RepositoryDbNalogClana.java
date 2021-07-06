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
import rs.ac.bg.fon.ps.domain.NalogClana;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.IDbRepository;

/**
 *
 * @author Mr OLOGIZ
 */
public class RepositoryDbNalogClana implements IDbRepository<NalogClana, Long> {

    @Override
    public void insert(NalogClana t) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();

            String query2 = "ALTER TABLE nalogclana AUTO_INCREMENT =1";
            Statement statement2 = connection.createStatement();

            statement2.executeUpdate(query2);
            statement2.close();

            String query = "INSERT INTO nalogclana(imeClana,prezimeClana,brojTelefonaClana,UlicaBroj,platio,polClana,JBMGClana) VALUES (?,?,?,?,?,?,?)";
            //System.out.println("Upit za insertovanje naloga clana: " + query);
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, t.getImeClana());
            statement.setString(2, t.getPrezimeClana());
            statement.setLong(3, t.getBrojTelefonaClana());
            statement.setString(4, t.getUlicaBroj());
            statement.setBoolean(5, t.isPlatio());
            statement.setString(6, t.getPolClana());
            statement.setLong(7, t.getJBMGClana());

            statement.executeUpdate();
            statement.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Greska u insertovanju naloga clana u bazu!\n" + ex.getMessage());
        }
    }

    @Override
    public void update(NalogClana t) throws Exception {
          try {
            String query = "UPDATE nalogclana SET imeClana='" + t.getImeClana()
                    + "',prezimeClana='" + t.getPrezimeClana()+ "',brojTelefonaClana="+t.getBrojTelefonaClana()+
                    ",UlicaBroj='"+t.getUlicaBroj()+"',platio="+t.isPlatio()+",polClana='"+t.getPolClana()+"',JBMGClana="+t.getJBMGClana()+
                    " WHERE idClana=" + t.getIdClana();
            //System.out.println("Upit za izmenu naloga clana: "+query);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska u izmeni naloga u bazi!\n");
        }
    }

    @Override
    public void delete(NalogClana t) throws Exception {
        try {
            String query = "DELETE FROM nalogclana"
                    + " WHERE idClana=" + t.getIdClana().toString();
            //System.out.println("Upit za brisanje naloga iz baze: "+ query);

            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska u brisanju naloga iz baze!\n" + e.getMessage());
        }
    }

    @Override
    public List<NalogClana> getAll() throws Exception {
        try {
            /*String query = "SELECT idClana,imeClana,prezimeClana,brojTelefonaClana,UlicaBroj,platio,polClana,JBMGClana"
                    + " FROM nalogclana";*/
            
            String query = "SELECT *"
                    + " FROM nalogclana";
            
            //System.out.println("Upit za importovanje svih naloga clanova: " + query);
            List<NalogClana> nalozi = new ArrayList<>();

            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                NalogClana nalog = new NalogClana(rs.getLong("idClana"), rs.getString("imeClana"), rs.getString("prezimeClana"),
                        rs.getLong("brojTelefonaClana"), rs.getString("UlicaBroj"), rs.getBoolean("platio"),
                        rs.getString("polClana"), rs.getLong("JBMGClana"));

                nalozi.add(nalog);
            }
            return nalozi;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska u importovanju svih naloga iz baze!\n" + e.getMessage());
        }
    }

    @Override
    public NalogClana get(Long k) throws Exception {
        try {
            String query = "SELECT idClana,imeClana,prezimeClana,brojTelefonaClana,UlicaBroj,platio,polClana,JBMGClana"
                    + " FROM nalogclana WHERE idClana="+k;
            //System.out.println("Upit za importovanje naloga clana: " + query);
            NalogClana nalogClana = null;

            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            if(rs.next()){
            nalogClana=new NalogClana(rs.getLong("idClana"), rs.getString("imeClana"), rs.getString("prezimeClana"),
                    rs.getLong("brojTelefonaClana"), rs.getString("UlicaBroj"), rs.getBoolean("platio"), rs.getString("polClana"), rs.getLong("JBMGClana"));
                       
            }
            return nalogClana;
            

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska u importovanju naloga iz baze!\n" + e.getMessage());
        }
    }

    @Override
    public List<NalogClana> getAll(NalogClana param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NalogClana get(Long k, NalogClana t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
