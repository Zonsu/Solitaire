/*
 Tästä luokasta generoidaan pelissä käytettävät kortit. Kortin arvo voi olla välillä 1-13 ja maa
 hertta, pata, ruutu tai risti.
 */
package Klondike;

public class Kortti {

    private String maa;
    private int arvo;

    public Kortti(String maa, int arvo) {
        this.maa = maa;
        this.arvo = arvo;
    }

    public String getMaa() {
        return maa;
    }

    public void setMaa(String maa) {
        this.maa = maa;
    }

    public int getArvo() {
        return arvo;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }

}
