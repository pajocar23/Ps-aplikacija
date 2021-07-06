/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.prisustvoTerminu;

import java.util.List;
import rs.ac.bg.fon.ps.domain.PrisustvoTerminu;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class GetSvaPrisustva extends AbstractGenericOperation<Object>{
    
    List<PrisustvoTerminu> svaPrisustva;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        svaPrisustva=repository.getAll(new PrisustvoTerminu());
    }

    public List<PrisustvoTerminu> getSvaPrisustva() {
        return svaPrisustva;
    }
    
    
    
}
