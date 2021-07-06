/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.trener;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class GetSviTreneri extends AbstractGenericOperation<List<Trener>>{
    
    List<Trener> sviTreneri;
    
    @Override
    protected void preconditions(Object param) throws Exception {        
    }

    @Override
    public void executeOperation(Object param) throws Exception {
        sviTreneri=repository.getAll(new Trener());
    }

    public List<Trener> getSviTreneri() {
        return sviTreneri;
    }
    
    
    
}
