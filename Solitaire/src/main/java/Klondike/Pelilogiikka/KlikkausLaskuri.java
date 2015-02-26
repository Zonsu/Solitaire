package Klondike.Pelilogiikka;

import Klondike.GraafinenUi.PelilaudanPiirtaja;
import javax.swing.JButton;

/**
 * Luokka pitää kirjaa kenttien saamasta klikkausten määristä ja tekee niiden
 * perusteella päätöksen yritetäänkö korttia siirtää ja minne.
 *
 * @author Zonsu
 */
public class KlikkausLaskuri {

    private static int x;
    private static int y;
    private static int laskuri;
    private static JButton muistiKortti;

    /**
     * Metodi kuuntelee korttien klikkauksia. Jos peräkkäin klikataan kahta eri
     * korttia, tarkistetaanko saako korttia siirtää. Laskuri 0 tai 1, 0: ei
     * valittua korttia, 1: valittu kortti siirrettäväksi
     *
     * @param tarkistetaan missä kohdassa olevaa korttia on klikattu
     */
    public static void lisaaKlikkaus(int korttiX, int korttiY, JButton kortti) {

        if (laskuri == 0) {
            x = korttiX;
            y = korttiY;
            muistiKortti = kortti;
            
            laskuri++;

        } else if (laskuri == 1) {

            if (x == korttiX && y == korttiY) {
                laskuri = 0;
            } else {
                if (KortinSiirto.saakoSiirtaa(x, y, korttiX, korttiY)) {
                    System.out.println("Eka kortti: " + x + " " + y);
                    System.out.println("Toka kortti: " + korttiX + " " + korttiY);
                    
//                    KortinSiirto.siirraKortti(x, korttiX);
                    
//                    Kortti kortti1 = 
//                    
//                    PelilaudanPiirtaja.siirraKortti(x, muistiKortti, korttiX, kortti);
                    
                    
                    laskuri = 0;

                } else {
                    laskuri = 0;
                }
            }
        }
        System.out.println("Klikkauksia: " + laskuri);
        System.out.println("");
    }
}
