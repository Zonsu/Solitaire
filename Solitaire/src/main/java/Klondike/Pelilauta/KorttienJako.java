package Klondike.Pelilauta;

import static Klondike.Pelilauta.Peli.maaliPinot;
import static Klondike.Pelilauta.Peli.pinotNurin;
import static Klondike.Pelilauta.Peli.pinotOikein;

import java.util.*;

public class KorttienJako {

    public static ArrayList<Kortti> korttiPakka;

    public static void uusiJako() {
        luoPakka();
        sekoitaPakka();

        jaaUudestaan();
    }

    public static void jaaUudestaan() {

        pinotNurin = new Korttipino[8];                                             // Seitsemän pelipinoa ja pakka.
        pinotOikein = new Korttipino[8];
        maaliPinot = new Korttipino[4];                                             // Maalipinot erikseen selkeyden vuoksi.

        jaaKortit();
        alustaMaalipinot();
        laitaPakkaan();
    }

    public static void luoPakka() {

        korttiPakka = new ArrayList();

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
                korttiPakka.add(kortti);
            }
        }
    }

    public static void sekoitaPakka() {
        Collections.shuffle(korttiPakka);
    }

    public static void jaaKortit() {

        int pinoLaskuri = 1;                                                        

        for (int i = 1; i < 8; i++) {                                               // Jaetaan kortit ensin seitsemään pelipinoon. Jokaisen pinon
            Korttipino kortitNurin = new Korttipino(i);                             //  päällä on yksi kortti kuvapuoli ylöspäin.    
            Korttipino kortitOikein = new Korttipino(i);

            for (int k = 1; k < i; k++) {
                kortitNurin.lisaaKortti(korttiPakka.get(korttiPakka.size() - pinoLaskuri));   // Ekassa pinossa ei yhtään nurinpäin olevaa korttia, vikassa kuusi.
                pinoLaskuri++;
            }
            
            kortitOikein.lisaaKortti(korttiPakka.get(korttiPakka.size() - pinoLaskuri));
            
            pinoLaskuri++;
            pinotNurin[i] = kortitNurin;
            pinotOikein[i] = kortitOikein;
        }

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
