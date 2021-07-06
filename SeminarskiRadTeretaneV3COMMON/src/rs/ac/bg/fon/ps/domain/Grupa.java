/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author Mr OLOGIZ
 */
public class Grupa implements GenericEntity {

    private Long idGrupe;
    private int brojTermina;
    private String nazivGrupe;
    private Trener trener;

    public Grupa() {
    }

    public Grupa(Long idGrupe, int brojTermina, String nazivGrupe, Trener trener) {
        this.idGrupe = idGrupe;
        this.brojTermina = brojTermina;
        this.nazivGrupe = nazivGrupe;
        this.trener = trener;
    }

    public Long getIdGrupe() {
        return idGrupe;
    }

    public void setIdGrupe(Long idGrupe) {
        this.idGrupe = idGrupe;
    }

    public int getBrojTermina() {
        return brojTermina;
    }

    public void setBrojTermina(int brojTermina) {
        this.brojTermina = brojTermina;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public String getNazivGrupe() {
        return nazivGrupe;
    }

    public void setNazivGrupe(String nazivGrupe) {
        this.nazivGrupe = nazivGrupe;
    }

    @Override
    public String toString() {
        return "Grupa{" + "idGrupe=" + idGrupe + ", brojTermina=" + brojTermina + ", nazivGrupe=" + nazivGrupe + ", trener=" + trener + '}';
    }

    public String toString2() {
        return nazivGrupe;
    }

    @Override
    public String getTableName() {
        return "grupa";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "brojTermina,nazivGrupe,idTrenera";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(brojTermina).append(",")
                .append("'").append(nazivGrupe).append("',")
                .append(trener.getIDTrenera());

        return sb.toString();
    }

    @Override
    public String getJoinConditions() {
        return "LEFT JOIN " + "trener ON grupa.idTrenera=trener.idTrenera";
    }

    @Override
    public String getPrimaryKey() {
        return "idGrupe";
    }

    @Override
    public String getPrimaryKeyValue() {
        return idGrupe.toString();
    }

    @Override
    public String getUpdateValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("brojTermina =").append(brojTermina)
                .append(",nazivGrupe='").append(nazivGrupe)
                .append("',idTrenera=").append(trener.getIDTrenera());

        return sb.toString();
    }

    @Override
    public GenericEntity setGetAllValues(ResultSet rs) {
        Grupa g = null;

        try {
            Trener trener = new Trener();
            trener.setIDTrenera(rs.getLong("trener.idTrenera"));
            trener.setImeTrenera(rs.getString("trener.imeTrenera"));
            trener.setPrezimeTrenera(rs.getString("trener.prezimeTrenera"));
            trener.setBrojTelefonaTrenera(rs.getLong("trener.brojTelefonaTrenera"));
            trener.setPolTrenera(rs.getString("trener.polTrenera"));
            trener.setJBMGTrenera(rs.getLong("trener.JBMGTrenera"));
            trener.setKorisnickoIme(rs.getString("trener.korisnickoIme"));
            trener.setSifra(rs.getString("trener.sifra"));

            g = new Grupa(rs.getLong("grupa.idGrupe"), rs.getInt("grupa.brojTermina"), rs.getString("grupa.nazivGrupe"),
                    trener);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }

}
