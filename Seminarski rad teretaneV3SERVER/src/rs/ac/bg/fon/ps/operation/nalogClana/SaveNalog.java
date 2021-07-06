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
public class SaveNalog extends AbstractGenericOperation<NalogClana> {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof NalogClana)) {
            throw new Exception("Nevalidni podaci o nalogu");
        }

        List<NalogClana> naloziClanova = new ArrayList<>();
        NalogClana ncc = null;

        naloziClanova = repository.getAll(new NalogClana());

        for (NalogClana nalogClana : naloziClanova) {

            if (Objects.equals(nalogClana.getJBMGClana(),((NalogClana)param).getJBMGClana())) {

                throw new Exception("Korisnik sa unetim JBMGom vec je registrovan");
            }
        }

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.insert((NalogClana)param);    
    }

   

    

   

}
