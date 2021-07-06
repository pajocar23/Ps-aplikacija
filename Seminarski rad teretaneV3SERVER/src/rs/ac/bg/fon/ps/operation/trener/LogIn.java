/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation.trener;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.operation.AbstractGenericOperation;

/**
 *
 * @author Mr OLOGIZ
 */
public class LogIn extends AbstractGenericOperation<Object> {

    private Trener trener;

    @Override
    protected void preconditions(Object param) throws Exception {

        List<Trener> treneri;
        trener = new Trener();

        treneri = repository.getAll(new Trener());
        //System.out.println("svi "+treneri);
        String username = ((Trener) param).getKorisnickoIme();
        String lozinka = ((Trener) param).getSifra();
        //System.out.println("sifra "+lozinka);
        //System.out.println("ime "+username);
        for (Trener trener1 : treneri) {
            if (trener1.getKorisnickoIme().equals(username)) {
                System.out.println("1:" + trener1.getKorisnickoIme());
                System.out.println("2:" + username);
                if (trener1.getSifra().equals(lozinka)) {
                    trener = trener1;
                    return;
                }
                throw new Exception("Pogrešna lozinka !");
            }
        }

        throw new Exception("Korisnik čije ste ime uneli ne postoji !");

    }

    @Override
    public void executeOperation(Object param) throws Exception {
    }

    public Trener getTrener() {
        return trener;
    }

}
