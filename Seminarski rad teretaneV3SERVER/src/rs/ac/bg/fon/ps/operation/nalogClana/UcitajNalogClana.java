/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.nalogClana;

import rs.ac.bg.fon.ps.domain.NalogClana;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class UcitajNalogClana extends AbstractGenericOperation<Object>{
    
    NalogClana selektovaniNalog;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof NalogClana)) {
            throw new Exception("Nevalidni podaci o terminu");
        }
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        selektovaniNalog=(NalogClana) repository.get(((NalogClana)param).getIdClana(),new NalogClana());
    }

    public NalogClana getSelektovaniNalog() {
        return selektovaniNalog;
    }
    
    
}
