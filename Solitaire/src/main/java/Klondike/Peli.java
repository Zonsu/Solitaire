/*
 Täältä löytyy varsinaista peliä koskevat toiminnallisuudet.
 */
package Klondike;

import java.util.*;

public class Peli {

    public static ArrayList<Kortti> korttiPakka;

    public static void main(String[] args) {

        luoPakka();                                                                 // Generoidaan korttipakka, 52 korttia.
        sekoitaPakka();                                                             // Sekoitetaan pakka.

        jaaKortit();                                                                // Jaetaan saatu korttipakka pelilaudalle.

    }

    public static void luoPakka() {                                                 

        korttiPakka = new ArrayList();

        for (int i = 0; i < 4; i++) {                                               // 4 maata ja 13 eri korttia. Selkeyden vuoksi korttien
            for (int j = 0; j < 13; j++) {                                          // maat Stringeinä.

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

        for (int i = 1; i < 8; i++) {                                               // Jaetaan kortit ensin seitsemään pelipinoon. Jokaisen pinon
            Korttipino kortitNurin = new Korttipino(i, false);                      //  päällä on yksi kortti kuvapuoli ylöspäin.    
            Korttipino kortitOikein = new Korttipino(i, true);

            for (int k = 1; k < i; k++) {
                kortitNurin.lisaaKortti(korttiPakka.get(korttiPakka.size() - 1));   // Ekassa pinossa ei yhtään nurinpäin olevaa korttia, vikassa kuusi.
            }
                kortitOikein.lisaaKortti(korttiPakka.get(korttiPakka.size() - 1));

        }
    }

}
