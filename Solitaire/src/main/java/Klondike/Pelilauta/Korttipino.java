/*
 Tästä luokasta luodaan ilmentymät kaikille pelin käyttämille pinoille. Piilossa olevalle pinolla ja jo käännetyille korteille
 luodaan omat pinot.
 */
package Klondike.Pelilauta;

import java.util.*;

public class Korttipino {

    private int pino;                       //Luku kertoo pinon paikan: 0 korttipakka, 1-7 pelipinot ja 8-11 maalipinot. Käytetään tunnisteena.
    private ArrayList<Kortti> kortit;

    public Korttipino(int pino) {
        this.pino = pino;
        this.kortit = new ArrayList();
    }

    public int getPino() {
        return pino;
    }

    public void setPino(int pino) {
        this.pino = pino;
    }

    public ArrayList getKortit() {
        return kortit;
    }

    public void lisaaKortti(Kortti kortti) {
        kortit.add(kortti);
    }

//    public void poistaKortti(Kortti kortti) {
//        kortit.remove(kortti);
//    }

    public void poistaViimeinen() {
        kortit.remove(kortit.size() - 1);
    }

    public Kortti naytaPaalimmainen() {
        return kortit.get(kortit.size() - 1);
    }

    public int pinonKoko() {
        return kortit.size();
    }

}
