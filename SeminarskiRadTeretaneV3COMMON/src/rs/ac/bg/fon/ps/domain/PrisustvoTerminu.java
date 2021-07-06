/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Objects;

/**
 *
 * @author Mr OLOGIZ
 */
public class PrisustvoTerminu implements GenericEntity {

    private Long id;
    private NalogClana nalogClana;
    private Termin termin;

    public PrisustvoTerminu() {
    }

    public PrisustvoTerminu(Long id, NalogClana nalogClana, Termin termin) {
        this.id = id;
        this.nalogClana = nalogClana;
        this.termin = termin;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public NalogClana getNalogClana() {
        return nalogClana;
    }

    public void setNalogClana(NalogClana nalogClana) {
        this.nalogClana = nalogClana;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "idPrisustva:" + id + "  idNaloga:" + nalogClana.getIdClana();
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final PrisustvoTerminu other = (PrisustvoTerminu) obj;
        if (!Objects.equals(this.nalogClana, other.nalogClana)) {
            return false;
        }
        if (!Objects.equals(this.termin, other.termin)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "prisustvoterminu";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "idClana,idTermina";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(nalogClana.getIdClana()).append(",")
                .append(termin.getIDTermina());

        return sb.toString();
    }

    @Override
    public String getJoinConditions() {
        return "LEFT JOIN nalogclana ON prisustvoterminu.idClana=nalogclana.idClana"
                    + " LEFT JOIN termin ON prisustvoterminu.idTermina=termin.idTermina"
                    + " LEFT JOIN grupa ON termin.idGrupe=grupa.idGrupe";
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
        PrisustvoTerminu pt = null;
        try {
            NalogClana nc = new NalogClana();
            nc.setIdClana(rs.getLong("prisustvoterminu.idClana"));
            nc.setImeClana(rs.getString("nalogclana.imeClana"));
            nc.setPrezimeClana(rs.getString("nalogclana.prezimeClana"));

            Grupa g = new Grupa();
            g.setIdGrupe(rs.getLong("grupa.idGrupe"));
            g.setNazivGrupe(rs.getString("grupa.nazivGrupe"));

            Termin t = new Termin();

            t.setIDTermina(rs.getLong("prisustvoterminu.idTermina"));
            t.setDatum(rs.getDate("termin.datum"));
            t.setVremePocetka(rs.getTime("termin.vremePocetka"));
            t.setVremeKraja(rs.getTime("termin.vremeKraja"));
            t.setG(g);

            pt = new PrisustvoTerminu(rs.getLong("prisustvoterminu.id"), nc, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pt;
    }

}
