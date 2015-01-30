package Klondike.Pelilogiikka;

import Klondike.Pelilauta.Kortti;
import Klondike.Pelilauta.Korttipino;
import static Klondike.Pelilauta.Peli.maaliPinot;
import static Klondike.Pelilauta.Peli.pinotNurin;
import static Klondike.Pelilauta.Peli.pinotOikein;
import java.util.*;

/**
 * Korttien liikutteluun liittyvä logiikka. Vielä kovin kesken.
 *
 * @author Zonsu
 */
public class KortinSiirto {

    private static Korttipino lahtoPino;
    private static Korttipino maaliPino;
    private static Kortti siirrettava;
    private static Kortti verrattava;

    /**
     * Käännetään paljastuneesta nurinpäin olevasta pakasta sen päälle uusi
     * kortti näkyviin.
     *
     * @param pakan paikka
     */
    public static void kaannaKortti(int mista) {

        if (mista == 0) {

            nostaKorttiPakasta();

        } else {
            maaliPino = pinotOikein[mista];

            if (maaliPino.pinonKoko() == 0) {

                lahtoPino = pinotNurin[mista];
                siirrettava = lahtoPino.naytaPaalimmainen();
                lahtoPino.nostaPaalimmainen();

                maaliPino.lisaaKortti(siirrettava);

                pinotOikein[mista] = maaliPino;
                pinotNurin[mista] = lahtoPino;
            }
        }
    }

    /**
     * Kesken. Siirretään näkyvillä olevasta pinosta toiseen näkyvillä olevaan
     * pinoon. Mukana siirtyvät kaikki pinon päälimmäiset.
     *
     * @param mista pinosta
     * @param monesko kortti pinossa siirretaan
     * @param mihin pinoon
     */
    public static void siirraPino(int mista, int monesko, int mihin) {

    }

    /**
     * Käännetään pakasta kortti jos siellä niitä on, muuten käännetään jo
     * avatut uudeksi pakaksi, niin että päälimmäinen jää pohjalle.
     */
    public static void nostaKorttiPakasta() {
        Korttipino pakka = pinotNurin[0];
        Korttipino kaannetyt = pinotOikein[0];

        if (pakka.pinonKoko() == 0) {

            ArrayList<Kortti> kortit = kaannetyt.getKortit();
            for (int i = 0; i < kortit.size(); i++) {
                pakka.lisaaKortti(kortit.get(kortit.size() - i - 1));
            }

        } else {
            siirrettava = pakka.naytaPaalimmainen();
            pakka.nostaPaalimmainen();
            kaannetyt.lisaaKortti(siirrettava);
        }
        pinotNurin[0] = pakka;
        pinotOikein[0] = kaannetyt;

    }

    /**
     * Siiretään kortti toiseen pinoon jos se on laillista. Poistuu ehkä koska
     * hoitaa saman asian kuin siirraPino(). Ehkä.
     *
     * @param mista pinosta
     * @param mihin pinoon
     */
    public static void siirraKortti(int mista, int mihin) {

        lahtoPino = pinotOikein[mista];
        siirrettava = lahtoPino.naytaPaalimmainen();

        maaliPino = pinotOikein[mihin];
        verrattava = maaliPino.naytaPaalimmainen();

        if (maaliPino.pinonKoko() > 0 && onkoLaillinenSiirto(siirrettava, verrattava)) {
            
            maaliPino.lisaaKortti(siirrettava);
            lahtoPino.nostaPaalimmainen();
            
            pinotOikein[mista] = lahtoPino;
            pinotOikein[mihin] = maaliPino;
        }

    }

    /**
     * Tarkistetaan onko siirto laillinen eli siirrettävä yhden numeron pienempi
     * ja eri väriä.
     *
     * @param siirrettavaKortti
     * @param verrattavaKortti
     * @return boolean
     */
    public static boolean onkoLaillinenSiirto(Kortti siirrettavaKortti, Kortti verrattavaKortti) {
        siirrettava = siirrettavaKortti;
        verrattava = verrattavaKortti;

        if (verrattava.getArvo() - siirrettava.getArvo() == 1 && !verrattava.getVari().equals(siirrettava.getVari())) {
            return true;
        }
        return false;
    }

    /**
     * Käytetään jos koitetaan siirtää korttia maalipinoon, eli onko yhden
     * isompi ja samaa maata.
     *
     * @param siirrettavaKortti
     * @param verrattavaKortti
     * @return boolean
     */
    public static boolean onkoLaillinenSiirtoMaalipinoon(Kortti siirrettavaKortti, Kortti verrattavaKortti) {
        siirrettava = siirrettavaKortti;
        verrattava = verrattavaKortti;

        if (siirrettava.getArvo() - verrattava.getArvo() == 1 && verrattava.getMaa().equals(siirrettava.getMaa())) {
            return true;
        }
        return false;
    }

}
