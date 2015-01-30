package Klondike.Pelilauta;

import java.util.*;

/**
 * Tästä luokasta luodaan ilmentymät kaikille pelin käyttämille pinoille.
 * Piilossa olevalle pinolla ja jo käännetyille korteille luodaan omat pinot.
 *
 * @author Zonsu
 */
public class Korttipino {

    //Luku kertoo pinon paikan: 0 korttipakka, 1-7 pelipinot ja 8-11 maalipinot. Käytetään tunnisteena.
    private int pino;
    private ArrayList<Kortti> kortit;

    public Korttipino(int pino) {
        this.pino = pino;
        this.kortit = new ArrayList();
    }

    public int getPino() {
        return this.pino;
    }

    public void setPino(int pino) {
        this.pino = pino;
    }

    public ArrayList getKortit() {
        return this.kortit;
    }

    public void lisaaKortti(Kortti kortti) {
        this.kortit.add(kortti);
    }

//   public void poistaKortti(Kortti kortti) {
//        kortit.remove(kortti);
//    }
    
    /**
     * Tyhjennyksessä jotain pielessä ja se tulee tutkia ja mahd. muuttaa.
     */
    public void tyhjennaPino() {
        this.kortit.clear();
    }

    /**
     * Nosta poistaa ja näytä kurkkaa päälimmäisen.
     */
    public void nostaPaalimmainen() {
        this.kortit.remove(kortit.size() - 1);
    }

    public Kortti naytaPaalimmainen() {
        return this.kortit.get(kortit.size() - 1);
    }

    public int pinonKoko() {
        return this.kortit.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Korttipino other = (Korttipino) obj;
        if (this.pino != other.pino) {
            return false;
        }
        if (!Objects.equals(this.kortit, other.kortit)) {
            return false;
        }
        return true;
    }

}
