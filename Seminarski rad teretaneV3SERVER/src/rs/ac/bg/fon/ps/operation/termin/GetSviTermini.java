/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.termin;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Termin;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class GetSviTermini extends AbstractGenericOperation<List<Termin>>{
    
    private List<Termin> sviTermini;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        sviTermini=repository.getAll(new Termin());
    }

    public List<Termin> getSviTermini() {
        return sviTermini;
    }
    
}
