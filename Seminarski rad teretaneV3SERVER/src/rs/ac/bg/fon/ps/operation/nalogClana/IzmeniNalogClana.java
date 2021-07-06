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
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class IzmeniNalogClana extends AbstractGenericOperation<Object> {

    @Override
    protected void preconditions(Object param) throws Exception {
        List<NalogClana> sviNalozi = new ArrayList<>();
        sviNalozi = repository.getAll(new NalogClana());

        for (NalogClana nalogClana : sviNalozi) {
            if (!Objects.equals(nalogClana.getIdClana(), ((NalogClana) param).getIdClana()) && Objects.equals(nalogClana.getJBMGClana(), ((NalogClana) param).getJBMGClana())) {
                throw new Exception("Korisnik sa unetim JBMGom vec je registrovan");
            }
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.update((NalogClana) param);
    }

}
