package Klondike.Pelilauta;

import Klondike.Pelilogiikka.KortinSiirto;
import java.util.*;

/**
 * Pelilauta pitää muistissa korttipinojen paikat ja siirrot.
 *
 * @author Zonsu
 */
public class Pelilauta {

    public static Korttipino[] pinotNurin;
    public static Korttipino[] pinotOikein;
    public static Korttipino[] maaliPinot;

    public Pelilauta() {
        KorttienJako.uudetKortit();
    }

}
