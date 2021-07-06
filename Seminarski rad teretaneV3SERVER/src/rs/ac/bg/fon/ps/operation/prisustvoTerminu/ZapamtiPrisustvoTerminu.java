/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.prisustvoTerminu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import rs.ac.bg.fon.ps.domain.PrisustvoTerminu;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class ZapamtiPrisustvoTerminu extends AbstractGenericOperation<Object> {

    @Override
    protected void preconditions(Object param) throws Exception {
        List<PrisustvoTerminu> prisustvaTerminu = repository.getAll(new PrisustvoTerminu());

        for (PrisustvoTerminu prisustvoTerminu : prisustvaTerminu) {
            if (Objects.equals(((PrisustvoTerminu) param).getNalogClana().getIdClana(), prisustvoTerminu.getNalogClana().getIdClana())
                    && Objects.equals(((PrisustvoTerminu) param).getTermin().getIDTermina(), prisustvoTerminu.getTermin().getIDTermina())) {
                throw new Exception("Nalog je vec registrovan za taj termin");
            }
        }
        repository.insert((PrisustvoTerminu) param);

        List<Termin> SviTermini = repository.getAll(new Termin());
        Termin noviTermin = null;

        for (Termin termin : SviTermini) {

            if (Objects.equals(((PrisustvoTerminu) param).getTermin().getIDTermina(), termin.getIDTermina())) {
                if (termin.getBrojClanova() == termin.getMaksBrojClanova()) {
                    throw new Exception("Popunjeni kapaciteti termina");
                }
                noviTermin = termin;
                noviTermin.setBrojClanova(noviTermin.getBrojClanova() + 1);
            }

        }
        
        repository.update(noviTermin);

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
    }

}
