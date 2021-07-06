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
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.IDbRepository;

/**
 *
 * @author Mr OLOGIZ
 */
public class RepositoryDbGrupa implements IDbRepository<Grupa, Long> {

    @Override
    public void insert(Grupa t) throws Exception {
        try {

            Connection connection = DbConnectionFactory.getInstance().getConnection();

            String query2 = "ALTER TABLE grupa AUTO_INCREMENT =1";
            Statement statement2 = connection.createStatement();

            statement2.executeUpdate(query2);
            statement2.close();

            String query = "INSERT INTO grupa(brojTermina,nazivGrupe,idTrenera) VALUES (?,?,?)";
            //System.out.println("Upit za insertovanje grupe: " + query);
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, t.getBrojTermina());
            statement.setString(2, t.getNazivGrupe());
            statement.setLong(3, t.getTrener().getIDTrenera());

            statement.executeUpdate();
            statement.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Greska u insertovanju grupe u bazu!\n" + ex.getMessage());
        }
    }

    @Override
    public void update(Grupa t) throws Exception {
        try {
            String query = "UPDATE grupa SET brojTermina=" + t.getBrojTermina()
                    + ",nazivGrupe='" + t.getNazivGrupe() + "',idTrenera=" + t.getTrener().getIDTrenera()
                    + " WHERE idGrupe=" + t.getIdGrupe();

            //System.out.println("Upit za izmenu grupe: "+query);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska u izmeni grupe u bazi!\n");
        }

    }

    @Override
    public void delete(Grupa t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Grupa> getAll() throws Exception {
        try {
            /*String query = "SELECT grupa.idGrupe,grupa.brojTermina,grupa.nazivGrupe,trener.idTrenera,trener.imeTrenera,trener.prezimeTrenera,trener.brojTelefonaTrenera"+
                    ",trener.polTrenera,trener.JBMGTrenera,trener.korisnickoIme,trener.sifra"
                    + " FROM grupa"+
                    " LEFT JOIN "+"trener ON grupa.idTrenera=trener.idTrenera";*/

            String query = "SELECT *"
                    + " FROM grupa"
                    + " LEFT JOIN " + "trener ON grupa.idTrenera=trener.idTrenera";

            //System.out.println("Upit za importovanje svih grupa: " + query);
            List<Grupa> grupe = new ArrayList<>();

            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Trener trener = new Trener();
                trener.setIDTrenera(rs.getLong("trener.idTrenera"));
                trener.setImeTrenera(rs.getString("trener.imeTrenera"));
                trener.setPrezimeTrenera(rs.getString("trener.prezimeTrenera"));
                trener.setBrojTelefonaTrenera(rs.getLong("trener.brojTelefonaTrenera"));
                trener.setPolTrenera(rs.getString("trener.polTrenera"));
                trener.setJBMGTrenera(rs.getLong("trener.JBMGTrenera"));
                trener.setKorisnickoIme(rs.getString("trener.korisnickoIme"));
                trener.setSifra(rs.getString("trener.sifra"));

                Grupa g = new Grupa(rs.getLong("grupa.idGrupe"), rs.getInt("grupa.brojTermina"), rs.getString("grupa.nazivGrupe"),
                        trener);

                grupe.add(g);
            }
            return grupe;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska u importovanju svih naloga iz baze!\n" + e.getMessage());
        }
    }

    @Override
    public Grupa get(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Grupa> getAll(Grupa param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Grupa get(Long k, Grupa t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
