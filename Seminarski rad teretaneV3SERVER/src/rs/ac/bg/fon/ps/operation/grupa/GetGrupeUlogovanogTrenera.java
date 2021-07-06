/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.grupa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import rs.ac.bg.fon.ps.domain.Grupa;
import rs.ac.bg.fon.ps.domain.NalogClana;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class GetGrupeUlogovanogTrenera extends AbstractGenericOperation<List<Grupa>> {

    List<Grupa> grupeUlogovanogTrenera;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Trener)) {
            throw new Exception("Nevalidni podaci o treneru grupa");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {

        List<Grupa> sveGrupe = repository.getAll(new Grupa());
        grupeUlogovanogTrenera = new ArrayList<>();

        for (Grupa grupa : sveGrupe) {
            if (Objects.equals(grupa.getTrener().getIDTrenera(), ((Trener) param).getIDTrenera()) && grupa.getBrojTermina() > 0) {
                grupeUlogovanogTrenera.add(grupa);
            }
        }
      
    }

    public List<Grupa> getGrupeUlogovanogTrenera() {
        return grupeUlogovanogTrenera;
    }

}
