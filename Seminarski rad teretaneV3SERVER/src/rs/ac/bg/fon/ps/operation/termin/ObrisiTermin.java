/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.termin;

import rs.ac.bg.fon.ps.domain.Grupa;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class ObrisiTermin extends AbstractGenericOperation<Object> {

    @Override
    protected void preconditions(Object param) throws Exception {
        //ako se obrise termin gde trenira grupa A, broj mogucih termina grupe A ce se povecati za taj jedan
        Grupa g = ((Termin) param).getG();
        g.setBrojTermina(g.getBrojTermina() + 1);

        repository.update(g);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Termin) param);
    }

}
