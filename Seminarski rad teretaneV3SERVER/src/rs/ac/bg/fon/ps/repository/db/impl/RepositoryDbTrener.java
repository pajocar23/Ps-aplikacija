/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.IDbRepository;

/**
 *
 * @author Mr OLOGIZ
 */
public class RepositoryDbTrener implements IDbRepository<Trener, Long>{

    @Override
    public void insert(Trener t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Trener t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(Trener t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Trener> getAll() throws Exception {
        try{
            /*String query="SELECT idTrenera,imeTrenera,prezimeTrenera,brojTelefonaTrenera,polTrenera,JBMGTrenera,korisnickoIme,sifra"
                    + " FROM trener";*/
            
            String query="SELECT *"
                    + " FROM trener";
            
            //System.out.println("Upit za importovanje svih trenera: "+query);
            List<Trener> treneri=new ArrayList<>();
            Connection connection=DbConnectionFactory.getInstance().getConnection();
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            
            while(rs.next()){
                
               Trener trener=new Trener(rs.getLong("idTrenera"), rs.getString("imeTrenera"), rs.getString("prezimeTrenera"),rs.getLong("brojTelefonaTrenera"),
                                        rs.getString("polTrenera"),rs.getLong("JBMGTrenera"), rs.getString("korisnickoIme"), rs.getString("sifra"));
               
               treneri.add(trener);
            }
            return treneri;
            
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Greska u importovanju svih trenera iz baze!\n"+e.getMessage());
        }
    }

    @Override
    public Trener get(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Trener> getAll(Trener param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Trener get(Long k, Trener t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
