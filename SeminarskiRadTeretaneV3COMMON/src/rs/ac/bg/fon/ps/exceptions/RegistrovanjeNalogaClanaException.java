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
public class RegistrovanjeNalogaClanaException extends Exception{
    
     public RegistrovanjeNalogaClanaException() {
        super("Sistem ne moze da kreira nalog clana");
    }

    public RegistrovanjeNalogaClanaException(String message) {
        super(message);
    }
    
}
