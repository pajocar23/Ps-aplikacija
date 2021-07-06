/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Mr OLOGIZ
 */
public class PripadnostGrupama implements Serializable{
    private Grupa grupa;
    private NalogClana nalogClana;

    public PripadnostGrupama() {
    }

    public PripadnostGrupama(Grupa grupa, NalogClana nalogClana) {
        this.grupa = grupa;
        this.nalogClana = nalogClana;
    }

    public NalogClana getNalogClana() {
        return nalogClana;
    }

    public void setNalogClana(NalogClana nalogClana) {
        this.nalogClana = nalogClana;
    }

    public Grupa getGrupa() {
        return grupa;
    }

    public void setGrupa(Grupa grupa) {
        this.grupa = grupa;
    }

    @Override
    public String toString() {
        return "PripadnostGrupama{" + "grupa=" + grupa + ", nalogClana=" + nalogClana + '}';
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
        final PripadnostGrupama other = (PripadnostGrupama) obj;
        if (!Objects.equals(this.grupa, other.grupa)) {
            return false;
        }
        if (!Objects.equals(this.nalogClana, other.nalogClana)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
