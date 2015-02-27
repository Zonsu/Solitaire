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
     * @param ekaX
     * @param tokaY
     * @param ekaX
     * @param tokaY
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
            System.out.println("Päälimmäinen: " + kortti1);
        } else if (ekaY > 20) {
            lahtoPino = pinotOikein[ekaX];
            kortti1 = lahtoPino.naytaPaalimmainen();
        }

        /*
         Tyhään maalipinoon siirretään vain ässä. Ässän päälle vain samaa maata numeroa isompana jne.
         */
        if (tokaY == 20 && tokaX > 3) {
            System.out.println("Maalipinon koko: " + maaliPinot[tokaX - 4].pinonKoko());

            if (maaliPinot[tokaX - 4].pinonKoko() > 0) {
                kortti2 = maaliPinot[tokaX - 4].naytaPaalimmainen();
            }
            System.out.println("Pohjimmainen: " + kortti2);

            if (maaliPinot[tokaX - 4].pinonKoko() == 0) {
                if (kortti1.getArvo() != 1) {
                    return false;
                }

            } else if (kortti1.getArvo() - kortti2.getArvo() != 1 || !(kortti1.getMaa().equals(kortti2.getMaa()))) {
                return false;
            }

        }
        if (tokaY > 20) {
            Korttipino maalipino = pinotOikein[tokaX];
            kortti2 = maalipino.naytaPaalimmainen();
            if ((kortti2.getArvo() - kortti1.getArvo() != 1) || kortti2.getVari().equals(kortti1.getVari())) {
                return false;
            }
        }

        return true;

    }

    /**
     * Käännetään paljastuneesta nurinpäin olevasta pakasta sen päälle uusi
     * kortti näkyviin.
     *
     * @param pakan paikka
     */
    public static Kortti kaannaKortti(int mista) {

        Kortti kaannetty = null;
        if (mista == 0) {

            kaannetty = nostaKorttiPakasta();

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

        return kaannetty;
    }

    /**
     * Käännetään pakasta kortti jos siellä niitä on, muuten käännetään jo
     * avatut uudeksi pakaksi, niin että päälimmäinen jää pohjalle.
     */
    public static Kortti nostaKorttiPakasta() {

        Korttipino pakka = pinotNurin[0];
        Korttipino kaannetyt = pinotOikein[0];

        Kortti kaannetty = null;

        if (pakka.pinonKoko() == 0) {
            System.out.println("sekoitetaan");

            ArrayList<Kortti> kortit = kaannetyt.getKortit();
            for (int i = 0; i < kortit.size(); i++) {
                pakka.lisaaKortti(kortit.get(kortit.size() - i - 1));
            }
        } else {
            siirrettava = pakka.naytaPaalimmainen();
            pakka.nostaPaalimmainen();
            kaannetyt.lisaaKortti(siirrettava);
            kaannetty = siirrettava;
            System.out.println("käännetään " + kaannetty);
        }
        pinotNurin[0] = pakka;
        pinotOikein[0] = kaannetyt;

        return kaannetty;

    }

    /**
     * Siiretään kortti toiseen pinoon jos se on laillista. Poistuu ehkä koska
     * hoitaa saman asian kuin siirraPino(). Ehkä.
     *
     * @param mista pinosta
     * @param mihin pinoon
     */
    public static void siirraKortti(int mista, int mihin, int mistaKorkeus, int mihinKorkeus) {

        lahtoPino = pinotOikein[mista];
        maaliPino = pinotOikein[mihin];
        Korttipino nurin = pinotNurin[mista];

        siirrettava = lahtoPino.naytaPaalimmainen();
        maaliPino.lisaaKortti(siirrettava);
        
        lahtoPino.nostaPaalimmainen();

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

    public static Kortti siirraKorttiMaalipinoon(int mista, int mihin) {

        Korttipino oikeinPino = Pelilauta.getOikeinPino(mista);
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

        return kortti;
    }

    public static Kortti siirraKorttiPakastaMaalipinoon(int mihin) {

        Korttipino pino = Pelilauta.getOikeinPino(0);
        Kortti kortti = pino.naytaPaalimmainen();
        pino.nostaPaalimmainen();

        pinotOikein[0] = pino;

        pino = Pelilauta.getMaaliPino(mihin - 4);
        pino.lisaaKortti(kortti);
        Pelilauta.maaliPinot[mihin - 4] = pino;

        return kortti;

    }

    public static void lisaaKorttiPinoon(Kortti kortti, int mihin) {
        Korttipino pino = pinotOikein[mihin];
        pino.lisaaKortti(kortti);
        pinotOikein[mihin] = pino;
    }

    public static Kortti siirraKorttiPakastaLaudalle(int mihin) {
        Korttipino pino = Pelilauta.getOikeinPino(0);
        Kortti kortti = pino.naytaPaalimmainen();
        pino.nostaPaalimmainen();

        lisaaKorttiPinoon(kortti, mihin);
        return kortti;
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
        } else {
            return false;
        }
    }

    /**
     * Käytetään jos koitetaan siirtää korttia maalipinoon, eli onko yhden
     * isompi ja samaa maata.
     *
     * @param siirrettavaKortti
     * @param verrattavaKortti
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
//    public static void siirraKortti(int mista, int mihin, int mistaKorkeus, int mihinKorkeus) {
//
//        lahtoPino = pinotOikein[mista];
//        Korttipino nurin = pinotNurin[mista];
//
//        int halkaisu = (mistaKorkeus - (nurin.pinonKoko() * 20) - 1) / 20;
//        System.out.println("Halkaisu: " + halkaisu + " pinon koko: " + lahtoPino.pinonKoko());
//
//        for (int i = halkaisu - 1; i < lahtoPino.pinonKoko(); i++) {
//            siirrettava = lahtoPino.getKortti(i);
//            maaliPino = pinotOikein[mihin];
//
//            maaliPino.lisaaKortti(siirrettava);
//        }
//        lahtoPino.poista(halkaisu - 1, lahtoPino.pinonKoko());
//
//        if (lahtoPino.pinonKoko() < 1) {
//
//            if (nurin.pinonKoko() > 0) {
//                Kortti kaannetty = nurin.naytaPaalimmainen();
//                nurin.nostaPaalimmainen();
//                pinotNurin[mista] = nurin;
//                lahtoPino.lisaaKortti(kaannetty);
//
//            }
//
//        }
//
//        pinotOikein[mista] = lahtoPino;
//        pinotOikein[mihin] = maaliPino;
//    }
