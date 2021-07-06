/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Mr OLOGIZ
 */
public class Termin implements GenericEntity {

    private Long IDTermina;

    private Date datum;
    private Date vremePocetka;
    private Date vremeKraja;
    private Date trajanje;

    private int brojClanova;
    private int maksBrojClanova;

    private Grupa g;

    public Termin() {
    }

    public Termin(Long IDTermina, Date datum, Date vremePocetka, Date vremeKraja, Date trajanje, int brojClanova, int maksBrojClanova, Grupa g) {
        this.IDTermina = IDTermina;
        this.datum = datum;
        this.vremePocetka = vremePocetka;
        this.vremeKraja = vremeKraja;
        this.trajanje = trajanje;
        this.brojClanova = brojClanova;
        this.maksBrojClanova = maksBrojClanova;
        this.g = g;
    }

    public int getMaksBrojClanova() {
        return maksBrojClanova;
    }

    public void setMaksBrojClanova(int maksBrojClanova) {
        this.maksBrojClanova = maksBrojClanova;
    }

    public Long getIDTermina() {
        return IDTermina;
    }

    public void setIDTermina(Long IDTermina) {
        this.IDTermina = IDTermina;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(Date vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public Date getVremeKraja() {
        return vremeKraja;
    }

    public void setVremeKraja(Date vremeKraja) {
        this.vremeKraja = vremeKraja;
    }

    public Date getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Date trajanje) {
        this.trajanje = trajanje;
    }

    public int getBrojClanova() {
        return brojClanova;
    }

    public void setBrojClanova(int brojClanova) {
        this.brojClanova = brojClanova;
    }

    public Grupa getG() {
        return g;
    }

    public void setG(Grupa g) {
        this.g = g;
    }

    @Override
    public String toString() {
        return "Termin{" + "IDTermina=" + IDTermina + ", datum=" + datum + ", vremePocetka=" + vremePocetka + ", vremeKraja=" + vremeKraja + ", trajanje=" + trajanje + ", brojClanova=" + brojClanova + ", maksBrojClanova=" + maksBrojClanova + '}';
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
        final Termin other = (Termin) obj;
        if (this.brojClanova != other.brojClanova) {
            return false;
        }
        if (this.maksBrojClanova != other.maksBrojClanova) {
            return false;
        }
        if (!Objects.equals(this.IDTermina, other.IDTermina)) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        if (!Objects.equals(this.vremePocetka, other.vremePocetka)) {
            return false;
        }
        if (!Objects.equals(this.vremeKraja, other.vremeKraja)) {
            return false;
        }
        if (!Objects.equals(this.trajanje, other.trajanje)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "termin";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "datum,VremePocetka,vremeKraja,trajanje,brojClanova,maksBrojClanova,idGrupe";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(new java.sql.Date(datum.getTime())).append("',")
                .append("'").append(new java.sql.Timestamp(vremePocetka.getTime())).append("','")
                .append(new java.sql.Timestamp(vremeKraja.getTime())).append("','")
                .append(new java.sql.Timestamp(trajanje.getTime())).append("',")
                .append(brojClanova).append(",")
                .append(maksBrojClanova).append(",")
                .append(g.getIdGrupe());

        return sb.toString();
    }

    @Override
    public String getJoinConditions() {
        return "LEFT JOIN grupa ON termin.idGrupe=grupa.idGrupe LEFT JOIN trener ON grupa.idTrenera=trener.idTrenera";
    }

    @Override
    public String getPrimaryKey() {
        return "idTermina";
    }

    @Override
    public String getPrimaryKeyValue() {
        return IDTermina.toString();
    }

    @Override
    public String getUpdateValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("datum='").append(new java.sql.Date(datum.getTime()))
                .append("',vremePocetka='").append(new java.sql.Timestamp(vremePocetka.getTime()))
                .append("',vremeKraja='").append(new java.sql.Timestamp(vremeKraja.getTime()))
                .append("',trajanje='").append(new java.sql.Timestamp(trajanje.getTime()))
                .append("',brojClanova=").append(brojClanova)
                .append(",maksBrojClanova=").append(maksBrojClanova)
                .append(",idGrupe=").append(g.getIdGrupe());

        return sb.toString();

    }

    @Override
    public GenericEntity setGetAllValues(ResultSet rs) {
        Termin termin = null;
        try {

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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return termin;
    }

}
