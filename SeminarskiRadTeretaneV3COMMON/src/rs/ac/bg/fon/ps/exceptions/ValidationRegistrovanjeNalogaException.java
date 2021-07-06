/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.exceptions;

/**
 *
 * @author Mr OLOGIZ
 */
public class ValidationRegistrovanjeNalogaException extends Exception{
    
     public ValidationRegistrovanjeNalogaException() {
        super("Greska prilikom validacije forme, proverite sva polja!");
    }
    
    public ValidationRegistrovanjeNalogaException(String message){
        super(message);
    }
}
