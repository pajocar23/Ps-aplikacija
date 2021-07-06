/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.termin;

import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class UcitajTermin extends AbstractGenericOperation<Object>{
    
    Termin izabraniTermin;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Termin)) {
            throw new Exception("Nevalidni podaci o terminu");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        izabraniTermin=(Termin) repository.get(((Termin)param).getIDTermina(),new Termin());
    }

    public Termin getIzabraniTermin() {
        return izabraniTermin;
    }
    
    
}
