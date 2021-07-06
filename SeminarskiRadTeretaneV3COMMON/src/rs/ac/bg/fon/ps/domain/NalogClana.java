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
public class NalogClana implements GenericEntity {

    private Long IdClana;
    private String imeClana;
    private String prezimeClana;
    private Long brojTelefonaClana;
    private String UlicaBroj;
    private boolean platio;
    private String polClana;
    private Long JBMGClana;

    public NalogClana() {
    }

    public NalogClana(Long IdClana, String imeClana, String prezimeClana, Long brojTelefonaClana, String UlicaBroj, boolean platio, String polClana, Long JBMGClana) {
        this.IdClana = IdClana;
        this.imeClana = imeClana;
        this.prezimeClana = prezimeClana;
        this.brojTelefonaClana = brojTelefonaClana;
        this.UlicaBroj = UlicaBroj;
        this.platio = platio;
        this.polClana = polClana;
        this.JBMGClana = JBMGClana;
    }

    public Long getIdClana() {
        return IdClana;
    }

    public void setIdClana(Long IdClana) {
        this.IdClana = IdClana;
    }

    public String getImeClana() {
        return imeClana;
    }

    public void setImeClana(String imeClana) {
        this.imeClana = imeClana;
    }

    public String getPrezimeClana() {
        return prezimeClana;
    }

    public void setPrezimeClana(String prezimeClana) {
        this.prezimeClana = prezimeClana;
    }

    public Long getBrojTelefonaClana() {
        return brojTelefonaClana;
    }

    public void setBrojTelefonaClana(Long brojTelefonaClana) {
        this.brojTelefonaClana = brojTelefonaClana;
    }

    public String getUlicaBroj() {
        return UlicaBroj;
    }

    public void setUlicaBroj(String UlicaBroj) {
        this.UlicaBroj = UlicaBroj;
    }

    public boolean isPlatio() {
        return platio;
    }

    public void setPlatio(boolean platio) {
        this.platio = platio;
    }

    public String getPolClana() {
        return polClana;
    }

    public void setPolClana(String polClana) {
        this.polClana = polClana;
    }

    public Long getJBMGClana() {
        return JBMGClana;
    }

    public void setJBMGClana(Long JBMGClana) {
        this.JBMGClana = JBMGClana;
    }

    @Override
    public String toString() {
        return "Ime clana: " + imeClana + "  Prezime clana: " + prezimeClana;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    public String toString2() {
        return "IdClana:" + IdClana + "\nimeClana:" + imeClana + "\nprezimeClana:" + prezimeClana + "\nbrojTelefonaClana:" + brojTelefonaClana + "\nUlicaBroj:" + UlicaBroj + "\nplatio:" + platio + "\npolClana:" + polClana + "\nJBMGClana:" + JBMGClana;
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
        final NalogClana other = (NalogClana) obj;
        if (this.platio != other.platio) {
            return false;
        }
        if (!Objects.equals(this.imeClana, other.imeClana)) {
            return false;
        }
        if (!Objects.equals(this.prezimeClana, other.prezimeClana)) {
            return false;
        }
        if (!Objects.equals(this.UlicaBroj, other.UlicaBroj)) {
            return false;
        }
        if (!Objects.equals(this.polClana, other.polClana)) {
            return false;
        }
        if (!Objects.equals(this.IdClana, other.IdClana)) {
            return false;
        }
        if (!Objects.equals(this.brojTelefonaClana, other.brojTelefonaClana)) {
            return false;
        }
        if (!Objects.equals(this.JBMGClana, other.JBMGClana)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "nalogclana";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "imeClana,prezimeClana,brojTelefonaClana,UlicaBroj,platio,polClana,JBMGClana";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(imeClana).append("',")
                .append("'").append(prezimeClana).append("',")
                .append(brojTelefonaClana).append(",'")
                .append(UlicaBroj).append("',")
                .append(platio).append(",'")
                .append(polClana).append("',")
                .append(JBMGClana);

        return sb.toString();
    }

    @Override
    public String getJoinConditions() {
        return "";
    }

    @Override
    public String getPrimaryKey() {
        return "idClana";
    }

    @Override
    public String getPrimaryKeyValue() {
        return IdClana.toString();
    }

    @Override
    public String getUpdateValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("imeClana='").append(imeClana)
                .append("',prezimeClana='").append(prezimeClana)
                .append("',brojTelefonaClana=").append(brojTelefonaClana)
                .append(",UlicaBroj='").append(UlicaBroj)
                .append("',platio=").append(platio)
                .append(",polClana='").append(polClana)
                .append("',JBMGClana=").append(JBMGClana);

        return sb.toString();
    }

    @Override
    public GenericEntity setGetAllValues(ResultSet rs) {
        NalogClana nc = null;
        try {
            nc = new NalogClana(rs.getLong("idClana"), rs.getString("imeClana"), rs.getString("prezimeClana"),
                    rs.getLong("brojTelefonaClana"), rs.getString("UlicaBroj"), rs.getBoolean("platio"),
                    rs.getString("polClana"), rs.getLong("JBMGClana"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nc;
    }

}
