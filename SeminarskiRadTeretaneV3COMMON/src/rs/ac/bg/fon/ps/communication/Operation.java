/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.communication;

/**
 *
 * @author Mr OLOGIZ
 */
public enum Operation {
    LOGIN, //
    LOGUT,
    ZAPAMTI_NALOG_CLANA, //
    UCITAJ_LISTU_TRENERA, //POMOCNA
    NADJI_NALOGE_CLANOVA, // meni ova operacija nalazi naloge po vrednosti, potrebna mi je i ona koja nalazi sve naloge i moze da ih doda u termin
    UCITAJ_LISTU_NALOGA_CLANOVA, //ovo je ta koja nalazi sve naloge
    OBRISI_NALOG_CLANA, //
    IZMENI_NALOG_CLANA, //u wordu je ovo ista operacija kao zapamti nalog clana
    UCITAJ_LISTU_GRUPA_ULOGOVANOG_TRENERA, //
    ZAPAMTI_GRUPU, //DODATNA
    IZMENI_GRUPU, //DODATNA
    GET_LISTA_TERMINA, // svi termini
    ZAPAMTI_TERMIN, //
    GET_TRAZENI_TERMINI,//
    OBRISI_TERMIN, //
    IZMENI_TERMIN, //u wordu je ovo ista operacija kao zapamti termin
    ZAPAMTI_PRISUSTVO_TERMINU, //
    UCITAJ_NALOG_CLANA, //
    UCITAJ_TERMIN, //
    UCITAJ_LISTU_PRISUSTVA //POMOCNA
    
}
