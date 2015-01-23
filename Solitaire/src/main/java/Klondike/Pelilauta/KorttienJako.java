package Klondike.Pelilauta;

import static Klondike.Pelilauta.Peli.maaliPinot;
import static Klondike.Pelilauta.Peli.pinotNurin;
import static Klondike.Pelilauta.Peli.pinotOikein;

import java.util.*;

public class KorttienJako {

    public static ArrayList<Kortti> korttiPakka;

    public static void uudetKortit() {                                              // Täysin uusi peli eli luodaan myös kortit.
        korttiPakka = luoPakka();
        uusiJako();
    }

    public static void uusiJako() {
        korttiPakka = sekoitaPakka(korttiPakka);
        jaaUudestaan();
    }

    public static void jaaUudestaan() {

        pinotNurin = new Korttipino[8];                                             // Seitsemän pelipinoa ja pakka. Pinot erikseen korteille kuvapuoli alas- ja ylöspäin.
        pinotOikein = new Korttipino[8];
        maaliPinot = new Korttipino[4];                                             // Maalipinot erikseen.

        jaaKortit();
        alustaMaalipinot();
        laitaPakkaan();
    }

    public static ArrayList luoPakka() {

        ArrayList<Kortti> pakka = new ArrayList();

        for (int i = 0; i < 4; i++) {                                               // 4 maata ja 13 eri korttia. Selkeyden vuoksi korttien
            for (int j = 1; j < 14; j++) {                                          // maat Stringeinä.

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

    public static int pakanKoko(ArrayList<Kortti> pakka) {                        // Testejä varten
        int laskuri = 0;

        for (Kortti kortti : pakka) {
            laskuri++;
        }

        return laskuri;
    }

    public static ArrayList<Kortti> sekoitaPakka(ArrayList pakka) {
        Collections.shuffle(pakka);
        return pakka;
    }

    public static void jaaKortit() {
        pinotNurin = new Korttipino[8];
        pinotOikein = new Korttipino[8];

        int pinoLaskuri = 1;

        for (int i = 1; i < 8; i++) {                                               // Jaetaan kortit ensin seitsemään pelipinoon. Jokaisen pinon päällä yksi kortti kuvapuoli ylöspäin.
            Korttipino kortitNurin = new Korttipino(i);
            Korttipino kortitOikein = new Korttipino(i);

            for (int k = 1; k < i; k++) {
                kortitNurin.lisaaKortti(korttiPakka.get(korttiPakka.size() - pinoLaskuri));   // Ekassa pinossa ei yhtään nurinpäin olevaa korttia, vikassa kuusi.
                pinoLaskuri++;
            }

            kortitOikein.lisaaKortti(korttiPakka.get(korttiPakka.size() - pinoLaskuri));      // Jaetaan kortti kuvapuoli ylöspäin.

            pinoLaskuri++;
            pinotNurin[i] = kortitNurin;
            pinotOikein[i] = kortitOikein;
        }
    }

    public static Korttipino palautaPino(int kumpi, int paikka) {
        if (kumpi == 0) {
            return pinotNurin[paikka];
        } else if (kumpi == 1) {
            return pinotOikein[paikka];
        } else if (kumpi == 2) {
            return maaliPinot[paikka];
        }
        return null;
    }

    public static void alustaMaalipinot() {                                         // Alustetaan maalipinot.

        for (int i = 0; i < 4; i++) {
            Korttipino maali = new Korttipino(i);
            maaliPinot[i] = maali;
        }
    }

    public static void laitaPakkaan() {                                             // Jäljelle jääneet kortit laitetaan pakkaan (24 korttia).

        Korttipino pakka = new Korttipino(0);
        Korttipino pakastaKaannetyt = new Korttipino(0);

        for (int i = 0; i < 24; i++) {
            pakka.lisaaKortti(korttiPakka.get(i));
        }
        pinotNurin[0] = pakka;
        pinotOikein[0] = pakastaKaannetyt;

    }

}
