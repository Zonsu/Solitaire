package Klondike.Pelilogiikka;

import Klondike.Pelilauta.Kortti;
import Klondike.Pelilauta.Korttipino;
import Klondike.Pelilauta.Pelilauta;
import static Klondike.Pelilauta.Pelilauta.maaliPinot;
import static Klondike.Pelilauta.Pelilauta.pinotNurin;
import static Klondike.Pelilauta.Pelilauta.pinotOikein;
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

    private static boolean tyhja;

    /**
     *
     * @param ekaX koordinaatti
     * @param tokaY koordinaatti
     * @param ekaX koordinaatti
     * @param tokaY koordinaatti
     */
    public static boolean saakoSiirtaa(int ekaX, int ekaY, int tokaX, int tokaY) {
        Kortti kortti1 = null;
        Kortti kortti2 = null;

        /*
         Maalipinoista ei voi siirtää
         */
        if (ekaY == 20 && ekaX > 2) {
            return false;
        } /*
         Korttipakkaan ei voi siirtää
         */ else if (tokaY == 20 && tokaX < 3) {
            return false;
        }

        if (ekaY == 20 && ekaX == 2) {
            lahtoPino = pinotOikein[0];
            kortti1 = lahtoPino.naytaPaalimmainen();

        } else {
            lahtoPino = pinotOikein[ekaX];
            kortti1 = lahtoPino.naytaPaalimmainen();
            kortti1 = lahtoPino.getKortti(kortinIndeksi(ekaX, ekaY));
        }

        /*
         Tyhään maalipinoon siirretään vain ässä. Ässän päälle vain samaa maata numeroa isompana jne.
         */
        if (tokaY == 20 && tokaX > 3) {

            if (maaliPinot[tokaX - 4].pinonKoko() > 0) {
                kortti2 = maaliPinot[tokaX - 4].naytaPaalimmainen();
            }

            if (maaliPinot[tokaX - 4].pinonKoko() == 0) {
                if (kortti1.getArvo() != 1) {
                    return false;
                }

            } else if (kortti1.getArvo() - kortti2.getArvo() != 1 || !(kortti1.getMaa().equals(kortti2.getMaa()))) {
                return false;
            }

        }
        if (tokaY != 20) {
            Korttipino maalipino = pinotOikein[tokaX];
            int koko = maalipino.pinonKoko();
            if (maalipino.pinonKoko() != 0) {
                kortti2 = maalipino.naytaPaalimmainen();
                if ((kortti2.getArvo() - kortti1.getArvo() != 1) || kortti2.getVari().equals(kortti1.getVari())) {
                    return false;
                }
            } else if (kortti1.getArvo() != 13) {
                return false;
            }
        }

        return true;

    }

    /**
     * Käännetään pakasta kortti jos siellä niitä on, muuten käännetään jo
     * avatut uudeksi pakaksi, niin että päälimmäinen jää pohjalle.
     */
    public static void nostaKorttiPakasta() {

        Korttipino pakka = pinotNurin[0];
        Korttipino kaannetyt = pinotOikein[0];

        Kortti kaannetty = null;

        if (pakka.pinonKoko() == 0) {

            ArrayList<Kortti> kortit = kaannetyt.getKortit();
            for (int i = 0; i < kortit.size(); i++) {
                pakka.lisaaKortti(kortit.get(kortit.size() - i - 1));
            }
            kaannetyt.tyhjennaPino();
        } else {
            siirrettava = pakka.naytaPaalimmainen();
            pakka.nostaPaalimmainen();
            kaannetyt.lisaaKortti(siirrettava);
            kaannetty = siirrettava;

        }
        pinotNurin[0] = pakka;
        pinotOikein[0] = kaannetyt;

    }

    /**
     * kortinIndeksi tarjoaa apuvälineen kortit suhteellisen sijainnin
     * määrittelyyn pinossa. Käytetään blockaamaan siirtoyritykset korteilta
     * joillta ei ole siihn oikeutta, esimerkiksi pinon keskeltä.
     *
     * @param mista
     * @param monesko
     * @return palautetaan indeksi
     */
    public static int kortinIndeksi(int mista, int monesko) {

        lahtoPino = pinotOikein[mista];
        Korttipino nurin = pinotNurin[mista];

        int pinonKoko = lahtoPino.pinonKoko();

        int indeksi = (monesko - nurin.pinonKoko() * 20 - 1) / 20;
        return indeksi;
    }

    /**
     * Siirretään korttia tai nippua kortteja. Jos kortin päällä on muita ne
     * siirtyvät mukana. Käännetään myös nurinpäin olevasta pinosta uusi kortti
     * tarvittaessa.
     *
     * @param mista mista x
     * @param mihin mihin x
     * @param monesko y
     */

    public static void siirraKortti(int mista, int mihin, int monesko) {

        lahtoPino = pinotOikein[mista];
        maaliPino = pinotOikein[mihin];
        Korttipino nurin = pinotNurin[mista];

        int pinonKoko = lahtoPino.pinonKoko();

        int indeksi = kortinIndeksi(mista, monesko);

        for (int i = indeksi; i < pinonKoko; i++) {
            siirrettava = lahtoPino.getKortti(i);
            maaliPino.lisaaKortti(siirrettava);
        }
        for (int i = indeksi; i < pinonKoko; i++) {
            lahtoPino.nostaPaalimmainen();
        }

        if (lahtoPino.pinonKoko() < 1) {

            if (nurin.pinonKoko() > 0) {
                Kortti kaannetty = nurin.naytaPaalimmainen();
                nurin.nostaPaalimmainen();
                pinotNurin[mista] = nurin;
                lahtoPino.lisaaKortti(kaannetty);

            }

        }

        pinotOikein[mista] = lahtoPino;
        pinotOikein[mihin] = maaliPino;
    }

    /**
     * Siirretään kortti ylös maalipinoon
     * @param mista mista x
     * @param monesko y
     * @param mihin  mihin x
     */
    public static void siirraKorttiMaalipinoon(int mista, int monesko, int mihin) {

        int indeksi = kortinIndeksi(mista, monesko);

        Korttipino oikeinPino = Pelilauta.getOikeinPino(mista);

        if (indeksi + 1 == oikeinPino.pinonKoko()) {

            Kortti kortti = oikeinPino.naytaPaalimmainen();
            oikeinPino.nostaPaalimmainen();

            Korttipino maalipino = Pelilauta.getMaaliPino(mihin - 4);
            maalipino.lisaaKortti(kortti);
            maaliPinot[mihin - 4] = maalipino;
            pinotOikein[mista] = oikeinPino;

            if (pinotOikein[mista].pinonKoko() == 0) {
                if (pinotNurin[mista].pinonKoko() > 0) {

                    setTyhja(true);

                    Korttipino nurinPino = Pelilauta.getNurinPino(mista);
                    Kortti kortti2 = nurinPino.naytaPaalimmainen();
                    nurinPino.nostaPaalimmainen();

                    oikeinPino.lisaaKortti(kortti2);
                    pinotOikein[mista] = oikeinPino;
                    pinotNurin[mista] = nurinPino;
                }
            }
        }
    }

    /**
     * Siirretään pakasta käännetty kortti maalipinoon.
     * @param mihin mihin siirretään
     */
    public static void siirraKorttiPakastaMaalipinoon(int mihin) {

        Korttipino pino = Pelilauta.getOikeinPino(0);
        Kortti kortti = pino.naytaPaalimmainen();
        pino.nostaPaalimmainen();

        pinotOikein[0] = pino;

        pino = Pelilauta.getMaaliPino(mihin - 4);
        pino.lisaaKortti(kortti);
        Pelilauta.maaliPinot[mihin - 4] = pino;
    }

    /**
     * Siirretään annettu kortti.
     * @param kortti kortti
     * @param mihin mihin siirretään
     */
    public static void lisaaKorttiPinoon(Kortti kortti, int mihin) {
        Korttipino pino = pinotOikein[mihin];
        pino.lisaaKortti(kortti);
        pinotOikein[mihin] = pino;
    }

    /**
     * Siirretään korttipinosta käännetty kortti pelipinoon.
     * @param mihin mihin siirretään
     */
    public static void siirraKorttiPakastaLaudalle(int mihin) {
        Korttipino pino = Pelilauta.getOikeinPino(0);
        Kortti kortti = pino.naytaPaalimmainen();
        pino.nostaPaalimmainen();

        lisaaKorttiPinoon(kortti, mihin);
    }

    /**
     * Tarkistetaan onko siirto laillinen eli siirrettävä yhden numeron pienempi
     * ja eri väriä.
     *
     * @param siirrettavaKortti kortti joka siirretään
     * @param verrattavaKortti mihinkä verrataan
     * @return boolean
     */
    public static boolean onkoLaillinenSiirto(Kortti siirrettavaKortti, Kortti verrattavaKortti) {
        siirrettava = siirrettavaKortti;
        verrattava = verrattavaKortti;

        if (verrattava.getArvo() - siirrettava.getArvo() == 1 && !verrattava.getVari().equals(siirrettava.getVari())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Käytetään jos koitetaan siirtää korttia maalipinoon, eli onko yhden
     * isompi ja samaa maata.
     *
     * @param siirrettavaKortti kortti joka siirretään
     * @param verrattavaKortti  mihinkä verrataan
     * @return boolean
     */
    public static boolean getTyhja() {
        return tyhja;
    }

    public static void setTyhja(boolean arvo) {
        tyhja = arvo;
    }

    public static boolean onkoLaillinenSiirtoMaalipinoon(Kortti siirrettavaKortti, Kortti verrattavaKortti) {
        siirrettava = siirrettavaKortti;
        verrattava = verrattavaKortti;

        if (siirrettava.getArvo() - verrattava.getArvo() == 1 && verrattava.getMaa().equals(siirrettava.getMaa())) {
            return true;
        } else {
            return false;
        }
    }

}

