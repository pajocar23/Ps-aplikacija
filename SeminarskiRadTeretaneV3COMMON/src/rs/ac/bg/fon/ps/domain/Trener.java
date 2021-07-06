/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mr OLOGIZ
 */
public class Trener implements GenericEntity {

    private Long IDTrenera;
    private String imeTrenera;
    private String prezimeTrenera;
    private Long brojTelefonaTrenera;
    private String polTrenera;
    private Long JBMGTrenera;

    private String korisnickoIme;
    private String sifra;

    public Trener() {
    }

    public Trener(Long IDTrenera, String imeTrenera, String prezimeTrenera, Long brojTelefonaTrenera, String polTrenera, Long JBMGTrenera, String korisnickoIme, String sifra) {
        this.IDTrenera = IDTrenera;
        this.imeTrenera = imeTrenera;
        this.prezimeTrenera = prezimeTrenera;
        this.brojTelefonaTrenera = brojTelefonaTrenera;
        this.polTrenera = polTrenera;
        this.JBMGTrenera = JBMGTrenera;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public Long getJBMGTrenera() {
        return JBMGTrenera;
    }

    public void setJBMGTrenera(Long JBMGTrenera) {
        this.JBMGTrenera = JBMGTrenera;
    }

    public Long getIDTrenera() {
        return IDTrenera;
    }

    public void setIDTrenera(Long IDTrenera) {
        this.IDTrenera = IDTrenera;
    }

    public String getImeTrenera() {
        return imeTrenera;
    }

    public void setImeTrenera(String imeTrenera) {
        this.imeTrenera = imeTrenera;
    }

    public String getPrezimeTrenera() {
        return prezimeTrenera;
    }

    public void setPrezimeTrenera(String prezimeTrenera) {
        this.prezimeTrenera = prezimeTrenera;
    }

    public Long getBrojTelefonaTrenera() {
        return brojTelefonaTrenera;
    }

    public void setBrojTelefonaTrenera(Long brojTelefonaTrenera) {
        this.brojTelefonaTrenera = brojTelefonaTrenera;
    }

    public String getPolTrenera() {
        return polTrenera;
    }

    public void setPolTrenera(String polTrenera) {
        this.polTrenera = polTrenera;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return imeTrenera + " " + prezimeTrenera;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trener other = (Trener) obj;
        if (!Objects.equals(this.imeTrenera, other.imeTrenera)) {
            return false;
        }
        if (!Objects.equals(this.prezimeTrenera, other.prezimeTrenera)) {
            return false;
        }
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        if (!Objects.equals(this.sifra, other.sifra)) {
            return false;
        }
        if (!Objects.equals(this.IDTrenera, other.IDTrenera)) {
            return false;
        }
        if (!Objects.equals(this.brojTelefonaTrenera, other.brojTelefonaTrenera)) {
            return false;
        }
        if (this.polTrenera != other.polTrenera) {
            return false;
        }
        if (!Objects.equals(this.JBMGTrenera, other.JBMGTrenera)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "trener";
    }

    @Override
    public String getColumnNamesForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getInsertValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getJoinConditions() {
        return "";
    }

    @Override
    public String getPrimaryKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPrimaryKeyValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdateValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenericEntity setGetAllValues(ResultSet rs) {
        Trener trener = null;
        try {
            trener = new Trener(rs.getLong("idTrenera"), rs.getString("imeTrenera"), rs.getString("prezimeTrenera"), rs.getLong("brojTelefonaTrenera"),
                    rs.getString("polTrenera"), rs.getLong("JBMGTrenera"), rs.getString("korisnickoIme"), rs.getString("sifra"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return trener;
    }

}
