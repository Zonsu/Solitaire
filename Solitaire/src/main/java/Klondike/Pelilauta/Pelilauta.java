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

    public static Korttipino[] getPinotNurin() {
        return pinotNurin;
    }

    public static Korttipino[] getPinotOikein() {
        return pinotOikein;
    }

    public static Korttipino[] getMaaliPinot() {
        return maaliPinot;
    }

    public static Korttipino getNurinPino(int i) {
        return pinotNurin[i];
    }

    public static Korttipino getOikeinPino(int i) {
        return pinotNurin[i];
    }

    public static Korttipino getMaaliPino(int i) {
        return maaliPinot[i];
    }

    public static Kortti getNurinPaalimmainen(int i) {
        Korttipino pino = getNurinPino(i);
        return pino.naytaPaalimmainen();
    }

    public static Kortti getOikeinPaalimmainen(int i) {
        Korttipino pino = getOikeinPino(i);
        return pino.naytaPaalimmainen();
    }

    public static Kortti getMaalipinoPaalimmainen(int i) {
        Korttipino pino = getMaaliPino(i);
        return pino.naytaPaalimmainen();
    }
}
