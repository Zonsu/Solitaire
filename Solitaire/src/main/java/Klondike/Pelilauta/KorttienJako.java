package Klondike.Pelilauta;

import static Klondike.Pelilauta.Pelilauta.maaliPinot;
import static Klondike.Pelilauta.Pelilauta.pinotNurin;
import static Klondike.Pelilauta.Pelilauta.pinotOikein;

import java.util.*;

/**
 * Luokassa korttien pelipöytään jakoon liittyvä logiikka.
 *
 * @author Zonsu
 */
public class KorttienJako {

    private static ArrayList<Kortti> korttiPakka;

    /**
     * Täysin uusi peli eli luodaan myös kortit.
     */
    public static void uudetKortit() {
        korttiPakka = luoPakka();
        uusiJako();
    }

    /**
     * Jos halutaan sekoittaa pakka ja jakaa uudestaan.
     */
    public static void uusiJako() {
        korttiPakka = sekoitaPakka(korttiPakka);
        jaaUudestaan();
    }

    /**
     * Uudelleenjako jo sekoitetuilla korteilla.
     */
    public static void jaaUudestaan() {

        // Seitsemän pelipinoa ja pakka. Pinot erikseen korteille kuvapuoli alas- ja ylöspäin.
        pinotNurin = new Korttipino[8];
        pinotOikein = new Korttipino[13];
        // Maalipinot erikseen.
        maaliPinot = new Korttipino[4];

        jaaKortit();
        alustaMaalipinot();
        alustaPakka();
    }

    /**
     * Luodaan 52 korttia, 4 maata joissa jokaisessa 13 korttia.
     *
     * @return luotu pakka
     */
    public static ArrayList luoPakka() {

        ArrayList<Kortti> pakka = new ArrayList();

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 14; j++) {

                Kortti kortti = new Kortti(j);

                if (i == 0) {
                    kortti.setMaa("Pata");
                }
                if (i == 1) {
                    kortti.setMaa("Hertta");
                }
                if (i == 2) {
                    kortti.setMaa("Risti");
                }
                if (i == 3) {
                    kortti.setMaa("Ruutu");
                }
                pakka.add(kortti);
            }
        }
        return pakka;
    }

    /**
     * Lasketaan pakan korttien lukumäärä
     *
     * @param pakka
     * @return korttien määrä
     */
    public static int pakanKoko(ArrayList<Kortti> pakka) {
        int laskuri = 0;

        for (Kortti kortti : pakka) {
            laskuri++;
        }

        return laskuri;
    }

    /**
     * Pakan sekoitus
     *
     * @param pakka
     * @return sekoitettu pakka
     */
    public static ArrayList<Kortti> sekoitaPakka(ArrayList pakka) {
        Collections.shuffle(pakka);
        return pakka;
    }

    /**
     * Jakaa kortit seitsemään pelipinoon ja jokaisen pinon päälle kortin
     * kuvapuoli ylöspäin.
     */
    public static void jaaKortit() {
        pinotNurin = new Korttipino[8];
        pinotOikein = new Korttipino[8];

        int pinoLaskuri = 1;

        for (int i = 1; i < 8; i++) {
            Korttipino kortitNurin = new Korttipino(i);
            Korttipino kortitOikein = new Korttipino(i);

            // Ekassa pinossa ei yhtään nurinpäin olevaa korttia, vikassa kuusi.
            for (int k = 1; k < i; k++) {
                kortitNurin.lisaaKortti(korttiPakka.get(korttiPakka.size() - pinoLaskuri));
                pinoLaskuri++;
            }
            // Jaetaan kortti kuvapuoli ylöspäin.
            kortitOikein.lisaaKortti(korttiPakka.get(korttiPakka.size() - pinoLaskuri));

            pinoLaskuri++;
            pinotNurin[i] = kortitNurin;
            pinotOikein[i] = kortitOikein;
        }
    }

    /**
     * Tarkistetaan pino halutusta paikasta
     *
     * @param koodi sille millainen pino
     * @param pinon paikka pinojenv taulussa
     * @return palauttaa halutun pinon
     */
    public static Korttipino palautaPino(int mika, int paikka) {
        if (mika == 0) {
            return pinotNurin[paikka];
        } else if (mika == 1) {
            return pinotOikein[paikka];
        } else if (mika == 2) {
            return maaliPinot[paikka];
        }
        return null;
    }

    /**
     * Alustetaan neljä tyhjää maalipinoa.
     */
    public static void alustaMaalipinot() {

        for (int i = 0; i < 4; i++) {
            Korttipino maali = new Korttipino(i);
            maaliPinot[i] = maali;
        }
    }

    /**
     * Laitetaan loput kortit pakkaan.
     */
    public static void alustaPakka() {

        Korttipino pakka = new Korttipino(0);
        Korttipino pakastaKaannetyt = new Korttipino(0);

        for (int i = 0; i < 24; i++) {
            pakka.lisaaKortti(korttiPakka.get(i));
        }
        pinotNurin[0] = pakka;
        pinotOikein[0] = pakastaKaannetyt;

    }

}
