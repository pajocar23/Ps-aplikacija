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
public class TrenerLogInException extends Exception {

    public TrenerLogInException() {
        super("Pogresni korisnicko ime ili lozinka!");
    }

    public TrenerLogInException(String message) {
        super(message);
    }
}
