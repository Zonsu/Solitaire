package Klondike.GraafinenUi;

import Klondike.Pelilogiikka.KlikkausLaskuri;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Luokasta muodostetaan kaikki mahdolliset pelilaudalla olevat paikat. Luokka
 * kuuntelee klikkauksia ja sovelluslogiikka päättää klikkausten määrän mukaan
 * mitä tapahtuu.
 *
 * @author Zonsu
 */
public class KortinKuuntelija implements ActionListener {

    //Paikka kertoo mihin sarakkeeseen kortti piirretään tai mistä poistetaan.
    private Point paikka;
    private JButton kortti;
    
    private int x;
    private int y;

    public KortinKuuntelija(JButton kortti) {
        this.paikka = paikka;
        this.kortti = kortti;

    }

 

    @Override
    public void actionPerformed(ActionEvent ae) {
        Point piste = kortti.getLocation();
        y = (int) piste.getY() /20 -1;
        
        
        Container vanhempi = kortti.getParent();
        
        Point vanhempiPiste = vanhempi.getLocation();
        y = (int) (vanhempiPiste.getY()/vanhempi.getHeight()) + piste.y;
        x = ((int)vanhempiPiste.getX()/vanhempi.getWidth() + 1);
        
        KlikkausLaskuri.lisaaKlikkaus(x, y, kortti);
    }
}
