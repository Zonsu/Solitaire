/*
 Täältä löytyy varsinaista peliä koskevat toiminnallisuudet.
 */
package Klondike.Pelilauta;

import java.util.*;

public class Peli {

    public static Korttipino[] pinotNurin;
    public static Korttipino[] pinotOikein;
    public static Korttipino[] maaliPinot;

    public static void main(String[] args) {

        KorttienJako.uudetKortit();                                            // Luodaan uusi pakka kortteja, sekoitetaan ja jaetaan ne pelilaudalle.

//        //Seuraavat testejä.
        
//        testiTulostus();
//        
//        KorttienJako.jaaUudestaan();
//        
//        testiTulostus();
//        
//        KorttienJako.uusiJako();
//        
//        testiTulostus();
        
    }

    public static void testiTulostus() {
        Korttipino testiPino1;
        Korttipino testiPino2;

        for (int i = 0; i < pinotNurin.length; i++) {
            testiPino1 = pinotNurin[i];
            testiPino2 = pinotOikein[i];

            System.out.println("Nurinpinossa nro. " + i + ". on " + testiPino1.pinonKoko() + " korttia. " + testiPino1.getKortit());

            System.out.println("Oikeinpinossa nro. " + i + ". on " + testiPino2.pinonKoko() + " korttia. " + testiPino2.getKortit());
            System.out.println("");
        }
    }

}
