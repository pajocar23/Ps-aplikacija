/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.grupa;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Grupa;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class ZapamtiGrupu extends AbstractGenericOperation<Object> {
    Grupa g;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        List<Grupa> grupe = repository.getAll(new Grupa());
        for (Grupa grupa : grupe) {
            if (grupa.getNazivGrupe().equals(((Grupa) param).getNazivGrupe())) {
                throw new Exception("Uneti naziv grupe vec postoji");
            }
        }
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.insert((Grupa)param);
        g=(Grupa)param;
    }

    public Grupa getG() {
        return g;
    }
    
    

}
