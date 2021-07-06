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
public class ZapamtiTermin extends AbstractGenericOperation<Object> {

    @Override
    protected void preconditions(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.insert((Termin) param);

        Grupa izmenjenaGrupa = ((Termin)param).getG();
        izmenjenaGrupa.setBrojTermina(izmenjenaGrupa.getBrojTermina() - 1);
        repository.update(izmenjenaGrupa);

    }

}
