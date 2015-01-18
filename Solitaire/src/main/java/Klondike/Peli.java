/*
 Täältä löytyy varsinaista peliä koskevat toiminnallisuudet.
 */
package Klondike;

import java.util.*;

public class Peli {

    public static ArrayList<Kortti> korttiPakka;

    public static void main(String[] args) {

        luoPakka();
        sekoitaPakka();

        jaaKortit();

    }

    public static void luoPakka() {                             // Pelin alussa luodaan uusi korttiPakka.

        korttiPakka = new ArrayList();

        for (int i = 0; i < 4; i++) {                           // 4 maata ja 13 eri korttia. Selkeyden vuoksi korttien maat Stringeinä.
            for (int j = 0; j < 13; j++) {

                Kortti kortti = new Kortti(j);

                if (i == 0) {
                    kortti.setMaa("pata");
                }
                if (i == 1) {
                    kortti.setMaa("hertta");
                }
                if (i == 2) {
                    kortti.setMaa("risti");
                }
                if (i == 3) {
                    kortti.setMaa("ruutu");
                }
                korttiPakka.add(kortti);
            }
        }
    }

    public static void sekoitaPakka() {
        Collections.shuffle(korttiPakka);
    }

    public static void jaaKortit() {

        for (int i = 1; i < 8; i++) {
            Korttipino kortitNurin = new Korttipino(i, false);
            Korttipino kortitOikein = new Korttipino(i, true);

            for (int k = 1; k < i; k++) {
                kortitNurin.lisaaKortti(korttiPakka.get(korttiPakka.size() - 1));
            }
            for (int k = 1; k < i + 1; k++) {
                kortitOikein.lisaaKortti(korttiPakka.get(korttiPakka.size() - 1));
            }
        }
    }

}
