package Klondike.GraafinenUi;

import Klondike.Pelilogiikka.KlikkausLaskuri;
import java.awt.event.*;

/**
 * Luokasta muodostetaan kaikki mahdolliset pelilaudalla olevat paikat. Luokka
 * kuuntelee klikkauksia ja sovelluslogiikka päättää klikkausten määrän mukaan
 * mitä tapahtuu.
 *
 * @author Zonsu
 */
public class KortinKuuntelija implements ActionListener {

    //Paikka kertoo mihin sarakkeeseen kortti piirretään tai mistä poistetaan.
    private int paikka;

    public KortinKuuntelija(int paikka) {
        this.paikka = paikka;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        KlikkausLaskuri.lisaaKlikkaus(this.paikka);
    }
}
