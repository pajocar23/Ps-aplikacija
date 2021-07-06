/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.termin;

import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class IzmeniTermin extends AbstractGenericOperation<Object> {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Termin)) {
            throw new Exception("Nevalidni podaci o terminu");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.update((Termin)param);
    }

}
