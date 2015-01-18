/*
 Tästä luokasta luodaan ilmentymät kaikille pelin käyttämille pinoille. Piilossa olevalle pinolla ja jo käännetyille korteille
 luodaan omat pinot.
 */
package Klondike;

import java.util.*;

public class Korttipino {

    private int pino;            //Luku kertoo pinon paikan: 0 korttipakka, 1-7 pelipinot ja 8-12 maalipinot.
    private boolean kaannetty;   //Kertoo onko kyseessä käännettyjen korttien pino vai ei.
    private ArrayList<Kortti> kortit;

    
    public Korttipino(int pino, boolean kaannetty) {
        this.pino = pino;
        this.kaannetty = kaannetty;
        this.kortit = new ArrayList();
    }
    
    public int getPino() {
        return pino;
    }

    public void setPino(int pino) {
        this.pino = pino;
    }

    public boolean isKaannetty() {
        return kaannetty;
    }

    public void setKaannetty(boolean kaannetty) {
        this.kaannetty = kaannetty;
    }

    public ArrayList getKortit() {
        return kortit;
    }

    public void lisaaKortti(Kortti kortti) {
        kortit.add(kortti);
    }

    public void poistaKortti(Kortti kortti) {
        kortit.remove(kortti);
    }

    public void poistaViimeinen() {
        kortit.remove(kortit.size() - 1);
    }

    public void poistaEnsimmainen() {
        kortit.remove(0);
    }
    public Kortti naytaPaalimmainen() {
        return kortit.get(kortit.size()-1);
    }
}
