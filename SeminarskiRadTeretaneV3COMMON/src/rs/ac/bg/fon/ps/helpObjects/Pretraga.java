/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.helpObjects;

/**
 *
 * @author Mr OLOGIZ
 */
public class Pretraga {
    private String kriterijum;
    private String vrednost;

    public Pretraga() {
    }

    public Pretraga(String kriterijum, String vrednost) {
        this.kriterijum = kriterijum;
        this.vrednost = vrednost;
    }

    public String getVrednost() {
        return vrednost;
    }

    public void setVrednost(String vrednost) {
        this.vrednost = vrednost;
    }

    public String getKriterijum() {
        return kriterijum;
    }

    public void setKriterijum(String kriterijum) {
        this.kriterijum = kriterijum;
    }
    
    
}
