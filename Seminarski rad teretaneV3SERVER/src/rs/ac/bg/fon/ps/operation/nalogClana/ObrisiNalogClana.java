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
import rs.ac.bg.fon.ps.domain.PrisustvoTerminu;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class ObrisiNalogClana extends AbstractGenericOperation<Object> {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof NalogClana)) {
            throw new Exception("Nevalidni podaci o treneru grupa");
        }
        List<Termin> sviTermini = repository.getAll(new Termin());
        List<PrisustvoTerminu> svaPrisustva = repository.getAll(new PrisustvoTerminu());
        List<Termin> trazeniTermini = new ArrayList<>();
        List<PrisustvoTerminu> trazenaPrisustva = new ArrayList<>();

        for (PrisustvoTerminu prisustvoTerminu : svaPrisustva) {
            //System.out.println("o: " + prisustvoTerminu.getNalogClana().getIdClana());
            if (Objects.equals(prisustvoTerminu.getNalogClana().getIdClana(), ((NalogClana) param).getIdClana())) {
                trazenaPrisustva.add(prisustvoTerminu);
            }
        }
        
        
        for (Termin termin : sviTermini) {
            for (PrisustvoTerminu prisustvoTerminu : trazenaPrisustva) {
                if (Objects.equals(termin.getIDTermina(), prisustvoTerminu.getTermin().getIDTermina())) {
                    trazeniTermini.add(termin);
                }
            }
        }
        

        for (Termin termin : trazeniTermini) {
            Termin t = termin;
            t.setBrojClanova(t.getBrojClanova() - 1);
            repository.update(t);          
        }
        

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((NalogClana) param);
    }

}
