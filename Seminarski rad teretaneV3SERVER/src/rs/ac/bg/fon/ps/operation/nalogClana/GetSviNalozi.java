/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.nalogClana;

import java.util.List;
import rs.ac.bg.fon.ps.domain.NalogClana;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class GetSviNalozi extends AbstractGenericOperation<List<NalogClana>>{
    List<NalogClana> sviNalozi;
    
    @Override
    protected void preconditions(Object param) throws Exception {       
    }

    @Override
    public void executeOperation(Object param) throws Exception {
         sviNalozi=repository.getAll(new NalogClana());
    }

    public List<NalogClana> getSviNalozi() {
        return sviNalozi;
    }
    
    
    
}
