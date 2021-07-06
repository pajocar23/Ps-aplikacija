/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.nalogClana;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import rs.ac.bg.fon.ps.domain.NalogClana;
import rs.ac.bg.fon.ps.helpObjects.Pretraga;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class GetTrazeniNalozi extends AbstractGenericOperation<Object> {
    
    private List<NalogClana> trazeniNalozi;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        List<NalogClana> sviNalozi = new ArrayList<>();
        sviNalozi = repository.getAll(new NalogClana());

        trazeniNalozi = new ArrayList<>();

        for (NalogClana nalogClana : sviNalozi) {
            if (((Pretraga) param).getKriterijum().equals("Ime i prezime")) {
                String imePrezime = nalogClana.getImeClana() + " " + nalogClana.getPrezimeClana();
                if (imePrezime.toLowerCase().contains(((Pretraga) param).getVrednost().toLowerCase()) && !((Pretraga) param).getVrednost().isEmpty() && !((Pretraga) param).getVrednost().equals(" ")) {
                    trazeniNalozi.add(nalogClana);
                }
            } else if (((Pretraga) param).getKriterijum().equals("JBMG")) {
                if (Objects.equals(nalogClana.getJBMGClana(), Long.valueOf(((Pretraga) param).getVrednost()))) {
                    trazeniNalozi.add(nalogClana);
                }
            }
        }               
        
    }

    @Override
    public void executeOperation(Object param) throws Exception {
    }

    public List<NalogClana> getTrazeniNalozi() {
        return trazeniNalozi;
    }  

}
