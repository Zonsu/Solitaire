package Klondike.Pelilogiikka;

import Klondike.GraafinenUi.PelilaudanPiirtaja;
import Klondike.Pelilauta.Kortti;
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
                laskuri = 0;
                //Kysytään pelilogiikalta lupaa siirtää.
                if (KortinSiirto.saakoSiirtaa(x, y, korttiX, korttiY)) {
                    System.out.println("Eka kortti: " + x + " " + y);
                    System.out.println("Toka kortti: " + korttiX + " " + korttiY);

                    if (korttiY == 20 && (y > 20 || y < 20)) {
                        KortinSiirto.siirraKorttiMaalipinoon(x, y, korttiX);

                    } else if (korttiY == 20 && y == 20) {
                        KortinSiirto.siirraKorttiPakastaMaalipinoon(korttiX);

                    } else if (y == 20 && (korttiY > 20 || korttiY < 20) && x < 3) {
                        KortinSiirto.siirraKorttiPakastaLaudalle(korttiX);

                    } else {
                        KortinSiirto.siirraKortti(x, korttiX, y);

                    }
                    
                    PelilaudanPiirtaja.piirraUudestaan(x, y, korttiX, korttiY);

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


