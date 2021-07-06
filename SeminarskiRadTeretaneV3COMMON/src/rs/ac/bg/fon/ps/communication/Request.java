/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.communication;

import java.io.Serializable;

/**
 *
 * @author Mr OLOGIZ
 */
public class Request implements Serializable{
    private Operation operation;
    private Object argument;
    
    /////get trazeni nalozi ili get trazeni nalozi
    private String kriterijum;
    private String vrednost;
    ////
    

    public Request() {
    }

    public Request(Operation operation, Object argument, String kriterijum, String vrednost) {
        this.operation = operation;
        this.argument = argument;
        this.kriterijum = kriterijum;
        this.vrednost = vrednost;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Object getArgument() {
        return argument;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }

    public String getKriterijum() {
        return kriterijum;
    }

    public void setKriterijum(String kriterijum) {
        this.kriterijum = kriterijum;
    }

    public String getVrednost() {
        return vrednost;
    }

    public void setVrednost(String vrednost) {
        this.vrednost = vrednost;
    }

    
    
}


