/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.termin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.helpObjects.Pretraga;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class GetTrazeniTermini extends AbstractGenericOperation<Object> {

    List<Termin> trazeniTermini;

    @Override
    protected void preconditions(Object param) throws Exception {
        List<Termin> sviTermini = repository.getAll(new Termin());

        trazeniTermini = new ArrayList<>();

        for (Termin termin : sviTermini) {

            if (((Pretraga)param).getKriterijum().equals("Datum")) {
                Date sqlDate = termin.getDatum();
                String sqlDateText = String.valueOf(sqlDate);

                if (sqlDateText.equals(((Pretraga)param).getVrednost())) {
                    trazeniTermini.add(termin);
                }

            } else if (((Pretraga)param).getKriterijum().equals("Naziv grupe")) {
                String nazivGr = termin.getG().getNazivGrupe();
                if (nazivGr.toLowerCase().contains(((Pretraga)param).getVrednost().toLowerCase()) && !((Pretraga)param).getVrednost().isEmpty() && !((Pretraga)param).getVrednost().equals(" ")) {
                    trazeniTermini.add(termin);
                }
            } else { //ime i prezime trenera
                String imePrez = termin.getG().getTrener().getImeTrenera() + " " + termin.getG().getTrener().getPrezimeTrenera();
                if (imePrez.toLowerCase().contains(((Pretraga)param).getVrednost().toLowerCase()) && !((Pretraga)param).getVrednost().isEmpty() && !((Pretraga)param).getVrednost().equals(" ")) {
                    trazeniTermini.add(termin);
                }
            }           
        }
       
    }

    @Override
    protected void executeOperation(Object param) throws Exception {       
    }

    public List<Termin> getTrazeniTermini() {
        return trazeniTermini;
    }

    
}
